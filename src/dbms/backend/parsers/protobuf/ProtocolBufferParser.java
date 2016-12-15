package dbms.backend.parsers.protobuf;

import dbms.backend.BackendController;
import dbms.backend.BackendParser;
import dbms.backend.BackendParserFactory;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.util.Table;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

public class ProtocolBufferParser extends BackendParser {
    public static final String KEY = "alt";
    private static ProtocolBufferParser instance = null;
    private static final String WORKSPACE_DIR =
            System.getProperty("user.home") + File.separator + "databases";
    private static final ResourceBundle CONSTANTS =
            ResourceBundle.getBundle("dbms.backend.parsers.protobuf.Constants");

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
    public void loadTable(Table table) throws TableNotFoundException, DatabaseNotFoundException {
        File tableFile = openTable(table.getDatabase().getName(), table.getName());
        try {
            load(table, tableFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void writeToFile(Table table) throws TableNotFoundException, DatabaseNotFoundException {
        File tableFile = openTable(table.getDatabase().getName(), table.getName());
        try {
            write(table, tableFile);
        } catch (IOException | NoSuchMethodException
                | InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTable(Table table) throws DatabaseNotFoundException, TableAlreadyCreatedException {
        File tableFile = new File(openDB(table.getDatabase().getName()),
                table.getName() + ".txt");
        if (tableFile.exists()) {
            throw new TableAlreadyCreatedException();
        }
        try {
            write(table, tableFile);
        } catch (IOException | NoSuchMethodException
                | InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropTable(Table table) throws DatabaseNotFoundException {
        File tableFile = new File(openDB(table.getDatabase().getName()),
                table.getName() + ".txt" );
        if (tableFile.exists()) {
            tableFile.delete();
        }
    }

    private void write (Table table, File tableFile) throws InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, IOException {
        ColumnsAdapterProto columnAdapter = new ColumnsAdapterProto();
        byte[] serializedData = columnAdapter.serializeTable(table);
        FileOutputStream fileOutputStream = new FileOutputStream(tableFile);
        fileOutputStream.write(serializedData);
        fileOutputStream.close();

    }

    private void load (Table table , File tableFile) throws DatabaseNotFoundException,
            TableNotFoundException, IOException {
        ColumnsAdapterProto columnAdapter = new ColumnsAdapterProto();
        FileInputStream fileInputStream = new FileInputStream(tableFile);
        byte[] deSerializedData = new byte[(int)tableFile.length()];
        fileInputStream.read(deSerializedData);
        columnAdapter.desrializeColumns(deSerializedData,table);
    }

    private static File openDB(String dbName) throws DatabaseNotFoundException {
        File database = new File(BackendController.getInstance().getCurrentDatabaseDir()
                + File.separator + dbName);
        if (!database.exists()) {
            throw new DatabaseNotFoundException();
        }
        return database;
    }

    private static File openTable(String dbName, String tableName) throws TableNotFoundException,
            DatabaseNotFoundException {
        File tableFile = new File(openDB(dbName), tableName + ".txt");
        if (!tableFile.exists()) {
            throw new TableNotFoundException();
        }
        return tableFile;
    }
}
