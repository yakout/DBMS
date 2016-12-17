package dbms.backend.parsers.xml.schema.dtd;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

class DTDAttributeCreator {
    private static final ResourceBundle CONSTANTS =
            ResourceBundle.getBundle("dbms.backend.parsers.xml.Constants");

    /**
     * Creates an attribute element in the DTD file.
     * @param elName element name.
     * @param attName attribute name.
     * @param state behaviour of DTD element.
     * @param out {@link PrintWriter} Reference on the file.
     * @throws FileNotFoundException if there is not file with that name.
     */
    protected static void createElement(String elName, String attName,
                                        String state, PrintWriter out) throws
            FileNotFoundException {
        out.println("<!ATTLIST " + elName + " " + attName + " "
                + CONSTANTS.getString("c.data").substring(1) + " " + state +
                ">");
    }
}
