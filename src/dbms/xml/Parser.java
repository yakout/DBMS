package dbms.xml;

import java.io.File;

import dbms.exception.DatabaseAlreadyCreatedException;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.util.Table;

public abstract class Parser {
	private static final String WORKSPACE_DIR =
			System.getProperty("user.home") + File.separator + "databases";

	public static void createDatabase(String dbName)
			throws DatabaseAlreadyCreatedException {
		File workspace = new File(WORKSPACE_DIR);
		if (!workspace.exists()) {
			workspace.mkdir();
		}
		File database = new File(workspace, dbName);
		if (!database.exists()) {
			database.mkdir();
		} else {
			throw new DatabaseAlreadyCreatedException();
		}
	}

	public static void dropDatabase(String dbName)
			throws DatabaseNotFoundException {
		File database = new File(WORKSPACE_DIR + File.separator + dbName);
		if (database.exists()) {
			String[] files = database.list();
			for (String fileName : files) {
				new File(database.getPath(), fileName).delete();
			}
			database.delete();
		} else {
			throw new DatabaseNotFoundException();
		}
	}

	public abstract void load(Table table)
			throws TableNotFoundException, DatabaseNotFoundException;

	public abstract void writeTo(Table table)
			throws TableNotFoundException, DatabaseNotFoundException;

	public abstract void create(Table table)
			throws DatabaseNotFoundException, TableAlreadyCreatedException;

	public abstract void dropTable(String dbName, String tableName)
			throws DatabaseNotFoundException;
}
