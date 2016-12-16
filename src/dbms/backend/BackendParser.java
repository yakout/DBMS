package dbms.backend;

import dbms.exception.DatabaseAlreadyCreatedException;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.util.Database;
import dbms.util.Table;

import java.io.File;

public abstract class BackendParser {
    public static void createDatabase(Database database)
            throws DatabaseAlreadyCreatedException {
        File workspace = new File(BackendController.getInstance().getCurrentDatabaseDir());
        if (!workspace.exists()) {
            workspace.mkdirs();
        }
        File databaseDir = new File(workspace,
                database.getName());
        if (!databaseDir.exists()) {
            databaseDir.mkdirs();
        } else {
            throw new DatabaseAlreadyCreatedException();
        }
    }

    public static void dropDatabase(Database database)
            throws DatabaseNotFoundException {
        File databaseDir = new File(BackendController.getInstance().getCurrentDatabaseDir()
                + File.separator + database.getName());
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

    public abstract void loadTable(Table table)
            throws TableNotFoundException, DatabaseNotFoundException;

    public abstract void writeToFile(Table table)
            throws TableNotFoundException, DatabaseNotFoundException;

    public abstract void createTable(Table table)
            throws DatabaseNotFoundException, TableAlreadyCreatedException;

    public abstract void dropTable(Table table)
            throws DatabaseNotFoundException;
}
