package dbms.backend.parsers.xml.schema.dtd;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

class DTDElementCreator {
    static PrintWriter out;

    private DTDElementCreator() {
    }
    /**
     * Creates DTD element in DTD file.
     * @param elName defines element name.
     * @param property defines the behaviour of the DTD element.
     * @param out {@link PrintWriter} Reference on the file.
     * @throws FileNotFoundException if the named file is no found.
     */
    protected static void createElement(String elName, String property, PrintWriter out)
            throws FileNotFoundException {
        out.println("<!ELEMENT " + elName + " (" + property + ")>");
    }

}
