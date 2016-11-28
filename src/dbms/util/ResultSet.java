package dbms.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ResultSet implements Iterable<Result> {

	private List<Result> results;
	private int i;

	public ResultSet() {
		results = new ArrayList<>();
		i = 0;
	}

	public ResultSet(ArrayList<Result> results) {
		this.results = new ArrayList<>();
		for (Result res : results) {
			if (res == null) {
				continue;
			}
			this.results.add(res);
		}
	}
	
	public List<Map<String, Object>> getMapList(ResultSet resultSet) {
		List<Map<String, Object>> mapRepresent = new LinkedList<Map<String, Object>>();
		for (int j = 0; j < results.size(); j++) {
			mapRepresent.add(results.get(j).getResult());
		}
		return mapRepresent;
	}

	public void add(Result result) {
		results.add(result);
	}

	public int size() {
		return results.size();
	}

	public boolean hasNext() {
		if (i < results.size()) {
			return true;
		}
		return false;
	}

	public Result next() {
		if (hasNext()) {
			Result result = results.get(i);
			i++;
			return result;
		}
		return null;
	}

	@Override
	public Iterator<Result> iterator() {
		Iterator<Result> it = results.iterator();
		return it;
	}
}