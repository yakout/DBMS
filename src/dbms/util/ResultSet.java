package dbms.util;

import java.util.*;

/**
 * Data holder for a collection of results.
 */
public class ResultSet implements Iterable<Result> {

	private List<Result> results;
	private int i;

	/**
	 * Constructor for a {@link ResultSet}, initiates an empty
	 * set.
	 */
	public ResultSet() {
		results = new ArrayList<>();
		i = 0;
	}

	/**
	 * Constructor for a {@link ResultSet} that takes in an {@link ArrayList}
	 * if results and copies data from it.
	 * @param results {@link ArrayList} of results.
	 */
	public ResultSet(ArrayList<Result> results) {
		this.results = new ArrayList<>();
		for (Result res : results) {
			if (res == null) {
				continue;
			}
			this.results.add(res);
		}
	}

	/**
	 * Gets a {@link List} representation of the set.
	 * @return {@link List} if results.
	 */
	public List<Map<String, Object>> getMapList() {
		List<Map<String, Object>> mapRepresent = new LinkedList<Map<String, Object>>();
		for (int j = 0; j < results.size(); j++) {
			mapRepresent.add(results.get(j).getResult());
		}
		return mapRepresent;
	}

	public List<Result> getResults() {
		return results;
	}

	/**
	 * Adds a new {@link Result} to the set of results.
	 * @param result {@link Result} to be added.
	 */
	public void add(Result result) {
		results.add(result);
	}

	/**
	 * Gets number of results in set.
	 * @return size.
	 */
	public int size() {
		return results.size();
	}

	/**
	 * Checks if a set has a next result when using an iterator.
	 * @return boolean value.
	 */
	public boolean hasNext() {
		if (i < results.size()) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the next result in line when using an iterator.
	 * @return next {@link Result}.
	 */
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

	/**
	 * Checks if the result set is empty.
	 * @return boolean value.
	 */
	public boolean isEmpty() {
		return results.isEmpty();
	}

}