package dbms.xml.schema.dtd;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

class DTDAttributeCreator {
	private static final ResourceBundle CONSTANTS =
			ResourceBundle.getBundle("dbms.xml.Constants");

	public static void createElement(String elName, String attName,
			String state, PrintWriter out) throws FileNotFoundException {
		out.println("<!ATTLIST " + elName + " " + attName + " "
			 + CONSTANTS.getString("c.data").substring(1) + " " + state + ">");
	}
}