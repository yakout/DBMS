package dbms.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Data holder for a single row of data, normally
 * is used with {@link ResultSet}.
 */
public class Result {
	private Map<String, Object> result;

	/**
	 * Constructor for a result.
	 */
	public Result() {
		result = new LinkedHashMap<>();
	}

	/**
	 * Constructor for a result that takes in a
	 * {@link Map} as a data holder.
	 * @param result {@link Map} map of data.
	 */
	public Result(Map<String, Object> result) {
		this.result = result;
	}

	/**
	 * Adds a single piece of data to result.
	 * @param key key to be used to access an object.
	 * @param {@link Object} to be accessed.
	 */
	public void add(String key, Object object) {
		result.put(key, object);
	}

	/**
	 * Return an {@link Integer} representation of a
	 * single piece of data, returns null if it can't be parsed
	 * to an Integer.
	 * @param key key to be used to access an integer.
	 * @return {@link Integer} to be accessed.
	 */
	public Integer getInt(String key) {
		Object ret = result.get(key);
		if (ret instanceof Integer) {
			return ((Integer) ret).intValue();
		}
		return null;
	}

	/**
	 * Return a {@link String} representation of a
	 * single piece of data, returns null if it can't be parsed
	 * to a string.
	 * @param key key to be used to access a string.
	 * @return {@link String} to be accessed.
	 */
	public String getString(String key) {
		Object ret = result.get(key);
		if (ret instanceof String) {
			return (String) ret;
		}
		return null;
	}

	/**
	 * Returns a {@link Map} representation of result.
	 * @return {@link Map} to be accessed.
	 */
	public Map<String, Object> getResult() {
		return result;
	}

	/**
	 * Returns an object of which a specified key is mapped.
	 * @param key Key to be used to access object.
	 * @return {@link Object} object to be accessed.
	 */
	public Object get(String key) {
		return result.get(key);
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Result result1 = (Result) o;
		return result != null ? result.equals(result1.result) : result1.result == null;
	}

	@Override
	public int hashCode() {
		return result != null ? result.hashCode() : 0;
	}
}