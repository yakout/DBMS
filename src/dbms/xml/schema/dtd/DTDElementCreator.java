package dbms.xml.schema.dtd;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

class DTDElementCreator {
	static PrintWriter out;
	private DTDElementCreator(){}

	protected static void createElement(String elName, String property, PrintWriter out)
			throws FileNotFoundException {
		out.println("<!ELEMENT " + elName + " (" + property + ")>");
	}

}
