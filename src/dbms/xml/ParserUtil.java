package dbms.xml;

class ParserUtil {
	private static ParserUtil instance = null;

	private ParserUtil() {

	}

	protected static ParserUtil getInstance() {
		if (instance == null) {
			instance = new ParserUtil();
		}
		return instance;
	}

	protected static boolean validateSQLPredicate() {
		return false;
	}

	protected static boolean validateColumnEntries() {
		return false;
	}
}
