package dbms.xml;

import java.io.File;
import java.util.HashMap;

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

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import dbms.exception.DatabaseNotFoundException;
import dbms.exception.TableAlreadyCreatedException;

public class SchemaParser {
	private static SchemaParser instance  = null;
	private static Transformer transformer = null;
	private static DocumentBuilder docBuilder = null;
	private static final String EXTENSION = ".xsd";
	private static final String WORKSPACE_DIR =
			System.getProperty("user.home") + "\\databases";
	/** XS nodes.*/
	private static final String XS_SCHEMA = "xs:schema";
	private static final String XS_ELEMENT = "xs:element";
	private static final String XS_EXTENSION = "xs:extension";
	private static final String XS_COMPLEX = "xs:complexType";
	private static final String XS_SEQUENCE = "xs:sequence";
	private static final String XS_SIMPLE = "xs:simpleContent";
	private static final String XS_ATTR = "xs:attribute";

	private static final String NOTHING = "0";
	/** XS types.*/
	private static final String STRING_TYPE = "xs:string";
	private static final String INT_TYPE = "xs:integer";
	/** Tag elements.*/
	private static final String TABLE_ELEMENT = "table";
	private static final String COLUMN_ELEMENT = "column";
	private static final String ROW_ELEMENT = "row";
	/** XS nodes.*/
	private static final String FORMDEFAULT_ATTR = "attributeFormDefault";
	private static final String ELEMENTFORMDEFAULT_ATTR = "elementFormDefault";
	private static final String XMLNS_ATTR = "xmlns:xs";
	private static final String NAME_ATTR = "name";
	private static final String DB_ATTR = "database";
	private static final String TYPE_ATTR = "type";
	private static final String USE_ATTR = "use";
	private static final String MAXOCCURS_ATTR = "maxOccurs";
	private static final String MINOCCURS_ATTR = "minOccurs";
	private static final String BASE_ATTR = "base";
	/** Attribute values.*/
	private static final String FORMDEFAULT_VAL = "unqualified";
	private static final String ELEMENTFORMDEFAULT_VAL = "qualified";
	private static final String XMLNS_VAL = "http://www.w3.org/2001/XMLSchema";
	private static final String UNBOUNDED_VAL = "unbounded";
	private static final String OPTIONAL_VAL = "optional";
	private static final String INDEX_VAL = "index";
	/** Indentations.*/
	private static final String INDENTATION_VAL = "4";
	private static final String INDENTATION =
            "{http://xml.apache.org/xslt}indent-amount";
	private static final String TABLE = null;
//	private static final String MAXOCCURS_ATTR = null;

	private SchemaParser() {
		initialize();
	}
	
