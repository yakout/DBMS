package dbms.xml;


import java.io.File;
import java.io.IOException;
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
import dbms.exception.IncorrectDataEntryException;
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

	/**
	 * Gets the static instance of TableParser.
	 * @return {@link TableParser} static global instance.
	 */
	protected static TableParser getInstance() {
		if (instance == null) {
			instance = new TableParser();
		}
		return instance;
	}

	/**
	 * Creates a new XML table inside a given database.
	 * @param dbName Name of the given database.
	 * @param tableName Name of the given table.
	 * @param columns {@link Map} between names of given columns and
	 * their data type.
	 * @throws DatabaseNotFoundException
	 * @throws TableAlreadyCreatedException
	 * @throws IncorrectDataEntryException
	 */
	protected void createTable(String dbName, String tableName,
			Map<String, Class> columns)
			throws DatabaseNotFoundException,
			TableAlreadyCreatedException, IncorrectDataEntryException {
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

	/**
	 * Deletes a given database from its directory.
	 * @param dbName Name of a given database
	 * @throws DatabaseNotFoundException
	 */
	public void dropDatabase(String dbName) throws DatabaseNotFoundException {
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

	/**
	 * Inserts new data into table.
	 * @param dbName Name of database.
	 * @param tableName Name of table inside database.
	 * @param entryMap {@link Map} between column names
	 * and objects to be inserted.
	 * @throws DatabaseNotFoundException
	 * @throws TableNotFoundException
	 * @throws IncorrectDataEntryException
	 */
	public void insertIntoTable(String dbName, String tableName,
			Map<String, Object> entryMap)
			throws DatabaseNotFoundException,
			TableNotFoundException, IncorrectDataEntryException {
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

	/**
	 * Selects data from database given a certain condition,
	 * the result is stored after in a {@link ResultSet}.
	 * @param dbName Name of database.
	 * @param tableName Name of table inside database.
	 * @param condition {@link Condition} condition for data selection,
	 * can be null.
	 * @param columns {@link Collection<String>} columns to select from.
	 * @return {@link ResultSet} Set of returned data.
	 * @throws TableNotFoundException
	 * @throws DatabaseNotFoundException
	 * @throws IncorrectDataEntryException
	 * @throws SyntaxErrorException
	 */
	public ResultSet select(String dbName,
			String tableName, Condition condition, Collection<String> columns)
					throws TableNotFoundException, DatabaseNotFoundException, IncorrectDataEntryException, SyntaxErrorException {
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

	/**
	 * Updates data inside database given a certain condition.
	 * @param dbName Name of database.
	 * @param tableName Name of table.
	 * @param values {@link Map} between column names and
	 * objects to be updated inside database.
	 * @param columns {@link Map} between columns to be updated
	 * with values of other columns.
	 * @param condition {@link Condition} condition for data updating,
	 * can be null.
	 * @throws DatabaseNotFoundException
	 * @throws TableNotFoundException
	 * @throws SyntaxErrorException
	 * @throws IncorrectDataEntryException
	 */
	public void update(String dbName, String tableName,
			Map<String, Object> values, Map<String, String> columns,
			Condition condition) throws DatabaseNotFoundException,
					   TableNotFoundException, SyntaxErrorException, IncorrectDataEntryException {
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
			updateRows(colList, condition, values, columns,
					ParserUtil.getColsNodeListMap(colList));
		}
		transform(doc, tableFile, tableName);
	}

	/**
	 * Drops table from database.
	 * @param tableName Name of table.
	 * @param dbName Name of database.
	 * @throws DatabaseNotFoundException
	 */
	public void dropTable(String tableName, String dbName) throws DatabaseNotFoundException {
		File tableFile = new File(openDB(dbName), tableName
				+ CONSTANTS.getString("extension.xml"));
		File xsdFile = new File(openDB(dbName), tableName
				+ CONSTANTS.getString("extension.schema"));
		File dtdFile = new File(openDB(dbName), tableName
				+ CONSTANTS.getString("extensionDTD.schema"));
		if (tableFile.exists()) {
			tableFile.delete();
		}
		if (xsdFile.exists()) {
			xsdFile.delete();
		}
		if (dtdFile.exists()) {
			dtdFile.delete();
		}
	}

	/**
	 * Deletes data from database given a certain condition.
	 * @param dbName Name of database.
	 * @param tableName Name of table.
	 * @param condition {@link Condition} condition for data deletion,
	 * can be null.
	 * @throws DatabaseNotFoundException
	 * @throws TableNotFoundException
	 * @throws SyntaxErrorException
	 * @throws IncorrectDataEntryException
	 */
	public void delete(String dbName, String tableName, Condition condition)
			throws DatabaseNotFoundException,
			TableNotFoundException, SyntaxErrorException, IncorrectDataEntryException {
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

	/*
	 * Adds columns to the XML table when it's created.
	 * @param doc {@link Document} DOM Document
	 * @param table {@link Node} DOM Node representing table element
	 * @param columns Columns map indicating names and types.
	 * @throws SyntaxErrorException
	 */
	private void addColumns(Document doc, Node table,
			Map<String, Class> columns) throws IncorrectDataEntryException {
		for (Map.Entry<String, Class> col : columns.entrySet()) {
			String name = col.getKey();
			String type = ParserUtil.getClassName(col.getValue());
			if (type == null) {
				throw new IncorrectDataEntryException();
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

	private ResultSet getData(NodeList colList, Condition condition,
			Collection<String> columns, int size) throws IncorrectDataEntryException, SyntaxErrorException {
		if (!ParserUtil.validateColumns(colList, columns)) {
			throw new IncorrectDataEntryException();
		}
		Map<String, String> cols = ParserUtil.getColsNodeListMap(colList);
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
							rowMap, condition.getPostfix(), cols)) {
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

	/* Modifies the 'rows' attribute in the table
	 * root element, calls insertRowData after
	 * to append a row to each column*/
	private void addRow(Document doc,
			Map<String, Object> entryMap) throws IncorrectDataEntryException {
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
					throws IncorrectDataEntryException {
		if (!ParserUtil.validateColumnEntries(entryMap, cols)) {
			throw new IncorrectDataEntryException();
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
			String type, Object value) throws IncorrectDataEntryException {
		Element row =
				doc.createElement(CONSTANTS.getString("row.element"));
		row.setAttribute(CONSTANTS.getString("index.val"), Integer.toString(index));
		String content = ParserUtil.getObjectStringValue(value, type);
		if (content == null) {
			throw new IncorrectDataEntryException();
		}
		row.setTextContent(content);
		return row;
	}

	private void transform(Document doc, File tableFile, String tableName) {
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		DOMImplementation domImpl = doc.getImplementation();
		DocumentType doctype = domImpl.createDocumentType("doctype",
			"-//DBMS//DBMS v1.0//EN", tableName + CONSTANTS.getString("extensionDTD.schema"));
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
			Map<String, Object> values, Map<String, String> columns,
			Map<String, String> colMap)
			throws SyntaxErrorException, IncorrectDataEntryException {
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
			if (!rowMap.isEmpty()) {
				if (condition == null
						|| Evaluator.getInstance().evaluate(
								rowMap, condition.getPostfix(), colMap)) {
					for (Node row : rowEntries) {
						String colName = row.getParentNode().getAttributes()
								.getNamedItem(CONSTANTS.getString("name.attr"))
								.getTextContent();
						if (values != null) {
							if (values.containsKey(colName)) {
								row.setTextContent(ParserUtil
										.getObjectStringValue(values.get(colName)));
							}
						}
						if (columns != null) {
							if (columns.containsKey(colName)) {
								row.setTextContent(
										getRowDataUpdate(columns.get(colName), rowEntries));
							}
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

	private void deleteRows(Node table,
			NodeList colList, Condition condition) throws SyntaxErrorException, IncorrectDataEntryException {
		Map<String, String> cols = ParserUtil.getColsNodeListMap(colList);
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
						|| Evaluator.getInstance().evaluate(rowMap, condition.getPostfix(), cols)) {
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