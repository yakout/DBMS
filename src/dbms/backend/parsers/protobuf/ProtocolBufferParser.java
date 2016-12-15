package dbms.backend.parsers.protobuf;

import dbms.backend.BackendParser;
import dbms.backend.BackendParserFactory;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.util.Table;

public class ProtocolBufferParser extends BackendParser {
    public static final String KEY = "alt";
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
    public void loadTable(Table table) throws TableNotFoundException, DatabaseNotFoundException {

    }

    @Override
    public void writeToFile(Table table) throws TableNotFoundException, DatabaseNotFoundException {

    }

    @Override
    public void createTable(Table table) throws DatabaseNotFoundException, TableAlreadyCreatedException {

    }

    @Override
    public void dropTable(Table table) throws DatabaseNotFoundException {

    }
}
