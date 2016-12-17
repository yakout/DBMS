package dbms.backend;

import dbms.backend.parsers.json.JSONParser;

import java.util.HashMap;

public class BackendParserFactory {
    private static HashMap<String, BackendParser> registeredParsers = null;
    private static BackendParserFactory instance = null;
    private String currentKey = JSONParser.KEY;

    private BackendParserFactory() {
        registeredParsers = new HashMap<>();
        try {
            loadParsers();
        } catch (Throwable e) {
            // TODO
            e.getStackTrace();
        }
    }
    /**
     *
     * @return {@link BackendParserFactory} return static instance of
     *  factory by singleton.
     */
    public static BackendParserFactory getFactory() {
        if (instance == null) {
            instance = new BackendParserFactory();
        }
        return instance;
    }
    
    /**
     * Registers the invoker class to the factory.
     * @param key defines the key of the parser required to be registered.
     * @param parser {@link BackendParser} shows value of the
     *  registered class type.
     */
    public void register(final String key, final BackendParser parser) {
        registeredParsers.put(key, parser);
    }
    
    /**
     * Links the invoker class to its instance
     * @param key defines the the key of the required parser.
     * @return {@link BackendParser} reference to the instance of
     *  the required parser.
     */
    public BackendParser getRegisteredParser(final String key) {
        return registeredParsers.get(key).getParser();
    }

    public BackendParser getCurrentParser() {
        return registeredParsers.get(currentKey).getParser();
    }

    public void setCurrentParser(final String key) {
        if (registeredParsers.get(key) == null) {
            throw new UnsupportedOperationException();
        }
        currentKey = key;
    }

    public static void loadParsers() {
        try {
            Class.forName("dbms.backend.parsers.xml.XMLParser");
            Class.forName("dbms.backend.parsers.json.JSONParser");
            Class.forName("dbms.backend.parsers.protobuf.ProtocolBufferParser");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
