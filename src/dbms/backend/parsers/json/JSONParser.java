package dbms.backend.parsers.json;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dbms.backend.BackendController;
import dbms.backend.BackendParser;
import dbms.backend.BackendParserFactory;
import dbms.datatypes.DBInteger;
import dbms.datatypes.DBString;
import dbms.exception.DatabaseAlreadyCreatedException;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.util.Column;
import dbms.util.Database;
import dbms.util.Table;

import java.io.*;
import java.util.ResourceBundle;

public class JSONParser extends BackendParser {
	public static final String KEY = "alt";
	private static JSONParser instance = null;
	private static final ResourceBundle CONSTANTS = ResourceBundle.getBundle(
			"dbms.backend.parsers.json.Constants");
	private GsonBuilder builder;
	Gson gson;

	static {
		BackendParserFactory.getFactory().register(KEY, getInstance());
	}

	private JSONParser() {
		enhanceBuilder();
		
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
	public void loadTable(Table table) throws TableNotFoundException,
		DatabaseNotFoundException {
		BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(new FileReader(openTable(
					table.getDatabase().getName(), table.getName())));
		} catch (FileNotFoundException e) {
			throw new TableNotFoundException();
		}
		table.setTable(gson.fromJson(bufferedReader, Table.class));
	}

	@Override
	public void writeToFile(Table table) throws TableNotFoundException,
		DatabaseNotFoundException {
		File tableFile = openTable(table.getDatabase().getName(), table.getName());
		try {
			write(table, tableFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void createTable(Table table) throws DatabaseNotFoundException,
		TableAlreadyCreatedException {
		File tableFile = new File(openDB(table.getDatabase().getName()),
				table.getName() + CONSTANTS.getString("extension.json"));
		if (tableFile.exists()) {
			throw new TableAlreadyCreatedException();
		}
		try {
			write(table, tableFile);
		} catch (IOException e) {
			e.printStackTrace();
		}	
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
		}
	}

	private static File openTable(String dbName, String tableName) throws TableNotFoundException,
		DatabaseNotFoundException {
		File tableFile = new File(openDB(dbName), tableName + CONSTANTS.getString("extension.json"));
		if (!tableFile.exists()) {
			throw new TableNotFoundException();
		}
		return tableFile;
	}

	private static File openDB(String dbName) throws DatabaseNotFoundException {
		File database = new File(BackendController.getInstance().getCurrentDatabaseDir()
				+ File.separator + dbName);
		if (!database.exists()) {
			throw new DatabaseNotFoundException();
		}
		return database;
	}


	public static void main(String[] argv) {
		JSONParser parser = new JSONParser();
		Database database = new Database("Yakout");
		Table tb = database.createTable(("IdiotTable"));
		tb.setDatabase(database);
		tb.addColumn(new Column("hamada", DBString.class));
		tb.addColumn(new Column("Naggar", DBString.class));
		tb.addColumn(new Column("tolba", DBInteger.class));
		
		try {
			parser.createDatabase(database);
			parser.createTable(tb);
		} catch (DatabaseNotFoundException | TableAlreadyCreatedException
				| DatabaseAlreadyCreatedException e) {
			e.printStackTrace();
		}

		try {
			parser.writeToFile(tb);
		} catch (TableNotFoundException | DatabaseNotFoundException e) {
			e.printStackTrace();
		}
	}

}