package dbms.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResultSet implements Iterable<Result> {

	private List<Result> results;
	private int i;

	public ResultSet() {
		results = new ArrayList<>();
		i = 0;
	}

	public ResultSet(ArrayList<Result> results) {
		this.results = results;
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
