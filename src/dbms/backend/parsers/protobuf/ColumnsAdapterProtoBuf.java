package dbms.backend.parsers.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import dbms.datatypes.*;
import dbms.util.Column;
import dbms.util.Table;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ColumnsAdapterProtoBuf {

    public ColumnsAdapterProtoBuf()  {
    }

    public void desrializeColumns (byte[] deserialzedData, Table table) throws InvalidProtocolBufferException {
        TableProtoBuf.TableModule tableModule = TableProtoBuf.TableModule.parseFrom(deserialzedData);
        List<TableProtoBuf.TableModule.ColumnModule> columnsModule = tableModule.getColumnsList();
        List<Column> cloneColumns = new ArrayList<>();
        for (TableProtoBuf.TableModule.ColumnModule col : columnsModule) {
            String columnName = col.getColumnName();
            String typeProp = col.getColumnDataType();
            Class<? extends DBDatatype> typeClass = DatatypeFactory.getFactory()
                    .getRegisteredDatatype(col.getColumnDataType());
            Column newColumn = new Column();
            newColumn.setName(columnName);
            newColumn.setType(typeClass);
            List<String> columnValues = col.getEntriesList();

            for (String stringValue : columnValues) {
                if (typeProp.equals(DBInteger.KEY)) {
                    try {
                        Integer x = Integer.parseInt(stringValue);
                        newColumn.addEntry(new DBInteger(x));
                    } catch (NumberFormatException e) {
                        newColumn.addEntry(null);
                    }
                } else if (typeProp.equals(DBString.KEY)) {
                    if (stringValue.compareTo("") == 0) {
                        newColumn.addEntry(null);
                    } else {
                        newColumn.addEntry(new DBString(stringValue));
                    }
                } else if (typeProp.equals(DBFloat.KEY)) {
                    try {
                        Float x = Float.parseFloat(stringValue);
                        newColumn.addEntry(new DBFloat(x));
                    } catch (NumberFormatException e) {
                        newColumn.addEntry(null);
                    }
                } else if (typeProp.equals(DBDate.KEY)) {
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date x = new Date(format.parse(stringValue).getTime());
                        newColumn.addEntry(new DBDate(x));
                    } catch (ParseException e) {
                        newColumn.addEntry(null);
                    }
                }
            }
            cloneColumns.add(newColumn);
        }
        table.setColumns(cloneColumns);
        table.setSize(cloneColumns.get(0).getEntries().size());
    }



    public byte[] serializeTable (Table table) throws IllegalAccessException, InstantiationException,
            NoSuchMethodException, InvocationTargetException {
        List<TableProtoBuf.TableModule.ColumnModule> columnsModule = new ArrayList<>();
        List<Column> columns = table.getColumns();
        for (Column col : columns) {

            List<String> toProtoEntries = new ArrayList<>();
            List<DBDatatype> entries = col.getEntries();

            for (DBDatatype en : entries) {
                try {
                    toProtoEntries.add(en.toString());
                } catch (NullPointerException e) {
                    toProtoEntries.add("");
                }

            }

            TableProtoBuf.TableModule.ColumnModule columnMod  =  TableProtoBuf.TableModule.ColumnModule
                    .newBuilder()
                    .setColumnName(col.getName())
                    .setColumnDataType((String) col.getType().getMethod(
                            "getKey").invoke(col.getType().newInstance()))
                    .addAllEntries(toProtoEntries)
                    .build();

            columnsModule.add(columnMod);
        }

        TableProtoBuf.TableModule tableProtoBuf = TableProtoBuf.TableModule
                .newBuilder()
                .setTableName(table.getName())
                .setDatabaseName(table.getDatabase().getName())
                .addAllColumns(columnsModule)
                .build();

        return tableProtoBuf.toByteArray();
    }

    //TODO
    // check null or empty entries and how I will check them.
    // the extension of the output file.

}
