package dbms.backend;

import java.util.HashMap;

public class BackendParserFactory {
	private static HashMap<String, BackendParser> registeredParsers = null;
	private static BackendParserFactory instance = null;
	private String currentKey = "xml";

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
			Class.forName("dbms.backend.xml.XMLParser");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
