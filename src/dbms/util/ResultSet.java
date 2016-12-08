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
    public ResultSet(List<Result> results) {
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


	/**
	 * // TODO support multiple columns: String...
	 * @param columnName the column name
	 * @param isAscending
     */
	public void orderBy(boolean isAscending, final String columnName) {
		results.sort(new Comparator<Result>() {
			@Override
			public int compare(Result o1, Result o2) {
				Object value1 = o1.get(columnName);
				Object value2 = o2.get(columnName);
				if (value1 == null || value2 == null) {
					return -1;
				} else if (value1 instanceof String) {
					return ((String) value1).compareTo((String) value2);
				} else {
					return ((Integer) value1).compareTo((Integer) value2);
				}
			}
		});
		if (!isAscending) {
			Collections.reverse(results);
		}
	}

    /**
     * remove duplicates from results.
     */
    public void distinct() {
        Set<Result> distinctResults = new HashSet<>();
        for (Result result : results) {
            distinctResults.add(result);
        }
        results.clear();
        results.addAll(distinctResults);
    }

    public ResultSet union(ResultSet resultSet) {
        ResultSet result = new ResultSet(results);
        while(resultSet.hasNext()) {
            result.add(resultSet.next());
        }
        return result;
    }


    /**
     * just for testing // TODO: remove
     * @param args
     */
    public static void main(String[] args) {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("ahmed", 1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("ahmed", 1);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("ahmed", 1);

        Result result1 = new Result(map1);
        Result result2 = new Result(map2);
        Result result3 = new Result(map3);

        List<Result> results = new ArrayList<>();
        results.add(result1);
        results.add(result2);
        results.add(result3);

        ResultSet resultSet = new ResultSet(results);
        ResultSet resultSet1 = new ResultSet(results);
        resultSet.distinct();

        System.out.print(resultSet.union(resultSet1).size()); // 4
        System.out.print(resultSet.size()); // 1
    }

}