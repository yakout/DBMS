package dbms.xml;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class ParserUtil {

	private static final ResourceBundle CONSTANTS =
			ResourceBundle.getBundle("dbms.xml.Constants");

	private ParserUtil() {

	}

	protected static boolean validateColumnEntries(Map<String, Object> entryMap,
			Map<String, String> columns) {
		if (columns == null) {
			return false;
		}
		for (Map.Entry<String, Object> entry : entryMap.entrySet()) {
			String entryName = entry.getKey();
			String entryType = getObjectClassName(entry.getValue());
			if (entryType == null) {
				return false;
			}
			String colType = columns.get(entryName);
			if (colType == null) {
				return false;
			}
			if (!entryType.equals(colType)) {
				return false;
			}
		}
		return true;
	}

	protected static boolean validateColumnEntries(Map<String, Object> entryMap,
			NodeList cols) {
		return validateColumnEntries(entryMap, getColsNodeListMap(cols));
	}

	protected static boolean validateSQLPredicate() {
		return false;
	}

	protected static Map<String, String> getColsNodeListMap(NodeList cols) {
		Map<String, String> columns = new HashMap<String, String>();
		for (int i = 0; i < cols.getLength(); i++) {
			Node col = cols.item(i);
			String name = col.getAttributes().getNamedItem(
					CONSTANTS.getString("name.attr"))
					.getTextContent();
			String type = col.getAttributes().getNamedItem(
					CONSTANTS.getString("type.attr"))
					.getTextContent();
			columns.put(name, type);
		}
		return columns;
	}

	protected static String getObjectStringValue(Object o, String type) {
		if (o == null) {
			return "";
		}
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

	protected static String getClassName(Class c) {
		if (c.equals(Integer.class)) {
			return "Integer";
		}
		if (c.equals(String.class)) {
			return "String";
		}
		return null;
	}

	protected static Node getColumnFromNodeList(String name,
			NodeList cols) {
		for (int i = 0; i < cols.getLength(); i++) {
			Node col = cols.item(i);
			String colName = col.getAttributes().getNamedItem(
					CONSTANTS.getString("name.attr")).getTextContent();
			if (colName.equals(name)) {
				return col;
			}
		}
		return null;
	}

	protected static String getObjectClassName(Object o) {
		if (o instanceof Integer) {
			return "Integer";
		}
		if (o instanceof String) {
			return "String";
		}
		return null;
	}
}
