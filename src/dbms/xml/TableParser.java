package dbms.xml;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dbms.exception.DatabaseNotFoundException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.util.Result;
import dbms.util.ResultSet;

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
	private static final String COLUMN_ELEMENT = "col";
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

	public void createTable(String dbName, String tableName,
			Map<String, Class> columns)
			throws DatabaseNotFoundException,
			TableAlreadyCreatedException, SyntaxErrorException {
		File tableFile = new File(openDB(dbName), tableName + EXTENSION);
		if (tableFile.exists()) {
			throw new TableAlreadyCreatedException();
		}
		Document doc = docBuilder.newDocument();
		//Table element
		Element table = doc.createElement(TABLE_ELEMENT);
		table.setAttribute(NAME_ATTR, tableName);
		table.setAttribute(DB_ATTR, dbName);
		table.setAttribute(ROWS_ATTR, "0");
		doc.appendChild(table);
		addColumns(doc, table, columns);
		transform(doc, tableFile);
	}
	
	public ResultSet selectAll(String dbName, String tableName)
			throws DatabaseNotFoundException, TableNotFoundException {
		File tableFile = new File(openDB(dbName), tableName + EXTENSION);
		if (!tableFile.exists()) {
			throw new TableNotFoundException();
		}
		Document doc = null;
		try {
			doc = docBuilder.parse(tableFile);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		doc.getDocumentElement().normalize();
		ResultSet resultSet = new ResultSet();
		NodeList rowList = doc.getElementsByTagName(ROW_ELEMENT);
		int size = Integer.parseInt(doc.getFirstChild().
				getAttributes().getNamedItem(ROWS_ATTR).getTextContent());
		System.out.println(size);
		Result[] rows = new Result[size];
		for (int i = 0; i < rowList.getLength(); i++) {
			Node currChild = rowList.item(i);
			if (currChild.getNodeType() == Node.ELEMENT_NODE) {
				Node parentNode = currChild.getParentNode();
				Element parentNodeElement = (Element)parentNode;
				Element e = (Element)currChild;
				int index = Integer.parseInt(e.getAttribute(INDEX_ATTR));
				if (rows[index] == null) {
					rows[index] = new Result();
				}
				rows[index].add(parentNodeElement.getAttribute(NAME_ATTR),
						currChild.getTextContent().toString());
			}
		}
		for (int i = 0; i < size; i++) {
			resultSet.add(rows[i]);
		}
		return resultSet;
	}

	private void addColumns(Document doc, Node table,
			Map<String, Class> columns) throws SyntaxErrorException {
		for (Map.Entry<String, Class> col : columns.entrySet()) {
			String name = col.getKey();
			String type = getClassName(col.getValue());
			if (type == null) {
				throw new SyntaxErrorException();
			}
			Element column = doc.createElement(COLUMN_ELEMENT);
			column.setAttribute(NAME_ATTR, name);
			column.setAttribute(TYPE_ATTR, type);
			table.appendChild(column);
		}
	}

	public void insertIntoTable(String dbName, String tableName,
			Map<String, Object> entryMap)
			throws DatabaseNotFoundException,
			TableNotFoundException, SyntaxErrorException {
		File tableFile = new File(openDB(dbName), tableName + EXTENSION);
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
		transform(doc, tableFile);
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

	private String getObjectStringValue(Object o, String type) {
		if (o instanceof Integer
				&& type.equals("Integer")) {
			return ((Integer) o).toString();
		}
		if (o instanceof String
				&& type.equals("String")) {
			return ((String) o);
		}
		return null;
	}

	private String getClassName(Class c) {
		if (c.equals(Integer.class)) {
			return "Integer";
		}
		if (c.equals(String.class)) {
			return "String";
		}
		return null;
	}

	private void transform(Document doc, File tableFile) {
		DOMSource source = new DOMSource(doc);
		StreamResult result =
				new StreamResult(tableFile);
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}
