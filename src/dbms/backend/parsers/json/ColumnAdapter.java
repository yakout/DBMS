package dbms.backend.parsers.json;

import com.google.gson.*;
import dbms.datatypes.*;
import dbms.util.Column;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

public class ColumnAdapter implements JsonSerializer<Column>, JsonDeserializer<Column> {

    @Override
    public JsonElement serialize(Column column, Type type, JsonSerializationContext jsonSerializationContext) {
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
    public Column deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
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
                Integer x = null;
                try {
                    x = entry.getAsJsonPrimitive().getAsInt();
                } catch (NumberFormatException e) {
                }
                column.addEntry(new DBInteger(x));
            } else if (typeProp.equals(DBString.KEY)) {
                String x = entry.getAsJsonPrimitive().getAsString();
                if (x == "") {
                    x = null;
                }
                column.addEntry(new DBString(x));
            } else if (typeProp.equals(DBFloat.KEY)) {
                Float x = null;
                try {
                    x = entry.getAsJsonPrimitive().getAsFloat();
                } catch (NumberFormatException e) {
                }
                column.addEntry(new DBFloat(x));
            } else if (typeProp.equals(DBDate.KEY)) {
                //TODO
            }
        }
        return column;
    }

    private JsonArray addSerializedEntries(Column column, String typeProperty) {
        JsonArray entries = new JsonArray();
        for (DBDatatype entry : column.getEntries()) {
            JsonPrimitive obj = null;
            if (typeProperty.equals(DBInteger.KEY)) {
                if (entry == null || entry.getValue() == null || entry.getValue().equals("")) {
                    obj = new JsonPrimitive("");
                } else {
                    obj = new JsonPrimitive((Integer) entry.getValue());
                }
            } else if (typeProperty.equals(DBString.KEY)) {
                if (entry == null || entry.getValue() == null || entry.getValue().equals("")) {
                    obj = new JsonPrimitive("");
                } else {
                    obj = new JsonPrimitive((String) entry.getValue());
                }
            } else if (typeProperty.equals(DBFloat.KEY)) {
                if (entry == null || entry.getValue() == null || entry.getValue().equals("")) {
                    obj = new JsonPrimitive("");
                } else {
                    obj = new JsonPrimitive((Float) entry.getValue());
                }
            } else if (typeProperty.equals(DBDate.KEY)) {
                //TODO
            }
            entries.add(obj);
        }
        return entries;
    }
}
