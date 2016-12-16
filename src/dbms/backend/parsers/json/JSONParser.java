package dbms.backend.parsers.json;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dbms.backend.BackendController;
import dbms.backend.BackendParser;
import dbms.backend.BackendParserFactory;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.util.Column;
import dbms.util.Database;
import dbms.util.Table;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ResourceBundle;

/**
 * Table parser to .JSON format.
 */
public class JSONParser extends BackendParser {

    /**
     * Key to JSON parser that is used to register to factory.
     */
    public static final String KEY = "alt";

    /**
     * Resource bundle to constants.
     */
    private static final ResourceBundle CONSTANTS = ResourceBundle.getBundle(
            "dbms.backend.parsers.json.Constants");

    /**
     * Logger.
     */
    private static Logger log = LogManager.getLogger(JSONParser.class);

    /**
     * {@link GsonBuilder} Builds GSON objects.
     */
    private GsonBuilder builder;

    /**
     * {@link Gson} Google Gson object that is used to parse tables.
     */
    private Gson gson;

    /**
     * Static singleton instance.
     */
    private static JSONParser instance = null;

    static {
        BackendParserFactory.getFactory().register(KEY, getInstance());
    }

    private JSONParser() {
        enhanceBuilder();

    }

    /**
     * Gets static instance.
     * @return static instance.
     */
    public static JSONParser getInstance() {
        if (instance == null) {
            instance = new JSONParser();
        }
        return instance;
    }

    private static File openTable(String dbName, String tableName) throws TableNotFoundException,
            DatabaseNotFoundException {
        File tableFile = new File(openDB(dbName), tableName + CONSTANTS.getString(
                "extension.json"));
        if (!tableFile.exists()) {
            log.error("Error occured: " + tableName + " file is not found!");
            throw new TableNotFoundException();
        }
        return tableFile;
    }

    private static File openDB(String dbName) throws DatabaseNotFoundException {
        File database = new File(BackendController.getInstance().getCurrentDatabaseDir()
                + File.separator + dbName);
        if (!database.exists()) {
            log.error("Error occured: Database is not found.");
            throw new DatabaseNotFoundException();
        }
        return database;
    }

    private void enhanceBuilder() {
        builder = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                if (fieldAttributes.getDeclaringClass().equals(Database.class)) {
                    if (fieldAttributes.getName().equals("tables")) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> arg0) {
                return false;
            }
        });
        gson = builder.serializeNulls().disableHtmlEscaping()
                .registerTypeAdapterFactory(new ClassTypeAdapterFactory())
                .registerTypeAdapter(Column.class, new ColumnAdapter())
                .setPrettyPrinting().create();
    }

    @Override
    public BackendParser getParser() {
        return instance;
    }

    @Override
    public void loadTable(Table table) throws TableNotFoundException,
            DatabaseNotFoundException {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(openTable(
                    table.getDatabase().getName(), table.getName())));
        } catch (FileNotFoundException e) {
            log.error("Error occured while loading the table.");
            throw new TableNotFoundException();
        }
        log.debug("\'" + table.getName() + "\' is loaded successfully.");
        table.setTable(gson.fromJson(bufferedReader, Table.class));
    }

    @Override
    public void writeToFile(Table table) throws TableNotFoundException,
            DatabaseNotFoundException {
        File tableFile = openTable(table.getDatabase().getName(), table.getName());
        try {
            write(table, tableFile);
        } catch (IOException e) {
            log.error("Error occured while saving in JSON file.");
            e.printStackTrace();
        }
        log.debug("Saved into JSON file successfully.");
    }

    @Override
    public void createTable(Table table) throws DatabaseNotFoundException,
            TableAlreadyCreatedException {
        File tableFile = new File(openDB(table.getDatabase().getName()),
                table.getName() + CONSTANTS.getString("extension.json"));
        if (tableFile.exists()) {
            log.error("Can't create table with name" + table.getName()
                    + " this nameDatabase already created");
            throw new TableAlreadyCreatedException();
        }
        try {
            write(table, tableFile);
        } catch (IOException e) {
            log.error("Error ocurred while parsing!");
            e.printStackTrace();
        }
        log.debug("\'" + table.getName() + "\' is created successfully.");

    }

    private void write(Table table, File tableFile) throws IOException {
        FileWriter writer = new FileWriter(tableFile);
        writer.write(gson.toJson(table));
        writer.close();
    }

    @Override
    public void dropTable(Table table) throws DatabaseNotFoundException {
        File tableFile = new File(openDB(table.getDatabase().getName()), table.getName()
                + CONSTANTS.getString("extension.json"));
        if (tableFile.exists()) {
            tableFile.delete();
            log.debug(table.getName() + " file is successfully deleted.");
        } else {
            log.error("Error occured: " + table.getName() + " file is not found!");
        }
    }

}