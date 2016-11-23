package dbms.xml;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DOMParser {

	private static final String WORKSPACE_DIR =
			System.getProperty("user.home") + "\\databases\\";
	private static final String POSTFIX_SCHEMA =
			"Schema.xml";
	private static final String SCHEMA_ELEMENT = "xs:element";
	private static DOMParser instance = null;

	private DOMParser() {

	}

	public static DOMParser getInstance() {
		if (instance == null) {
			instance = new DOMParser();
		}
		return instance;
	}

	public void createTable(String dbName, String tableName)
			throws FileNotFoundException, RuntimeException {

		DocumentBuilder dBuilder;
		try {
			dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		createSchema(doc, tableName);
		Transformer transformer =
				TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(doc);
		File dir = new File(WORKSPACE_DIR + dbName);
		if (!dir.exists()) {
			throw new FileNotFoundException();
		}
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "10");
		StreamResult stream = new StreamResult(new File(dir, tableName + POSTFIX_SCHEMA));
		transformer.transform(source, stream);
		} catch (ParserConfigurationException
				| TransformerFactoryConfigurationError | TransformerException e) {
			throw new RuntimeException();
		}
	}

	private void createSchema(Document doc, String tableName) {
		//Root element -- Table
		Element root = doc.createElement("x");
		doc.appendChild(root);
		Element tbl = doc.createElement(SCHEMA_ELEMENT);
		tbl.setAttribute("name", tableName);
		root.appendChild(tbl);
	}

	private void createDB() {

	}
}
