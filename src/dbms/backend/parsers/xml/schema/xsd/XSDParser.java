package dbms.backend.parsers.xml.schema.xsd;

import dbms.backend.BackendController;
import dbms.exception.DatabaseNotFoundException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ResourceBundle;

public class XSDParser {
	private static XSDParser instance  = null;
	private static Transformer transformer = null;
	private static DocumentBuilder docBuilder = null;
	private static final String WORKSPACE_DIR =
			System.getProperty("user.home") + File.separator + "databases";
	private static final ResourceBundle CONSTANTS =
			ResourceBundle.getBundle("dbms.backend.parsers.xml.Constants");

	private XSDParser() {
		initialize();
	}

	public static XSDParser getInstance() {
		if (instance == null) {
			instance = new XSDParser();
		}
		return instance;
	}

	private void initialize() {
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
        transformer.setOutputProperty(CONSTANTS.getString("indentation"),
                CONSTANTS.getString("indentation.val"));

	}

	public void createSchema(String dbName, String tableName) throws
			 DatabaseNotFoundException {

		// File database = new File(WORKSPACE_DIR + File.separator + dbName);
		File database = new File(BackendController.getInstance().getCurrentDatabaseDir()
				+ File.separator + dbName);
		if (!database.exists()) {
			throw new DatabaseNotFoundException();
		}
		File schema = new File(database, tableName + CONSTANTS.getString("extension.schema"));
		if (schema.exists()) {
			return;
		}
		Document doc = docBuilder.newDocument();
		Element root = doc.createElement(CONSTANTS.getString("xs.schema"));
		doc.appendChild(root);
		setAllElements(root, doc);
		DOMSource source = new DOMSource(doc);
		StreamResult result =
				new StreamResult(schema);
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	private void setAllElements(Element root, Document doc) {
		setRootAttribute(root, doc);
		Element table = doc.createElement(CONSTANTS.getString("xs.element"));

		setTableAtrribute(table, doc);
		root.appendChild(table);

		Element cmplx = doc.createElement(CONSTANTS.getString("xs.complex"));
		table.appendChild(cmplx);

		Element sequence = doc.createElement(CONSTANTS.getString("xs.sequence"));
		cmplx.appendChild(sequence);

		Element column1 = doc.createElement(CONSTANTS.getString("xs.element"));
		setColumnAttribute(column1, doc);
		sequence.appendChild(column1);

		Element cmplx2 = doc.createElement(CONSTANTS.getString("xs.complex"));
		column1.appendChild(cmplx2);

		Element sequence2 = doc.createElement(CONSTANTS.getString("xs.sequence"));
		cmplx2.appendChild(sequence2);

		Element row = doc.createElement(CONSTANTS.getString("xs.element"));
		setRowAttribute(row, doc);
		sequence2.appendChild(row);

		Element cmplx3 = doc.createElement(CONSTANTS.getString("xs.complex"));
		row.appendChild(cmplx3);

		Element simpleContent = doc.createElement(CONSTANTS.getString("xs.simple"));
		cmplx3.appendChild(simpleContent);

		Element extension = doc.createElement(CONSTANTS.getString("xs.extension"));
		setExtensionAttribute(extension, doc);
		simpleContent.appendChild(extension);

		Element rowAttribute = doc.createElement(CONSTANTS.getString("xs.attr"));
		setAttrsToAttribute(rowAttribute, doc, CONSTANTS.getString("index.val"));
		extension.appendChild(rowAttribute);

		Element colAttr = doc.createElement(CONSTANTS.getString("xs.attr"));
		setAttrsToAttribute(colAttr, doc, CONSTANTS.getString("name.attr"));
		cmplx2.appendChild(colAttr);

		Element colAttr2 = doc.createElement(CONSTANTS.getString("xs.attr"));
		setAttrsToAttribute(colAttr2, doc, CONSTANTS.getString("type.attr"));
		cmplx2.appendChild(colAttr2);

		Element tableAttr1 = doc.createElement(CONSTANTS.getString("xs.attr"));
		setAttrsToAttribute(tableAttr1, doc, CONSTANTS.getString("name.attr"));
		cmplx.appendChild(tableAttr1);

		Element tableAttr2 = doc.createElement(CONSTANTS.getString("xs.attr"));
		setAttrsToAttribute(tableAttr2, doc, CONSTANTS.getString("db.attr"));
		cmplx.appendChild(tableAttr2);

		Element tableAttr3 = doc.createElement(CONSTANTS.getString("xs.attr"));
        setAttrsToAttribute(tableAttr3, doc, CONSTANTS.getString("rows.attr"));
        cmplx.appendChild(tableAttr3);
	}

	private void setAttrsToAttribute(Element attribute, Document doc, String attrName) {
		Attr type = doc.createAttribute(CONSTANTS.getString("type.attr"));

   		Attr name = doc.createAttribute(CONSTANTS.getString("name.attr"));
   		name.setValue(attrName);
   		attribute.setAttributeNode(name);

   		if (attrName.equals(CONSTANTS.getString("rows.attr"))) {
   			type.setValue(CONSTANTS.getString("int.type"));
   			Attr defaultVal = doc.createAttribute(CONSTANTS.getString("default.attr"));
   			defaultVal.setValue("0");
   			attribute.setAttributeNode(defaultVal);
       } else {
    	   	type.setValue(CONSTANTS.getString("string.type"));
    	   	Attr use = doc.createAttribute(CONSTANTS.getString("use.attr"));
    	   	use.setValue(CONSTANTS.getString("optional.val"));
    	   	attribute.setAttributeNode(use);
       }
   		attribute.setAttributeNode(type);
	}

	private void setExtensionAttribute(Element extension, Document doc) {
		Attr base = doc.createAttribute(CONSTANTS.getString("base.attr"));
		base.setValue(CONSTANTS.getString("string.type"));
		extension.setAttributeNode(base);
	}

	private void setColumnAttribute(Element column, Document doc) {
		Attr name = doc.createAttribute(CONSTANTS.getString("name.attr"));
		name.setValue(CONSTANTS.getString("column.element"));
		column.setAttributeNode(name);

		Attr maxOccurs = doc.createAttribute(CONSTANTS.getString("maxOccurs.attr"));
		maxOccurs.setValue(CONSTANTS.getString("unbounded.val"));
		column.setAttributeNode(maxOccurs);

		Attr minOccurs = doc.createAttribute(CONSTANTS.getString("minOccurs.attr"));
		minOccurs.setValue(CONSTANTS.getString("nothing"));
		column.setAttributeNode(minOccurs);
	}

	private void setRowAttribute(Element sequence, Document doc) {
		Attr name = doc.createAttribute(CONSTANTS.getString("name.attr"));
		name.setValue(CONSTANTS.getString("row.element"));
		sequence.setAttributeNode(name);

		Attr maxOccurs = doc.createAttribute(CONSTANTS.getString("maxOccurs.attr"));
		maxOccurs.setValue(CONSTANTS.getString("unbounded.val"));
		sequence.setAttributeNode(maxOccurs);

		Attr minOccurs = doc.createAttribute(CONSTANTS.getString("minOccurs.attr"));
		minOccurs.setValue(CONSTANTS.getString("nothing"));
		sequence.setAttributeNode(minOccurs);
	}

	private void setRootAttribute(Element root, Document doc) {
		Attr xmlns = doc.createAttribute(CONSTANTS.getString("xmlns.attr"));
		xmlns.setValue(CONSTANTS.getString("xmlns.val"));
		root.setAttributeNode(xmlns);
		Attr eFormDefault = doc.createAttribute(CONSTANTS.getString("elementFormDefault.attr"));
		eFormDefault.setValue(CONSTANTS.getString("elementFormDefault.val"));
		root.setAttributeNode(eFormDefault);
		Attr formAttr = doc.createAttribute(CONSTANTS.getString("formDefault.attr"));
		formAttr.setValue(CONSTANTS.getString("formDefault.val"));
		root.setAttributeNode(formAttr);
	}

	private void setTableAtrribute(Element table, Document doc) {
		Attr name = doc.createAttribute(CONSTANTS.getString("name.attr"));
		name.setValue(CONSTANTS.getString("table.element"));
		table.setAttributeNode(name);
	}
}