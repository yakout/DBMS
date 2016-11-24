package dbms.xml;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dbms.exception.DatabaseNotFoundException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;

public class TableParser {
	private static TableParser instance  = null;
	private static Transformer transformer = null;
	private static DocumentBuilder docBuilder = null;
	private static final String WORKSPACE_DIR =
			System.getProperty("user.home") + "\\databases";
	private static final String EXTENSION = ".xml";
	private static final String INDENTATION =
			"{http://xml.apache.org/xslt}indent-amount";
	private static final String INDENTATION_VAL = "4";
	private static final String TABLE_ELEMENT = "table";
	private static final String COLUMN_ELEMENT = "column";
	private static final String ROW_ELEMENT = "row";
	private static final String NAME_ATTR = "name";
	private static final String DB_ATTR = "database";
	private static final String ROWS_ATTR = "rows";
	private static final String TYPE_ATTR = "type";

	private TableParser() {
		try {
			docBuilder = DocumentBuilderFactory
					.newInstance().newDocumentBuilder();
			transformer = TransformerFactory
					.newInstance().newTransformer();
		} catch (TransformerConfigurationException
				| TransformerFactoryConfigurationError
				| ParserConfigurationException e) {
			e.printStackTrace();
		}
		transformer.setOutputProperty(OutputKeys.INDENT,
				"yes");
		transformer.setOutputProperty(INDENTATION,
				INDENTATION_VAL);
	}

	public static TableParser getInstance() {
		if (instance == null) {
			instance = new TableParser();
		}
		return instance;
	}

	public void createTable(String dbName, String tableName, Map<String, Class> columns)
			throws DatabaseNotFoundException, TableAlreadyCreatedException {
		File table = new File(openDB(dbName), tableName);
		if (table.exists()) {
			throw new TableAlreadyCreatedException();
		}
		Document doc = docBuilder.newDocument();
		//Table element
		Element root = doc.createElement(TABLE_ELEMENT);
		root.setAttribute(NAME_ATTR, tableName);
		root.setAttribute(DB_ATTR, dbName);
		doc.appendChild(root);
		DOMSource source = new DOMSource(doc);
		StreamResult result =
				new StreamResult(table);
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	public void insertIntoTable(String dbName, String tableName,
			Map<String, Object> entryMap)
			throws DatabaseNotFoundException, TableNotFoundException {
		File tableFile = new File(openDB(dbName), tableName);
		if (!tableFile.exists()) {
			throw new TableNotFoundException();
		}
		Document doc = null;
		try {
			doc = docBuilder.parse(tableFile);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		addRow(doc, entryMap);
	}

	private Element addRow(Document doc,
			Map<String, Object> entryMap) {
		//Changing rows attribute value in <table>
		Node rowsAttr = doc.getFirstChild()
				.getAttributes().getNamedItem(ROWS_ATTR);
		int index = Integer.parseInt(
				rowsAttr.getTextContent()) + 1;
		rowsAttr.setTextContent(Integer.toString(index));
		//Adding a row to <col>s
		Element row =
				doc.createElement(ROW_ELEMENT);
		NodeList cols = doc.getElementsByTagName(
				COLUMN_ELEMENT);
		for (int i = 0; i < cols.getLength(); i++) {
			Node col = cols.item(i);
			String name = col.getAttributes()
					.getNamedItem(NAME_ATTR).getTextContent();
			String type = col.getAttributes()
					.getNamedItem(TYPE_ATTR).getTextContent();
		}
		return null;
	}

	private File openDB(String dbName)
			throws DatabaseNotFoundException {
		File database = new File(WORKSPACE_DIR + "\\" + dbName);
		if (!database.exists()) {
			throw new DatabaseNotFoundException();
		}
		return database;
	}

	private HashMap<String, Class> getColumns(
			Document doc) {
		HashMap<String, Class> columns =
				new HashMap<>();
		NodeList cols = doc.getElementsByTagName(
				COLUMN_ELEMENT);
		for (int i = 0; i < cols.getLength(); i++) {
			Node col = cols.item(i);
			String name = col.getAttributes()
					.getNamedItem(NAME_ATTR).getTextContent();
			String type = col.getAttributes()
					.getNamedItem(TYPE_ATTR).getTextContent();
			if (type.equals("String")) {
				columns.put(name, String.class);
			} else if (type.equals("Integer")) {
				columns.put(name, Integer.class);
			}
		}
		return columns;
	}
}
