package dbms.backend.parsers.xml;

import dbms.backend.BackendController;
import dbms.backend.BackendParser;
import dbms.backend.BackendParserFactory;
import dbms.backend.parsers.xml.schema.dtd.DTDSchemaParser;
import dbms.backend.parsers.xml.schema.xsd.XSDParser;
import dbms.datatypes.DatatypeFactory;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.util.Column;
import dbms.util.Table;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Table parser to .XML format.
 */
public final class XMLParser extends BackendParser {
    /**
     * Key to JSON parser that is used to register to factory.
     */
    public static final String KEY = "xml";

    /**
     * Resource bundle to constants.
     */
    private static final ResourceBundle CONSTANTS =
            ResourceBundle.getBundle("dbms.backend.parsers.xml.Constants");
    /**
     * Logger.
     */
    private static Logger log = LogManager.getLogger(XMLParser.class);

    /**
     * Static singleton instance.
     */
    private static XMLParser instance = null;

    /**
     * Builds {@link Document}s that are used to parse tables.
     */
    private static DocumentBuilder docBuilder = null;

    /**
     * Transforms a {@link Document} to a
     */
    private static Transformer transformer = null;

    static {
        BackendParserFactory.getFactory().register(KEY, getInstance());
    }

    /**
     * Default constructor.
     */
    private XMLParser() {
        try {
            docBuilder = DocumentBuilderFactory
                    .newInstance().newDocumentBuilder();
            transformer = TransformerFactory
                    .newInstance().newTransformer();
        } catch (ParserConfigurationException
                | TransformerConfigurationException
                | TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        }
        transformer.setOutputProperty(OutputKeys.INDENT,
                "yes");
        transformer.setOutputProperty(
                CONSTANTS.getString("indentation"),
                CONSTANTS.getString("indentation.val"));
    }

    /**
     * Gets static singleton instance.
     * @return Static singleton instance.
     */
    public static synchronized XMLParser getInstance() {
        if (instance == null) {
            instance = new XMLParser();
        }
        return instance;
    }

    @Override
    public BackendParser getParser() {
        return instance;
    }

    @Override
    public void createTable(Table table)
            throws DatabaseNotFoundException,
            TableAlreadyCreatedException {
        File tableFile = new File(openDB(table.getDatabase().getName()),
                table.getName()
                        + CONSTANTS.getString("extension.xml"));
        if (tableFile.exists()) {
            log.error("Can't create table with name" + table.getName()
                    + " this nameDatabase already created");
            throw new TableAlreadyCreatedException();
        }
        log.debug("\'" + table.getName() + "\' is created successfully.");
        write(table, tableFile);
        XSDParser.getInstance().createSchema(table.getDatabase().getName(),
                table.getName());
        DTDSchemaParser.getInstance().createDTDSchema(table.getDatabase()
                .getName(), table.getName());
    }

    @Override
    public void loadTable(Table table)
            throws TableNotFoundException, DatabaseNotFoundException {
        File tableFile = openTable(table.getDatabase().getName(), table
                .getName());
        Document doc = null;
        try {
            doc = docBuilder.parse(tableFile);
        } catch (SAXException | IOException e) {
            log.error("Error occured while loading the table.");
            e.printStackTrace();
        }
        validateDB(doc, table.getDatabase().getName());
        doc.getDocumentElement().normalize();
        parseDataToTable(table, doc);
        log.debug("\'" + table.getName() + "\' is loaded successfully.");
    }

    @Override
    public void writeToFile(Table table) throws TableNotFoundException,
            DatabaseNotFoundException {
        File tableFile = openTable(table.getDatabase().getName(), table
                .getName());
        write(table, tableFile);
        log.debug("Saved into file successfully.");
    }

    @Override
    public void dropTable(Table table)
            throws DatabaseNotFoundException {
        File tableFile = new File(openDB(table.getDatabase().getName()),
                table.getName()
                        + CONSTANTS.getString("extension.xml"));
        File xsdFile = new File(openDB(table.getDatabase().getName()), table
                .getName()
                + CONSTANTS.getString("extension.schema"));
        File dtdFile = new File(openDB(table.getDatabase().getName()), table
                .getName()
                + CONSTANTS.getString("extensionDTD.schema"));
        if (tableFile.exists()) {
            tableFile.delete();
            log.debug(table.getName() + " is deleted succussfully.");
        }
        if (xsdFile.exists()) {
            xsdFile.delete();
            log.debug(table.getName() + " XSD file is deleted succussfully.");
        }
        if (dtdFile.exists()) {
            dtdFile.delete();
            log.debug(table.getName() + " DTD file is deleted succussfully.");
        }
    }

