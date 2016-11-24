package dbms.xml;


import java.io.File;

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
import dbms.exception.TableAlreadyCreatedException;

public class TableParser {
	private static TableParser instance  = null;
	private static Transformer transformer = null;
	private static DocumentBuilder docBuilder = null;
	private static final String WORKSPACE_DIR =
			System.getProperty("user.home") + "\\databases";
	private static final String EXTENSION = ".xml";
	private static final String INDENTATION = "indent-number";
	private static final String TABLE_ELEMENT = "table";
	private static final String COLUMN_ELEMENT = "column";
	private static final String ROW_ELEMENT = "row";
	private static final String NAME_ATTR = "name";
	private static final String DB_ATTR = "database";
	private static final String TYPE_ATTR = "type";
	private static final int INDENTATION_VAL = 4;


	private TableParser() {
		try {
			docBuilder = DocumentBuilderFactory
					.newInstance().newDocumentBuilder();
			TransformerFactory tf = TransformerFactory
					.newInstance();
			tf.setAttribute(INDENTATION, INDENTATION_VAL);
			transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT,
					"yes");
		} catch (TransformerConfigurationException
				| TransformerFactoryConfigurationError
				| ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static TableParser getInstance() {
		if (instance == null) {
			instance = new TableParser();
		}
		return instance;
	}

	public void createTable(String dbName, String tableName)
			throws DatabaseNotFoundException, TableAlreadyCreatedException {
		File database = new File(WORKSPACE_DIR + "\\" + dbName);
		if (!database.exists()) {
			throw new DatabaseNotFoundException();
		}
		File table = new File(database, tableName + EXTENSION);
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
}
