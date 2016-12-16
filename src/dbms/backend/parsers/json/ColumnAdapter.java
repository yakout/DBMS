package dbms.backend.parsers.json;

import com.google.gson.*;
import dbms.datatypes.*;
import dbms.util.Column;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Serializes/Deserializes a column to .JSON format.
 */
public class ColumnAdapter implements JsonSerializer<Column>, JsonDeserializer<Column> {

    @Override
    public JsonElement serialize(Column column, Type type, JsonSerializationContext jsc) {
        JsonObject columnObject = new JsonObject();
        columnObject.addProperty("name", column.getName());
        String typeProperty = null;
        try {
            typeProperty = (String) column.getType().getMethod(
                    "getKey").invoke(column.getType().newInstance());
        } catch (IllegalAccessException | InvocationTargetException
                | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
        }
        columnObject.addProperty("type", typeProperty);
        columnObject.add("entries", addSerializedEntries(
                column, typeProperty));
        return columnObject;
    }

    @Override
    public Column deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsc)
            throws JsonParseException {
        Column column = new Column();
        String nameProp = jsonElement.getAsJsonObject().getAsJsonPrimitive(
                "name").getAsString();
        String typeProp = jsonElement.getAsJsonObject().getAsJsonPrimitive(
                "type").getAsString();
        Class<? extends DBDatatype> typeClass = DatatypeFactory.getFactory()
                .getRegisteredDatatype(typeProp);
        column.setName(nameProp);
        column.setType(typeClass);
        JsonArray entries = jsonElement.getAsJsonObject().getAsJsonArray("entries");
        for (JsonElement entry : entries) {
            if (typeProp.equals(DBInteger.KEY)) {
                try {
                    Integer x = entry.getAsJsonPrimitive().getAsInt();
                    column.addEntry(new DBInteger(x));
                } catch (NumberFormatException e) {
                    column.addEntry(null);
                }
            } else if (typeProp.equals(DBString.KEY)) {
                String x = entry.getAsJsonPrimitive().getAsString();
                column.addEntry(new DBString(x));
            } else if (typeProp.equals(DBFloat.KEY)) {
                try {
                    Float x = entry.getAsJsonPrimitive().getAsFloat();
                    column.addEntry(new DBFloat(x));
                } catch (NumberFormatException e) {
                    column.addEntry(null);
                }
            } else if (typeProp.equals(DBDate.KEY)) {
                String dateString = entry.getAsJsonPrimitive().getAsString();
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date x = new Date(format.parse(dateString).getTime());
                    column.addEntry(new DBDate(x));
                } catch (ParseException e) {
                    column.addEntry(null);
                }
            }
        }
        return column;
    }

    private JsonArray addSerializedEntries(Column column, String typeProperty) {
        JsonArray entries = new JsonArray();
        for (DBDatatype entry : column.getEntries()) {
            JsonPrimitive obj = null;
            if (typeProperty.equals(DBInteger.KEY)) {
                if (entry == null) {
                    obj = new JsonPrimitive("");
                } else {
                    obj = new JsonPrimitive((Integer) entry.getValue());
                }
            } else if (typeProperty.equals(DBString.KEY)) {
                if (entry == null || entry.getValue().equals("")) {
                    obj = new JsonPrimitive("");
                } else {
                    obj = new JsonPrimitive((String) entry.getValue());
                }
            } else if (typeProperty.equals(DBFloat.KEY)) {
                if (entry == null) {
                    obj = new JsonPrimitive("");
                } else {
                    obj = new JsonPrimitive((Float) entry.getValue());
                }
            } else if (typeProperty.equals(DBDate.KEY)) {
                if (entry == null) {
                    obj = new JsonPrimitive("");
                } else {
                    DBDate date = (DBDate) entry;
                    obj = new JsonPrimitive(date.toString());
                }
            }
            entries.add(obj);
        }
        return entries;
    }
}
