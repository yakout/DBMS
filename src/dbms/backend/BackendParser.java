package dbms.backend;

import java.io.File;

import dbms.exception.DatabaseAlreadyCreatedException;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.util.Database;
import dbms.util.Table;

public abstract class BackendParser {
	private static final String WORKSPACE_DIR =
			System.getProperty("user.home") + File.separator + "databases";

	public static void createDatabase(Database database)
			throws DatabaseAlreadyCreatedException {
		File workspace = new File(WORKSPACE_DIR);
		if (!workspace.exists()) {
			workspace.mkdir();
		}
		File databaseDir = new File(workspace,
				database.getName());
		if (!databaseDir.exists()) {
			databaseDir.mkdir();
		} else {
			throw new DatabaseAlreadyCreatedException();
		}
	}

	public static void dropDatabase(Database database)
			throws DatabaseNotFoundException {
		File databaseDir = new File(WORKSPACE_DIR + File.separator
				+ database.getName());
		if (databaseDir.exists()) {
			String[] files = databaseDir.list();
			for (String fileName : files) {
				new File(databaseDir.getPath(), fileName).delete();
			}
			databaseDir.delete();
		} else {
			throw new DatabaseNotFoundException();
		}
	}

	public abstract BackendParser getParser();

	public abstract void load(Table table)
			throws TableNotFoundException, DatabaseNotFoundException;

	public abstract void writeTo(Table table)
			throws TableNotFoundException, DatabaseNotFoundException;

	public abstract void create(Table table)
			throws DatabaseNotFoundException, TableAlreadyCreatedException;

	public abstract void dropTable(String dbName, String tableName)
			throws DatabaseNotFoundException;
}