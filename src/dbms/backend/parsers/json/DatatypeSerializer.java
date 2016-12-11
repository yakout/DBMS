package dbms.backend.parsers.json;

import com.google.gson.*;
import dbms.datatypes.DBDatatype;

import java.lang.reflect.Type;

public class DatatypeSerializer implements JsonSerializer<DBDatatype>,
        JsonDeserializer<DBDatatype> {
    @Override
    public JsonElement serialize(DBDatatype dbDatatype, Type type,
                                 JsonSerializationContext jsc) {
//        JsonElement element = null;
//        if (dbDatatype.getKey().equals("Integer")) {
//            element = new JsonPrimitive((Integer) dbDatatype.getValue());
//        } else if (dbDatatype.getKey().equals("String")) {
//            element = new JsonPrimitive((String) dbDatatype.getValue());
//        }
//        return element;
        return null;
    }

    @Override
    public DBDatatype deserialize(JsonElement jsonElement, Type type,
                                  JsonDeserializationContext jdc) throws JsonParseException {
//        JsonPrimitive jp = jsonElement.getAsJsonPrimitive();
//        if (jp.isNumber()) {
//            return new DBInteger(jp.getAsInt());
//        } else if (jp.isString()) {
//            return new DBString(jp.getAsString());
//        }
        return null;
    }
}
