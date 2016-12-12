package dbms.backend.parsers.json.pojo;

import java.util.Collection;

public class TablePojo {
    private String name;
    private Collection<ColumnPojo> columns;
    private int size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<ColumnPojo> getColumns() {
        return columns;
    }

    public void setColumns(Collection<ColumnPojo> columns) {
        this.columns = columns;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
