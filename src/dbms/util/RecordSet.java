package dbms.util;

import java.util.*;

/**
 * Data holder for a collection of records.
 */
public class RecordSet implements Iterable<Record>, Cloneable {
	private List<Record> records;
	private List<String> columns;
	private int i;

	/**
	 * Constructor for a {@link RecordSet}, initiates an empty
	 * set.
	 */
    public RecordSet() {
		records = new ArrayList<>();
		columns = new ArrayList<>();
		i = 0;
	}

	/**
	 * Constructor for a {@link RecordSet} that takes in an {@link ArrayList}
	 * if records and copies data from it.
	 * @param records {@link ArrayList} of records.
	 */
    public RecordSet(List<Record> records) {
		this.records = new ArrayList<>();
		columns = new ArrayList<>();
		for (Record res : records) {
			if (res == null) {
				continue;
			}
			this.records.add(res);
		}
	}

	public void setColumnList(List<String> columnList) {
    	columns = columnList;
	}

	public List<String> getColumnList() {
    	return columns;
	}

	/**
	 * Gets a {@link List} representation of the set.
	 * @return {@link List} if records.
	 */
    public List<Map<String, Object>> getMapList() {
		List<Map<String, Object>> mapRepresent = new LinkedList<Map<String, Object>>();
		for (int j = 0; j < records.size(); j++) {
			mapRepresent.add(records.get(j).getRecord());
		}
		return mapRepresent;
	}

	/**
	 * Adds a new {@link Record} to the set of records.
	 * @param record {@link Record} to be added.
	 */
	public void add(Record record) {
		records.add(record);
	}

	/**
	 * Gets number of records in set.
	 * @return size.
	 */
	public int size() {
		return records.size();
	}

	/**
	 * Checks if a set has a next result when using an iterator.
	 * @return boolean value.
	 */
	public boolean hasNext() {
		if (i < records.size()) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the next result in line when using an iterator.
	 * @return next {@link Record}.
	 */
	public Record next() {
		if (hasNext()) {
			Record record = records.get(i);
			i++;
			return record;
		}
		return null;
	}

	@Override
	public Iterator<Record> iterator() {
		Iterator<Record> it = records.iterator();
		return it;
	}

	/**
	 * Checks if the result set is empty.
	 * @return boolean value.
	 */
	public boolean isEmpty() {
		return records.isEmpty();
	}


	/**
	 * // TODO support multiple columns: String...
	 * @param columnName the column name
	 * @param isAscending
     */
	public void orderBy(boolean isAscending, final String columnName) {
		records.sort(new Comparator<Record>() {
			@Override
			public int compare(Record o1, Record o2) {
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
			Collections.reverse(records);
		}
	}

	public List<Record> getRecords() {
		return records;
	}

    /**
     * remove duplicates from records.
     */
    public void distinct() {
        Set<Record> distinctRecords = new HashSet<>();
        for (Record record : records) {
            distinctRecords.add(record);
        }
        records.clear();
        records.addAll(distinctRecords);
    }

    public RecordSet union(RecordSet recordSet) {
        RecordSet result = new RecordSet(records);
        while(recordSet.hasNext()) {
            result.add(recordSet.next());
        }
        return result;
    }

	@Override
	public RecordSet clone() {
		RecordSet newRecSet = new RecordSet();
		for (Record record : records) {
			newRecSet.add(record.clone());
		}
    	return newRecSet;
	}

	/**
     * just for testing // TODO: remove
     * @param args
     */
    public static void main(String[] args) {
        LinkedHashMap<String, Object> map1 = new LinkedHashMap<>();
        map1.put("ahmed", 1);
        LinkedHashMap<String, Object> map2 = new LinkedHashMap<>();
        map2.put("ahmed", 1);
        LinkedHashMap<String, Object> map3 = new LinkedHashMap<>();
        map3.put("ahmed", 1);

        Record record1 = new Record(map1);
        Record record2 = new Record(map2);
        Record record3 = new Record(map3);

        List<Record> records = new ArrayList<>();
        records.add(record1);
        records.add(record2);
        records.add(record3);

        RecordSet recordSet = new RecordSet(records);
        RecordSet recordSet1 = new RecordSet(records);
        recordSet.distinct();

        System.out.print(recordSet.union(recordSet1).size()); // 4
        System.out.print(recordSet.size()); // 1
    }

}