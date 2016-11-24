package dbms.util;

import java.util.HashMap;
import java.util.Map;

public class Result {
	private Map<String, Object> result;

	public Result() {
		result = new HashMap<>();
	}

	public Result(Map<String, Object> result) {
		this.result = result;
	}

	public void add(String key, Object object) {
		result.put(key, object);
	}

	public Integer getInt(String key) {
		Object ret = result.get(key);
		if (ret instanceof Integer) {
			return ((Integer) ret).intValue();
		}
		return null;
	}

	public String getString(String key) {
		Object ret = result.get(key);
		if (ret instanceof String) {
			return (String) ret;
		}
		return null;
	}
}
