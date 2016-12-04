package dbms.xml;

import java.util.Collection;
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
			return true;
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

	protected static boolean validateColumns(NodeList columnList,
			Collection<String> columns) {
		if (columns == null) {
			return true;
		}
		Map<String, String> colsMap = getColsNodeListMap(columnList);
		for (String col : columns) {
			if (colsMap.get(col) == null) {
				return false;
			}
		}
		return true;
	}

	protected static String getObjectStringValue(Object o, String type) {
		if (o == null) {
			return "";
		} else if (o.getClass().getSimpleName().equals(type)) {
			return String.valueOf(o);
		}
		return null;
	}

	protected static String getObjectStringValue(Object o) {
		if (o == null) {
			return "";
		}
		return String.valueOf(o);
	}

	protected static String getClassName(Class c) {
		return c.getSimpleName();
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

//	protected static Node getRowFromNodeListWithGivenColumn(String name,
//			NodeList rows) {
//		int size = rows.getLength();
//		for (int i = 0; i < rows.getLength(); i++) {
//			Node row = rows.item(i);
//			Node columnNode = row.getParentNode();
//			String colName = columnNode.getAttributes().getNamedItem(
//					CONSTANTS.getString("name.attr")).getTextContent();
//			String val = row.getAttributes().getNamedItem(CONSTANTS.getString("index.val")).getTextContent();
//			if (colName.equals(name)) {
//				return row;
//			}
//		}
//		return null;
//	}

	protected static String getObjectClassName(Object o) {
		return o.getClass().getSimpleName();
	}
}
