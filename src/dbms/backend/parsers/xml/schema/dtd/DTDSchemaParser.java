package dbms.backend.parsers.xml.schema.dtd;

import dbms.backend.BackendController;
import dbms.exception.DatabaseNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

/**
 * Generates a DTD schema file for XML database.
 */
public class DTDSchemaParser {

    /**
     * Resource bundle to constants.
     */
    private static final ResourceBundle CONSTANTS =
            ResourceBundle.getBundle("dbms.backend.parsers.xml.Constants");

    /**
     * Static singleton instance.
     */
    private static DTDSchemaParser instance = null;

    /**
     * TODO
     */
    private PrintWriter out;

    private DTDSchemaParser() {
    }

    /**
     * Gets static singleton instance.
     * @return static singleton instance.
     */
    public static DTDSchemaParser getInstance() {
        if (instance == null) {
            instance = new DTDSchemaParser();
        }
        return instance;
    }

    /**
     * Creates the main flow to DTD file.
     * Writes element and attributes to DTD file.
     * @param dbName holds the name of the current database.
     * @param tableName holds the name of the table.
     * @throws DatabaseNotFoundException if no such database with the given
     * name is found.
     */
    public void createDTDSchema(String dbName, String tableName)
            throws DatabaseNotFoundException {
        // File database = new File(WORKSPACE_DIR + File.separator + dbName);
        File database = new File(BackendController.getInstance()
                .getCurrentDatabaseDir() + File.separator + dbName);
        if (!database.exists()) {
            throw new DatabaseNotFoundException();
        }
        File schema = new File(database, tableName
                + CONSTANTS.getString("extensionDTD.schema"));
        if (schema.exists()) {
            return;
        }
        try {
            out = new PrintWriter(schema);
            writeElements(schema, out);
            writeAttributes(schema, out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void writeElements(File schema, PrintWriter pWriter) throws
            FileNotFoundException {

        DTDElementCreator.createElement(
                CONSTANTS.getString("table.element"),
                CONSTANTS.getString("optional.col"), pWriter);
        DTDElementCreator.createElement(
                CONSTANTS.getString("column.element"),
                CONSTANTS.getString("optional.row"), pWriter);
        DTDElementCreator.createElement(
                CONSTANTS.getString("row.element"),
                CONSTANTS.getString("pc.data"), pWriter);
    }

    private void writeAttributes(File schema, PrintWriter pWriter) throws
            FileNotFoundException {
        DTDAttributeCreator.createElement(CONSTANTS.getString("table.element"),
                CONSTANTS.getString("db.attr"), CONSTANTS.getString("req"
                        + ".type"), pWriter);
        DTDAttributeCreator.createElement(CONSTANTS.getString("table.element"),
                CONSTANTS.getString("name.attr"), CONSTANTS.getString("req"
                        + ".type"), pWriter);
        DTDAttributeCreator.createElement(CONSTANTS.getString("table.element"),
                CONSTANTS.getString("rows.attr"), "\"0\"", pWriter);
        DTDAttributeCreator.createElement(CONSTANTS.getString("column.element"),
                CONSTANTS.getString("name.attr"), CONSTANTS.getString("req"
                        + ".type"), pWriter);
        DTDAttributeCreator.createElement(CONSTANTS.getString("column.element"),
                CONSTANTS.getString("type.attr"), CONSTANTS.getString("req"
                        + ".type"), pWriter);
        DTDAttributeCreator.createElement(CONSTANTS.getString("row.element"),
                CONSTANTS.getString("index.val"), CONSTANTS.getString("req"
                        + ".type"), pWriter);

    }
}
