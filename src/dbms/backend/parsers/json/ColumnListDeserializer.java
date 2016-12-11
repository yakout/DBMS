package dbms.backend.parsers.json;

import com.google.gson.*;
import dbms.util.Column;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ColumnListDeserializer implements JsonDeserializer<List<Column>> {
    @Override
    public List<Column> deserialize(JsonElement jsonElement, Type type,
                                    JsonDeserializationContext jdc)
            throws JsonParseException {
        Gson gson = new Gson();
        JsonArray colArray = jsonElement.getAsJsonArray();
        List<Column> columns = new ArrayList<Column>();
        for (JsonElement je : colArray) {
            columns.add((Column) jdc.deserialize(je, Column.class));
        }
        return columns;
    }
}
