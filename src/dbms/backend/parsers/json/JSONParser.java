package dbms.backend.parsers.json;

import dbms.backend.BackendParser;
import dbms.backend.BackendParserFactory;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.util.Table;

import java.io.File;
import java.util.ResourceBundle;

public class JSONParser extends BackendParser {
    public static final String KEY = "json";
    private static JSONParser instance = null;
    private static final String WORKSPACE_DIR =
            System.getProperty("user.home") + File.separator + "databases";
    private static final ResourceBundle CONSTANTS =
            ResourceBundle.getBundle("dbms.backend.parsers.json.Constants");

    static {
        BackendParserFactory.getFactory().register(KEY, getInstance());
    }

    private JSONParser() {
    }

    public static JSONParser getInstance() {
        if (instance == null) {
            instance = new JSONParser();
        }
        return instance;
    }

    @Override
    public BackendParser getParser() {
        return instance;
    }

    @Override
    public void loadTable(Table table) throws TableNotFoundException, DatabaseNotFoundException {
    	
    }

    @Override
    public void writeToFile(Table table) throws TableNotFoundException, DatabaseNotFoundException {
    	
    }

    @Override
    public void createTable(Table table) throws DatabaseNotFoundException, TableAlreadyCreatedException {
    	File tableFile = new File(openDB(table.getDatabase().getName()), table.getName()
				+ CONSTANTS.getString("extension.json"));
		if (tableFile.exists()) {
			throw new TableAlreadyCreatedException();
		}
		writeToFile(table, tableFile);
    }
    
    private void writeToFile(Table table, File tableFile) {
    	
    }

    @Override
    public void dropTable(Table table) throws DatabaseNotFoundException {

    }
    
	private File openTable(String dbName, String tableName)
			throws TableNotFoundException, DatabaseNotFoundException {
		File tableFile = new File(openDB(dbName), tableName
				+ CONSTANTS.getString("extension.xml"));
		if (!tableFile.exists()) {
			throw new TableNotFoundException();
		}
		return tableFile;
	}

	private File openDB(String dbName)
			throws DatabaseNotFoundException {
		File database = new File(WORKSPACE_DIR + File.separator + dbName);
		if (!database.exists()) {
			throw new DatabaseNotFoundException();
		}
		return database;
	}
}
