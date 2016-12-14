package dbms.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static final String extensionjson = ".json";
    public static final String extensionschema = ".xsd";
    public static final String extensionxml = ".xml";
    public static final String extensionDTDschema = ".dtd";
    public static final String xsschema = "xs:schema";
    public static final String xselement = "xs:element";
    public static final String xsextension = "xs:extension";
    public static final String xscomplex = "xs:complexType";
    public static final String xssequence = "xs:sequence";
    public static final String xssimple = "xs:simpleContent";
    public static final String xsattr = "xs:attribute";
    public static final String nothing = "0";
    public static final String stringtype = "xs:string";
    public static final String inttype = "xs:integer";
    public static final String tableelement = "table";
    public static final String columnelement = "col";
    public static final String optionalcol = "col*";
    public static final String optionalrow = "row*";
    public static final String pcdata = "#PCDATA";
    public static final String cdata = "#CDATA";
    public static final String rowelement = "row";
    public static final String formDefaultattr = "attributeFormDefault";
    public static final String elementFormDefaultattr = "elementFormDefault";
    public static final String xmlnsattr = "xmlns:xs";
    public static final String nameattr = "name";
    public static final String dbattr = "database";
    public static final String rowsattr = "rows";
    public static final String typeattr = "type";
    public static final String useattr = "use";
    public static final String maxOccursattr = "maxOccurs";
    public static final String minOccursattr = "minOccurs";
    public static final String baseattr = "base";
    public static final String formDefaultval = "unqualified";
    public static final String elementFormDefaultval = "qualified";
    public static final String xmlnsval = "http://www.w3.org/2001/XMLSchema";
    public static final String unboundedval = "unbounded";
    public static final String optionalval = "optional";
    public static final String indexval = "index";
    public static final String defaultattr = "default";
    public static final String indentationval = "4";
    public static final String indentation = "{http://xml.apache.org/xslt}indent-amount";
    public static final String reqtype = "#REQUIRED";
    public static final String publicIddtd = "-//DBMS//XML DBMS 1.0//EN";
    private Map<String, String> consts = new HashMap<String, String>();

}