    /**
     * Writes table content to file.
     * @param table Table.
     * @param tableFile Table file.
     */
    private void write(Table table, File tableFile) {
        Document doc = docBuilder.newDocument();
        Element tableElement = doc.createElement(
                CONSTANTS.getString("table.element"));
        tableElement.setAttribute(CONSTANTS.getString(
                "name.attr"), table.getName());
        tableElement.setAttribute(CONSTANTS.getString(
                "db.attr"), table.getDatabase().getName());
        tableElement.setAttribute(CONSTANTS.getString(
                "rows.attr"), Integer.toString(table.getSize()));
        doc.appendChild(tableElement);
        addColumns(doc, table, tableElement);
        transform(doc, tableFile, table.getName());
    }

    /**
     * Adds columns to table.
     * @param doc XML Document.
     * @param table Table.
     * @param tableElement Table Element in JAVA DOM.
     */
    private void addColumns(Document doc, Table table, Element tableElement) {
        for (Column col : table.getColumns()) {
            Element colElement = doc.createElement(
                    CONSTANTS.getString("column.element"));
            colElement.setAttribute(
                    CONSTANTS.getString("name.attr"), col.getName());
            try {
                colElement.setAttribute(
                        CONSTANTS.getString("type.attr"), (String) col
                                .getType().getField("KEY").get(col.getType()
                                        .newInstance()));
            } catch (DOMException | IllegalArgumentException
                    | IllegalAccessException | NoSuchFieldException
                    | SecurityException | InstantiationException e) {
                log.error("Error occured while parsing!");
                e.printStackTrace();
            }
            addRows(doc, col, colElement);
            tableElement.appendChild(colElement);
        }
    }

    private void addRows(Document doc, Column col, Element colElement) {
        int index = 0;
        for (Object entry : col.getEntries()) {
            Element rowElement = doc.createElement(
                    CONSTANTS.getString("row.element"));
            rowElement.setAttribute(
                    CONSTANTS.getString("index.val"), Integer.toString(index));
            if (entry == null) {
                rowElement.setTextContent("");
            } else {
                rowElement.setTextContent(String.valueOf(entry));
            }
            colElement.appendChild(rowElement);
            index++;
        }
    }

    private void parseDataToTable(Table table, Document doc) {
        int size = Integer.parseInt(doc.getElementsByTagName(
                CONSTANTS.getString("table.element")).item(0)
                .getAttributes().getNamedItem(CONSTANTS.getString(
                        "rows.attr")).getTextContent());
        table.setSize(size);
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
                if (!(row instanceof Element)) {
                    continue;
                }
                Object entry = DatatypeFactory.getFactory().toObj(
                        row.getTextContent(), colType);
                col.addEntry(DatatypeFactory.getFactory().convertToDataType(
                        entry));
            }
            table.addColumn(col);
        }
        log.debug("Data is reloaded successfully");
    }

    /**
     * Opens table file.
     * @param dbName Database name.
     * @param tableName Table name.
     * @return Table file.
     * @throws TableNotFoundException In case table wasn't found.
     * @throws DatabaseNotFoundException In case database wasn't found.
     */
    private File openTable(String dbName, String tableName)
            throws TableNotFoundException, DatabaseNotFoundException {
        File tableFile = new File(openDB(dbName), tableName
                + CONSTANTS.getString("extension.xml"));
        if (!tableFile.exists()) {
            log.error("Error occured:" + tableName + " table is not found.");
            throw new TableNotFoundException();
        }
        return tableFile;
    }

    private File openDB(String dbName)
            throws DatabaseNotFoundException {
        File database = new File(BackendController.getInstance()
                .getCurrentDatabaseDir()
                + File.separator + dbName);
        if (!database.exists()) {
            log.error("Error occured: Database is not found.");
            throw new DatabaseNotFoundException();
        }
        return database;
    }

    private void validateDB(Document doc, String dbName)
            throws DatabaseNotFoundException {
        String db = doc.getElementsByTagName(CONSTANTS.getString(
                "table.element")).item(0).getAttributes().getNamedItem(
                CONSTANTS.getString("db.attr")).getTextContent();
        if (!db.equals(dbName)) {
            log.error("Error occured:" + dbName + " database is not found.");
            throw new DatabaseNotFoundException();
        }
    }

    private void transform(Document doc, File tableFile, String tableName) {
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        DOMImplementation domImpl = doc.getImplementation();
        DocumentType doctype = domImpl.createDocumentType("doctype",
                "-//DBMS//DBMS v1.0//EN", tableName
                        + CONSTANTS.getString("extensionDTD.schema"));
        transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, doctype
                .getPublicId());
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype
                .getSystemId());
        DOMSource source = new DOMSource(doc);
        StreamResult result =
                new StreamResult(tableFile);
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            log.error("Error occured while transforming to XML file.");
            e.printStackTrace();
        }
        log.debug("Data is parsed successfully.");
    }
}
