package dbms.xml;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dbms.exception.DatabaseNotFoundException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.sqlparser.sqlInterpreter.Condition;
import dbms.util.Result;
import dbms.util.ResultSet;

public class TableParser {
	private static TableParser instance  = null;
	private static Transformer transformer = null;
	private static DocumentBuilder docBuilder = null;
	private static final String WORKSPACE_DIR =
			System.getProperty("user.home") + File.separator + "databases";
	private static final ResourceBundle CONSTANTS =
			ResourceBundle.getBundle("dbms.xml.Constants");

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
		transformer.setOutputProperty(
				CONSTANTS.getString("indentation"),
				CONSTANTS.getString("indentation.val"));
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
		File tableFile = new File(openDB(dbName), tableName
				+ CONSTANTS.getString("extension.xml"));
		if (tableFile.exists()) {
			throw new TableAlreadyCreatedException();
		}
		Document doc = docBuilder.newDocument();
		//Table element
		Element table = doc.createElement(CONSTANTS.getString("table.element"));
		table.setAttribute(CONSTANTS.getString("name.attr"), tableName);
		table.setAttribute(CONSTANTS.getString("db.attr"), dbName);
		table.setAttribute(CONSTANTS.getString("rows.attr"), "0");
		doc.appendChild(table);
		addColumns(doc, table, columns);
		transform(doc, tableFile, tableName);
	}
	
	public void dropDataBase(String dbName) throws DatabaseNotFoundException {
		File database = new File(WORKSPACE_DIR + File.separator + dbName);
		if (database.exists()) {
			String[] files = database.list();
			for (String fileName : files) {
				new File(database.getPath(), fileName).delete();
			}
			database.delete();
			
		} else {
			throw new DatabaseNotFoundException();
		}
	}
	/*
	 * Adds columns to the XML table when it's created.
	 * @param doc {@link Document} DOM Document
	 * @param table {@link Node} DOM Node representing table element
	 * @param columns Columns map indicating names and types.
	 * @throws SyntaxErrorException
	 */
	private void addColumns(Document doc, Node table,
			Map<String, Class> columns) throws SyntaxErrorException {
		for (Map.Entry<String, Class> col : columns.entrySet()) {
			String name = col.getKey();
			String type = ParserUtil.getClassName(col.getValue());
			if (type == null) {
				throw new SyntaxErrorException();
			}
			Element column = doc.createElement(
					CONSTANTS.getString("column.element"));
			column.setAttribute(
					CONSTANTS.getString("name.attr"), name);
			column.setAttribute(
					CONSTANTS.getString("type.attr"), type);
			table.appendChild(column);
		}
	}

	private File openDB(String dbName)
			throws DatabaseNotFoundException {
		File database = new File(WORKSPACE_DIR + File.separator + dbName);
		if (!database.exists()) {
			throw new DatabaseNotFoundException();
		}
		return database;
	}

	public ResultSet select(String dbName,
			String tableName, Condition condition, Collection<String> columns)
					throws TableNotFoundException, DatabaseNotFoundException, SyntaxErrorException {
		File tableFile = openTable(dbName, tableName);
		Document doc = null;
		try {
			doc = docBuilder.parse(tableFile);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		doc.getDocumentElement().normalize();
		NodeList tb = doc.getElementsByTagName("table");
		Node table = tb.item(0);
		int size = Integer.parseInt(table.
				getAttributes().getNamedItem(
						CONSTANTS.getString("rows.attr")).getTextContent());
		NodeList colList = doc.getElementsByTagName(CONSTANTS.getString(
				"column.element"));
		return getData(colList, condition, columns, size);
	}

	private ResultSet getData(NodeList colList, Condition condition,
			Collection<String> columns, int size) throws SyntaxErrorException {
		if (!ParserUtil.validateColumns(colList, columns)) {
			throw new SyntaxErrorException();
		}
		ResultSet res = new ResultSet();
		int i = 0;
		boolean reachedEnd = false;
		while (!reachedEnd) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			for (int j = 0; j < colList.getLength(); j++) {
				Node col = colList.item(j);
				if (i >= col.getChildNodes().getLength()) {
					reachedEnd = true;
					break;
				}
				String name = col.getAttributes().getNamedItem(
						CONSTANTS.getString("name.attr")).getTextContent();
				String type = col.getAttributes().getNamedItem(
						CONSTANTS.getString("type.attr")).getTextContent();
				Node row = col.getChildNodes().item(i);
				if (row instanceof Element == false) {
					continue;
				}
				Object value = null;
				if (type.equals("Integer")) {
					if (!row.getTextContent().equals("")) {
						value = Integer.parseInt(row.getTextContent());
					}
				} else if (type.equals("String")) {
					value = row.getTextContent();
				}
				rowMap.put(name, value);
			}
			if (condition == null
					|| Evaluator.getInstance().evaluate(
							rowMap, condition.getPostfix())) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				for (Map.Entry<String, Object> entry : rowMap.entrySet()) {
					if (columns == null || columns.contains(entry.getKey())) {
						resMap.put(entry.getKey(), entry.getValue());
					}
				}
				if (!resMap.isEmpty()) {
					res.add(new Result(resMap));
				}
			}
			i++;
		}
		return res;
	}

	private File openTable(String dbName, String tableName)
			throws DatabaseNotFoundException,
			TableNotFoundException {
		File tableFile = new File(openDB(dbName), tableName
				+ CONSTANTS.getString("extension.xml"));
		if (!tableFile.exists()) {
			throw new TableNotFoundException();
		}
		return tableFile;
	}

	public void insertIntoTable(String dbName, String tableName,
			Map<String, Object> entryMap)
			throws DatabaseNotFoundException,
			TableNotFoundException, SyntaxErrorException {
		File tableFile = openTable(dbName, tableName);
		Document doc = null;
		try {
			doc = docBuilder.parse(tableFile);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		addRow(doc, entryMap);
		transform(doc, tableFile, tableName);
	}

	/* Modifies the 'rows' attribute in the table
	 * root element, calls insertRowData after
	 * to append a row to each column*/
	private void addRow(Document doc,
			Map<String, Object> entryMap) throws SyntaxErrorException {
		NodeList tb = doc.getElementsByTagName("table");
		Node table = tb.item(0);
		Node rowsAttr = table
				.getAttributes().getNamedItem(
						CONSTANTS.getString("rows.attr"));
		int index = Integer.parseInt(
				rowsAttr.getTextContent());
		rowsAttr.setTextContent(Integer.toString(index + 1));
		NodeList cols = doc.getElementsByTagName(
				CONSTANTS.getString("column.element"));
		appendRowData(doc, index, cols, entryMap);
	}

	/*
	 * Appends a new row to each column in the
	 * table.
	 */
	private void appendRowData(Document doc, int index,
			NodeList cols, Map<String, Object> entryMap)
					throws SyntaxErrorException {
		if (!ParserUtil.validateColumnEntries(entryMap, cols)) {
			throw new SyntaxErrorException();
		}
		Map<Node, Boolean> inserted = new HashMap<Node, Boolean>();
		for (Map.Entry<String, Object> entry : entryMap.entrySet()) {
			Node col = ParserUtil
					.getColumnFromNodeList(entry.getKey(), cols);
			inserted.put(col, true);
			Node newRow =
					constructNewRow(doc, index, ParserUtil
							.getObjectClassName(entry.getValue()), entry.getValue());
			col.appendChild(newRow);
		}
		for (int i = 0; i < cols.getLength(); i++) {
			Node col = cols.item(i);
			if (!inserted.containsKey(col)) {
				String type = col.getAttributes()
						.getNamedItem(CONSTANTS.getString("type.attr")).getTextContent();
				Node newRow =
						constructNewRow(doc, index, type, null);
				col.appendChild(newRow);
			}
		}
	}

	/*
	 * Constructs a new row given its data.
	 */
	private Node constructNewRow(Document doc, int index,
			String type, Object value) throws SyntaxErrorException {
		Element row =
				doc.createElement(CONSTANTS.getString("row.element"));
		row.setAttribute(CONSTANTS.getString("index.val"), Integer.toString(index));
		String content = ParserUtil.getObjectStringValue(value, type);
		if (content == null) {
			throw new SyntaxErrorException();
		}
		row.setTextContent(content);
		return row;
	}

	private void transform(Document doc, File tableFile, String tableName) {
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		DOMImplementation domImpl = doc.getImplementation();
		DocumentType doctype = domImpl.createDocumentType("doctype",
			"-//Oberon//YOUR PUBLIC DOCTYPE//EN", tableName + CONSTANTS.getString("extensionDTD.schema"));
		transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, doctype.getPublicId());
		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
		DOMSource source = new DOMSource(doc);
		StreamResult result =
				new StreamResult(tableFile);
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	protected Document getDocument(String dbName, String tableName)
			throws DatabaseNotFoundException {
		File database = new File(WORKSPACE_DIR + File.separator + dbName);
		if (!database.exists()) {
			throw new DatabaseNotFoundException();
		}
		File schema = new File(database, tableName
				+ CONSTANTS.getString("extension.schema"));
		if (!schema.exists()) {
			return null;
		}
		Document doc = docBuilder.newDocument();
		return doc;
	}

	private NodeList getColumnsNodeList(String dbName, String tableName)
			throws DatabaseNotFoundException {
		Document doc = getDocument(dbName, tableName);
		if (doc == null) {
			return null;
		}
		NodeList cols = doc.getElementsByTagName(
				CONSTANTS.getString("column.element"));
		return cols;
	}

	protected Map<String, String> getColumns(String dbName, String tableName)
			throws DatabaseNotFoundException {
		NodeList colsList = getColumnsNodeList(dbName, tableName);
		return getColumns(colsList);
	}


	private Map<String, String> getColumns(NodeList colsList) {
		if (colsList == null) {
			return null;
		}
		Map<String, String> cols = new HashMap<String, String>();
		for (int i = 0; i < colsList.getLength(); i++) {
			Node col = colsList.item(i);
			String name = col.getAttributes()
					.getNamedItem(CONSTANTS.getString("name.attr"))
					.getTextContent();
			String type = col.getAttributes()
					.getNamedItem(CONSTANTS.getString("type.attr"))
					.getTextContent();
			cols.put(name, type);
		}
		return cols;
	}

	private boolean validateValues(NodeList columnList, Map<String, Object> values) {
		//	Validating syntax of values map.
		if (values != null) {
			if (!ParserUtil.validateColumnEntries(values, columnList)) {
				return false;
			}
		}
		return true;
	}

	private boolean validateColumns(NodeList columnList, Map<String, String> columns) {
		// For validating syntax of columns map.
		if (columns != null) {
			for (Map.Entry<String, String> entry : columns.entrySet()) {
				if (ParserUtil.getColumnFromNodeList(
						entry.getKey(), columnList) == null
						|| ParserUtil.getColumnFromNodeList(
						entry.getValue(), columnList) == null) {
							return false;
						}
					}
				}
		return true;
	}

	private void updateRows(NodeList colList, Condition condition,
			Map<String, Object> values, Map<String, String> columns)
			throws SyntaxErrorException {
		int i = 0;
		boolean reachedEnd = false;
		while (!reachedEnd) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			Collection<Node> rowEntries = new ArrayList<Node>();
			for (int j = 0; j < colList.getLength(); j++) {
				Node col = colList.item(j);
				if (i >= col.getChildNodes().getLength()) {
					reachedEnd = true;
					break;
				}
				String name = col.getAttributes().getNamedItem(
						CONSTANTS.getString("name.attr")).getTextContent();
				String type = col.getAttributes().getNamedItem(
						CONSTANTS.getString("type.attr")).getTextContent();
				Node row = col.getChildNodes().item(i);
				if (row instanceof Element == false) {
					continue;
				}
				Object value = null;
				if (type.equals("Integer")) {
					if (!row.getTextContent().equals("")) {
						value = Integer.parseInt(row.getTextContent());
					}
				} else if (type.equals("String")) {
					value = row.getTextContent();
				}
				rowMap.put(name, value);
				rowEntries.add(row);
			}
			if (condition == null
					|| Evaluator.getInstance().evaluate(
							rowMap, condition.getPostfix())) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				for (Map.Entry<String, Object> entry : rowMap.entrySet()) {
						resMap.put(entry.getKey(), entry.getValue());
				}
				if (!resMap.isEmpty()) {
					for (Node row : rowEntries) {
						String colName = row.getParentNode().getAttributes()
								.getNamedItem(CONSTANTS.getString("name.attr")).getTextContent();
						if (values.containsKey(colName)) {
							row.setTextContent(
									ParserUtil.getObjectStringValue(values.get(colName)));
						}
						if (columns.containsKey(colName)) {
							row.setTextContent(
									getRowDataUpdate(columns.get(colName), rowEntries));
						}
					}
				}
			}
			i++;
		}
	}

	private String getRowDataUpdate(String colName, Collection<Node> rowList) {
		for (Node row : rowList) {
			String colName2 = row.getParentNode().getAttributes()
					.getNamedItem(CONSTANTS.getString("name.attr")).getTextContent();
			if (colName.equals(colName2)) {
				return row.getTextContent();
			}
		}
		return null;
	}

	public void update(String dbName, String tableName,
			Map<String, Object> values, Map<String, String> columns,
			Condition condition) throws DatabaseNotFoundException,
					   TableNotFoundException, SyntaxErrorException {
		File tableFile = openTable(dbName, tableName);
		Document doc = null;
		try {
			doc = docBuilder.parse(tableFile);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		doc.getDocumentElement().normalize();
		NodeList colList = doc.getElementsByTagName(
				CONSTANTS.getString("column.element"));
		if (validateColumns(colList, columns)
				&& validateValues(colList, values)) {
			updateRows(colList, condition, values, columns);
		}
		transform(doc, tableFile, tableName);
	}

	public void dropTable(String tableName, String dbName) throws DatabaseNotFoundException {
		File tableFile = new File(openDB(dbName), tableName
				+ CONSTANTS.getString("extension.xml"));
		File xsdFile = new File(openDB(dbName), tableName
				+ CONSTANTS.getString("extension.schema"));
		File dtdFile = new File(openDB(dbName), tableName
				+ CONSTANTS.getString("extensionDTD.schema"));
		if (tableFile.exists()) {
			tableFile.delete();
			xsdFile.delete();
			dtdFile.delete();
		}
	}
	public void delete(String dbName, String tableName, Condition condition) throws DatabaseNotFoundException, TableNotFoundException, SyntaxErrorException {
		File tableFile = openTable(dbName, tableName);
		Document doc = null;
		try {
			doc = docBuilder.parse(tableFile);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		NodeList colList = doc.getElementsByTagName(
				CONSTANTS.getString("column.element"));
		Node table = doc.getElementsByTagName(
				CONSTANTS.getString("table.element")).item(0);
		deleteRows(table, colList, condition);
		doc.normalize();
		transform(doc, tableFile, tableName);
	}

	private void deleteRows(Node table,
			NodeList colList, Condition condition) throws SyntaxErrorException {
		int i = 0;
		int index = 0;
		boolean reachedEnd = false;
		while (!reachedEnd) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			Collection<Node> rowEntries = new ArrayList<Node>();
			for (int j = 0; j < colList.getLength(); j++) {
				Node col = colList.item(j);
				if (i >= col.getChildNodes().getLength()) {
					reachedEnd = true;
					break;
				}
				String name = col.getAttributes().getNamedItem(
						CONSTANTS.getString("name.attr")).getTextContent();
				String type = col.getAttributes().getNamedItem(
						CONSTANTS.getString("type.attr")).getTextContent();
				Node row = col.getChildNodes().item(i);
				if (row instanceof Element == false) {
					continue;
				}
				Object value = ParserUtil.getObjectFromString(
						type, row.getTextContent());
				rowMap.put(name, value);
				rowEntries.add(row);
			}
			if (!rowEntries.isEmpty()) {
				if (condition == null
						|| Evaluator.getInstance().evaluate(rowMap, condition.getPostfix())) {
					for (Node row : rowEntries) {
						row.getParentNode().removeChild(row);
					}
				} else {
					for (Node row : rowEntries) {
						row.getAttributes().getNamedItem(
								CONSTANTS.getString("index.val")).setTextContent(
										Integer.toString(index));
					}
					index++;
				}
			}
			i++;
		}
		table.getAttributes().getNamedItem(CONSTANTS.getString(
				"rows.attr")).setTextContent(Integer.toString(index));
	}
}