package dbms.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import dbms.exception.DatabaseNotFoundException;

public class DTDSchemaParser {
	private static DTDSchemaParser instance = null;
	private static final String WORKSPACE_DIR =
			System.getProperty("user.home") + "\\databases";
	private static final ResourceBundle CONSTANTS =
			ResourceBundle.getBundle("dbms.xml.Constants");
	private PrintWriter out;
	
	private DTDSchemaParser() {}
	
	public static DTDSchemaParser getInstance(){
		if (instance == null) {
			instance = new DTDSchemaParser();
		}
		return instance;
	}

	public void createDTDSchema(String dbName, String tableName)
		throws DatabaseNotFoundException, FileNotFoundException {
		File database = new File(WORKSPACE_DIR + "\\" + dbName);
		if (!database.exists()) {
			throw new DatabaseNotFoundException();
		}
		File schema = new File(database, tableName 
				+ CONSTANTS.getString("extensionDTD.schema"));
		if (schema.exists()) {
			return;
		}
		out = new PrintWriter(schema);
		RootCreator.createRoot(CONSTANTS.getString("table.element"),	
				out);
		writeElements(schema, out);
		writeAttributes(schema, out);
		RootCreator.terminateFile(out);
	}
	
	private void writeElements(File schema, PrintWriter pWriter) throws FileNotFoundException {
		
		DTDElementCreator.createElement(
				CONSTANTS.getString("table.element"),
				CONSTANTS.getString("optional.col"), pWriter);
		DTDElementCreator.createElement(
				CONSTANTS.getString("column.element"),
				CONSTANTS.getString("optional.row"), pWriter);
		DTDElementCreator.createElement(
				CONSTANTS.getString("row.element"),
				CONSTANTS.getString("c.data"), pWriter);
	}
	private void writeAttributes(File schema, PrintWriter pWriter) throws FileNotFoundException {
		DTDAttributeCreator.createElement(CONSTANTS.getString("table.element"),
				CONSTANTS.getString("db.attr"), CONSTANTS.getString("req.type"), pWriter);
		DTDAttributeCreator.createElement(CONSTANTS.getString("table.element"),
				CONSTANTS.getString("name.attr"), CONSTANTS.getString("req.type"), pWriter);
		DTDAttributeCreator.createElement(CONSTANTS.getString("table.element"),
				CONSTANTS.getString("rows.attr"), "\"0\"", pWriter);
		DTDAttributeCreator.createElement(CONSTANTS.getString("column.element"),
				CONSTANTS.getString("name.attr"), CONSTANTS.getString("req.type"), pWriter);
		DTDAttributeCreator.createElement(CONSTANTS.getString("column.element"),
				CONSTANTS.getString("type.attr"), CONSTANTS.getString("req.type"), pWriter);
		DTDAttributeCreator.createElement(CONSTANTS.getString("row.element"),
				CONSTANTS.getString("index.val"), CONSTANTS.getString("req.type"), pWriter);
		
	}
}
