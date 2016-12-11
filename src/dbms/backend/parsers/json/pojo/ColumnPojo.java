package dbms.backend.parsers.json.pojo;

import dbms.datatypes.DBDatatype;

import java.util.Collection;

public class ColumnPojo {
    Collection<DBDatatype> entries;
    Class<? extends DBDatatype> type;
    String name;

    public Collection<DBDatatype> getEntries() {
        return entries;
    }

    public void setEntries(Collection<DBDatatype> entries) {
        this.entries = entries;
    }

    public Class<? extends DBDatatype> getType() {
        return type;
    }

    public void setType(Class<? extends DBDatatype> type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
