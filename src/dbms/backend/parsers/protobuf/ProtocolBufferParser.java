package dbms.backend.parsers.protobuf;

import dbms.backend.BackendController;
import dbms.backend.BackendParser;
import dbms.backend.BackendParserFactory;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.util.Table;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

public class ProtocolBufferParser extends BackendParser {
	private static Logger log = LogManager.getLogger(
			ProtocolBufferParser.class);
    public static final String KEY = "alt";
    private static final ResourceBundle CONSTANTS =
            ResourceBundle.getBundle("dbms.backend.parsers.protobuf.Constants");

    private static ProtocolBufferParser instance = null;

    static {
        BackendParserFactory.getFactory().register(KEY, getInstance());
    }

    private ProtocolBufferParser() {
    }

    public static ProtocolBufferParser getInstance() {
        if (instance == null) {
            instance = new ProtocolBufferParser();
        }
        return instance;
    }

    @Override
    public BackendParser getParser() {
        return instance;
    }

    @Override
    public void loadTable(Table table)
    		throws TableNotFoundException, DatabaseNotFoundException {
        File tableFile = openTable(table.getDatabase().getName(),
        		table.getName());
        try {
            load(table, tableFile);
        } catch (IOException e) {
            log.error("Error occured while loading the table!");
            e.printStackTrace();
        }
        log.debug("Table is loaded successfully.");

    }

    @Override
    public void writeToFile(Table table)
    		throws TableNotFoundException, DatabaseNotFoundException {
        File tableFile = openTable(table.getDatabase().getName(),
        		table.getName());
        try {
            write(table, tableFile);
        } catch (IOException | NoSuchMethodException
                | InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            log.error("Error occured while parsing!");
            e.printStackTrace();
        }
        log.debug("Data is saved successfully.");
    }

    @Override
    public void createTable(Table table)
    		throws DatabaseNotFoundException, TableAlreadyCreatedException {
        File tableFile = new File(openDB(table.getDatabase().getName()),
                table.getName()
                        + CONSTANTS.getString("extension.proto"));
        if (tableFile.exists()) {
            log.error("Error occured: table is already created!");
            throw new TableAlreadyCreatedException();
        }
        try {
            write(table, tableFile);
        } catch (IOException | NoSuchMethodException
                | InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            log.error("Error occured while creating table!");
            e.printStackTrace();
        }
        log.debug("Table data is created successfully.");
    }

    @Override
    public void dropTable(Table table) throws DatabaseNotFoundException {
        File tableFile = new File(openDB(table.getDatabase().getName()),
                table.getName()
                        + CONSTANTS.getString("extension.proto") );

        if (tableFile.exists()) {
            tableFile.delete();
            log.debug("Table data is droped successfully.");
        }
    }

    private void write(Table table, File tableFile)
    		throws InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, IOException {
        ColumnsAdapterProtoBuf columnAdapter = new ColumnsAdapterProtoBuf();
        byte[] serializedData = columnAdapter.serializeTable(table);
        FileOutputStream fileOutputStream = new FileOutputStream(tableFile);
        fileOutputStream.write(serializedData);
        fileOutputStream.close();

    }

    private void load(Table table, File tableFile)
    		throws DatabaseNotFoundException,
            TableNotFoundException, IOException {
        ColumnsAdapterProtoBuf columnAdapter = new ColumnsAdapterProtoBuf();
        FileInputStream fileInputStream = new FileInputStream(tableFile);
        byte[] deSerializedData = new byte[(int) tableFile.length()];
        fileInputStream.read(deSerializedData);
        columnAdapter.deserializeColumns(deSerializedData,table);
    }

    private static File openDB(String dbName)
    		throws DatabaseNotFoundException {
        File database = new File(BackendController
        		.getInstance().getCurrentDatabaseDir()
                + File.separator + dbName);
        if (!database.exists()) {
        	log.error("Error occured: " + dbName + " database is not found!");
            throw new DatabaseNotFoundException();
        }
        return database;
    }

    private static File openTable(String dbName, String tableName)
    		throws TableNotFoundException,
            DatabaseNotFoundException {
        File tableFile = new File(openDB(dbName), tableName
                + CONSTANTS.getString("extension.proto"));
        if (!tableFile.exists()) {
        	log.error("Error occured: " + tableName + " is not found!");
            throw new TableNotFoundException();
        }
        return tableFile;
    }
}
