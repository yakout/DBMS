package dbms.xml;


import java.io.File;
import java.io.IOException;
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
import dbms.exception.SyntaxErrorException;
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
	private static final String INDEX_ATTR = "index";

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
			throws DatabaseNotFoundException, TableNotFoundException, SyntaxErrorException {
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

	private void addRow(Document doc,
			Map<String, Object> entryMap) throws SyntaxErrorException {
		//Changing rows attribute value in <table>
		Node rowsAttr = doc.getFirstChild()
				.getAttributes().getNamedItem(ROWS_ATTR);
		int index = Integer.parseInt(
				rowsAttr.getTextContent());
		rowsAttr.setTextContent(Integer.toString(index + 1));
		//Adding a row to <col>s
		NodeList cols = doc.getElementsByTagName(
				COLUMN_ELEMENT);
		for (int i = 0; i < cols.getLength(); i++) {
			Node col = cols.item(i);
			String name = col.getAttributes()
					.getNamedItem(NAME_ATTR).getTextContent();
			String type = col.getAttributes()
					.getNamedItem(TYPE_ATTR).getTextContent();
			Object value = entryMap.get(name);
			if (value == null) {
				throw new SyntaxErrorException();
			}
			Node newRow =
					getNewRowContent(doc, index, type, value);
			col.appendChild(newRow);
		}
	}

	private Node getNewRowContent(Document doc, int index,
			String type, Object value) throws SyntaxErrorException {
		Element row =
				doc.createElement(ROW_ELEMENT);
		row.setAttribute(INDEX_ATTR, Integer.toString(index));
		String content = getObjectStringValue(value, type);
		if (content == null) {
			throw new SyntaxErrorException();
		}
		row.setTextContent(content);
		return row;
	}

	private File openDB(String dbName)
			throws DatabaseNotFoundException {
		File database = new File(WORKSPACE_DIR + "\\" + dbName);
		if (!database.exists()) {
			throw new DatabaseNotFoundException();
		}
		return database;
	}

	private String getObjectStringValue(Object x, String type) {
		if (x instanceof Integer
				&& type.equals("Integer")) {
			return ((Integer) x).toString();
		}
		if (x instanceof String
				&& type.equals("String")) {
			return ((String) x);
		}
		return null;
	}
}
