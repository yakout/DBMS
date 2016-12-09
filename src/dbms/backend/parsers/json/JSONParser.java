package dbms.backend.parsers.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import dbms.backend.BackendParser;
import dbms.backend.BackendParserFactory;
import dbms.datatypes.DBDate;
import dbms.datatypes.DBFloat;
import dbms.datatypes.DBInteger;
import dbms.datatypes.DBString;
import dbms.exception.DatabaseAlreadyCreatedException;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.util.Column;
import dbms.util.Database;
import dbms.util.Table;

public class JSONParser extends BackendParser {
	public static final String KEY = "json";
	private static JSONParser instance = null;
	private static final String WORKSPACE_DIR = System.getProperty("user.home") + File.separator + "databases";
	private static final ResourceBundle CONSTANTS = ResourceBundle.getBundle("dbms.backend.parsers.json.Constants");

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
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapterFactory(new ClassTypeAdapterFactory());
		builder.registerTypeAdapter(DBString.class, new ClassTypeAdapter());
		builder.registerTypeAdapter(DBInteger.class, new ClassTypeAdapter());
		builder.registerTypeAdapter(DBFloat.class, new ClassTypeAdapter());
		builder.registerTypeAdapter(DBDate.class, new ClassTypeAdapter());
		Gson gson = builder.create();
		BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(new FileReader(table.getName() + CONSTANTS.getString("extension.json")));
		} catch (FileNotFoundException e) {
			throw new TableNotFoundException();
		}
		table = gson.fromJson(bufferedReader, Table.class);
	}

	@Override
	public void writeToFile(Table table) throws TableNotFoundException, DatabaseNotFoundException {
		File tableFile = openTable(table.getDatabase().getName(), table.getName());
		try {
			write(table, tableFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	public static void writeToFile1(Table table , File tableFile) throws
//		TableNotFoundException, DatabaseNotFoundException {
//		try {
//			write(table, tableFile);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	@Override
	public void createTable(Table table) throws DatabaseNotFoundException, TableAlreadyCreatedException {
		File tableFile = new File(openDB(table.getDatabase().getName()),
				table.getName() + CONSTANTS.getString("extension.json"));
		if (tableFile.exists()) {
			throw new TableAlreadyCreatedException();
		}
	}

	private static void write(Table table, File tableFile) throws IOException {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapterFactory(new ClassTypeAdapterFactory());
		builder.registerTypeAdapter(DBString.class, new ClassTypeAdapter());
		builder.registerTypeAdapter(DBInteger.class, new ClassTypeAdapter());
		builder.setPrettyPrinting();
		Gson gson = builder.create();
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

	private static File openTable(String dbName, String tableName) throws TableNotFoundException, DatabaseNotFoundException {
		File tableFile = new File(openDB(dbName), tableName + CONSTANTS.getString("extension.json"));
		if (!tableFile.exists()) {
			throw new TableNotFoundException();
		}
		return tableFile;
	}

	private static File openDB(String dbName) throws DatabaseNotFoundException {
		File database = new File(WORKSPACE_DIR + File.separator + dbName);
		if (!database.exists()) {
			throw new DatabaseNotFoundException();
		}
		return database;
	}

//	public static void main(String[] argv) {
//		Database database = new Database("Yakout");
//		Table tb = database.createTable("anas14");
//		tb.addColumn(new Column("hamada", DBString.class));
//		tb.addColumn(new Column("Naggar", DBString.class));
//		tb.addColumn(new Column("tolba", DBInteger.class));
//		
//		try {
//			createDatabase(database);
//		} catch (DatabaseAlreadyCreatedException e1) {
//			e1.printStackTrace();
//		}
//		
//		try {
//			createTable1(tb);
//		} catch (DatabaseNotFoundException | TableAlreadyCreatedException e) {
//			e.printStackTrace();
//		}
//		tb.clear();
//		try {
//			loadTable1(tb);
//		} catch (TableNotFoundException | DatabaseNotFoundException e) {
//			e.printStackTrace();
//		}
//		System.out.println(tb);
//}
}