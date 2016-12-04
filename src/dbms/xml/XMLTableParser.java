package dbms.xml;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dbms.datatypes.DatatypeFactory;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.TableNotFoundException;
import dbms.util.Column;
import dbms.util.Table;

public class XMLTableParser {
	private static XMLTableParser instance = null;
	private static DocumentBuilder docBuilder = null;
	private static final String WORKSPACE_DIR =
			System.getProperty("user.home") + File.separator + "databases";
	private static final ResourceBundle CONSTANTS =
			ResourceBundle.getBundle("dbms.xml.Constants");

	private XMLTableParser() {
		try {
			docBuilder = DocumentBuilderFactory
				.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static XMLTableParser getInstance() {
		if (instance == null) {
			instance = new XMLTableParser();
		}
		return instance;
	}

	public void loadTable(Table table) throws TableNotFoundException, DatabaseNotFoundException {
		File tableFile = openTable(table.getDBName(), table.getName());
		Document doc = null;
		try {
			doc = docBuilder.parse(tableFile);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		validateDB(doc, table.getDBName());
		doc.getDocumentElement().normalize();
		parseDataToTable(table, doc);
	}

	private void parseDataToTable(Table table, Document doc) {
		NodeList colList = doc.getElementsByTagName(
				CONSTANTS.getString("column.element"));
		for (int i = 0; i < colList.getLength(); i++) {
			Node colNode = colList.item(i);
			Column col = new Column();
			String colName = colNode.getAttributes().getNamedItem(
					CONSTANTS.getString("name.attr")).getTextContent();
			String colType = colNode.getAttributes().getNamedItem(
					CONSTANTS.getString("type.attr")).getTextContent();
			col.setName(colName);
			col.setType(DatatypeFactory.getFactory()
					.getRegisteredDatatype(colType));
			for (int j = 0; j < colNode.getChildNodes().getLength(); j++) {
				Node row = colNode.getChildNodes().item(j);
				if (row instanceof Element == false) {
					continue;
				}
				Object entry = DatatypeFactory.getFactory().toObj(
						row.getTextContent(), colType);
				col.addEntry(entry);
			}
			table.addColumn(col);
		}
	}

	private File openTable(String dbName, String tableName)
			throws TableNotFoundException, DatabaseNotFoundException {
		File tableFile = new File(openDB(dbName), tableName
				+ CONSTANTS.getString("extension.xml"));
		if (!tableFile.exists()) {
			throw new TableNotFoundException();
		}
		return tableFile;
	}

	private File openDB(String dbName)
			throws DatabaseNotFoundException {
		File database = new File(WORKSPACE_DIR + File.separator + dbName);
		if (!database.exists()) {
			throw new DatabaseNotFoundException();
		}
		return database;
	}

	private void validateDB(Document doc, String dbName)
			throws TableNotFoundException {
		String db = doc.getElementsByTagName(CONSTANTS.getString(
				"table.element")).item(0).getAttributes().getNamedItem(
						CONSTANTS.getString("db.attr")).getTextContent();
		if (!db.equals(dbName)) {
			throw new TableNotFoundException();
		}
	}
}