	public static SchemaParser getInstance() {
		if (instance == null) {
			instance = new SchemaParser();
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
        transformer.setOutputProperty(INDENTATION,
                INDENTATION_VAL);
		
	}

	public void createSchema(String dbName, String tableName) throws
			 DatabaseNotFoundException {	
		
		File database = new File(WORKSPACE_DIR + "\\" + dbName);
		if (!database.exists()) {
			throw new DatabaseNotFoundException();
		}
		File schema = new File(database, tableName + EXTENSION);
		if (schema.exists()) {
			return;
		}
		Document doc = docBuilder.newDocument();
		Element root = doc.createElement(XS_SCHEMA);
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
		Element table = doc.createElement(XS_ELEMENT);
		
		setTableAtrribute(table, doc);
		root.appendChild(table);
		
		Element cmplx = doc.createElement(XS_COMPLEX);
		table.appendChild(cmplx);
		
		Element sequence = doc.createElement(XS_SEQUENCE);
		cmplx.appendChild(sequence);
		
		
		Element column1 = doc.createElement(XS_ELEMENT);
		setColumnAttribute(column1, doc);
		sequence.appendChild(column1);
		
		
		Element cmplx2 = doc.createElement(XS_COMPLEX);
		column1.appendChild(cmplx2);
		
		Element sequence2 = doc.createElement(XS_SEQUENCE);
		cmplx2.appendChild(sequence2);
		
		Element row = doc.createElement(XS_ELEMENT);
		setRowAttribute(row, doc);
		sequence2.appendChild(row);
		
		Element cmplx3 = doc.createElement(XS_COMPLEX);
		row.appendChild(cmplx3);
		
		Element simpleContent = doc.createElement(XS_SIMPLE);
		cmplx3.appendChild(simpleContent);
		
		Element extension = doc.createElement(XS_EXTENSION);
		setExtensionAttribute(extension, doc);
		simpleContent.appendChild(extension);
		
		Element rowAttribute = doc.createElement(XS_ATTR);
		setAttrsToAttribute(rowAttribute, doc, INDEX_VAL);
		extension.appendChild(rowAttribute);
		
		Element colAttr = doc.createElement(XS_ATTR);
		setAttrsToAttribute(colAttr, doc, NAME_ATTR);
		cmplx2.appendChild(colAttr);
		
		Element colAttr2 = doc.createElement(XS_ATTR);
		setAttrsToAttribute(colAttr2, doc, TYPE_ATTR);
		cmplx2.appendChild(colAttr2);
		
		Element tableAttr1 = doc.createElement(XS_ATTR);
		setAttrsToAttribute(tableAttr1, doc, NAME_ATTR);
		cmplx.appendChild(tableAttr1);
		
		Element tableAttr2 = doc.createElement(XS_ATTR);
		setAttrsToAttribute(tableAttr2, doc, DB_ATTR);
		cmplx.appendChild(tableAttr2);	
	}
	
	private void setAttrsToAttribute(Element attribute, Document doc, String attrName) {
		Attr type = doc.createAttribute(TYPE_ATTR);
		type.setValue(STRING_TYPE);
		attribute.setAttributeNode(type);
		
		Attr name = doc.createAttribute(NAME_ATTR);
		name.setValue(attrName);
		attribute.setAttributeNode(name);
		
		Attr use = doc.createAttribute(USE_ATTR);
		use.setValue(OPTIONAL_VAL);
		attribute.setAttributeNode(use);
	}
	
	private void setExtensionAttribute(Element extension, Document doc) {
		Attr base = doc.createAttribute(BASE_ATTR);
		base.setValue(STRING_TYPE);
		extension.setAttributeNode(base);
	}
	
	private void setColumnAttribute(Element column, Document doc) {
		Attr name = doc.createAttribute(NAME_ATTR);
		name.setValue(COLUMN_ELEMENT);
		column.setAttributeNode(name);

		Attr maxOccurs = doc.createAttribute(MAXOCCURS_ATTR);
		maxOccurs.setValue(UNBOUNDED_VAL);
		column.setAttributeNode(maxOccurs);
		
		Attr minOccurs = doc.createAttribute(MINOCCURS_ATTR);
		minOccurs.setValue(NOTHING);
		column.setAttributeNode(minOccurs);
	}
	
	private void setRowAttribute(Element sequence, Document doc) {	
		
		Attr name = doc.createAttribute(NAME_ATTR);
		name.setValue(ROW_ELEMENT);
		sequence.setAttributeNode(name);

		Attr maxOccurs = doc.createAttribute(MAXOCCURS_ATTR);
		maxOccurs.setValue(UNBOUNDED_VAL);
		sequence.setAttributeNode(maxOccurs);
		
		Attr minOccurs = doc.createAttribute(MINOCCURS_ATTR);
		minOccurs.setValue(NOTHING);
		sequence.setAttributeNode(minOccurs);

	}
	private void setRootAttribute(Element root, Document doc) {
		Attr xmlns = doc.createAttribute(XMLNS_ATTR);
		xmlns.setValue(XMLNS_VAL);
		root.setAttributeNode(xmlns);
		Attr eFormDefault = doc.createAttribute(ELEMENTFORMDEFAULT_ATTR);
		eFormDefault.setValue(ELEMENTFORMDEFAULT_VAL);
		root.setAttributeNode(eFormDefault);
		Attr formAttr = doc.createAttribute(FORMDEFAULT_ATTR);
		formAttr.setValue(FORMDEFAULT_VAL);
		root.setAttributeNode(formAttr);
	}
	
	private void setTableAtrribute(Element table, Document doc) {
		Attr name = doc.createAttribute(NAME_ATTR);
		name.setValue(TABLE_ELEMENT);
		table.setAttributeNode(name);
	}
}
