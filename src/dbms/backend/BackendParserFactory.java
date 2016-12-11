package dbms.backend;

import dbms.backend.parsers.json.JSONParser;
import dbms.backend.parsers.xml.XMLParser;

import java.util.HashMap;

public class BackendParserFactory {
	private static HashMap<String, BackendParser> registeredParsers = null;
	private static BackendParserFactory instance = null;
	private String currentKey = JSONParser.KEY;

	private BackendParserFactory() {
		registeredParsers = new HashMap<>();
		loadParsers();
	}

	public static BackendParserFactory getFactory() {
		if (instance == null) {
			instance = new BackendParserFactory();
		}
		return instance;
	}

	public void register(String key, BackendParser parser) {
		registeredParsers.put(key, parser);
	}

	public BackendParser getRegisteredParser(String key) {
		return registeredParsers.get(key).getParser();
	}

	public BackendParser getCurrentParser() {
		return registeredParsers.get(currentKey).getParser();
	}

	public void setCurrentParser(String key) {
		if (registeredParsers.get(key) == null) {
			throw new UnsupportedOperationException();
		}
		currentKey = key;
	}

	private void loadParsers() {
		try {
			Class.forName("dbms.backend.parsers.xml.XMLParser");
			Class.forName("dbms.backend.parsers.json.JSONParser");
			Class.forName("dbms.backend.parsers.protobuf.ProtocolBufferParser");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
