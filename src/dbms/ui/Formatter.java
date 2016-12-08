package dbms.ui;

import dbms.util.Record;
import dbms.util.RecordSet;

import java.util.*;


public class Formatter {
    private static Formatter instance;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static Formatter getInstance() {
        if (instance == null) {
            instance = new Formatter();
        }
        return instance;
    }

    private int getColumnWidth(Object key, RecordSet recordSet) {
        int max = key.toString().length();
        for(Record record : recordSet) {
            Object value = record.getRecord().get(key);
            int currentLength = value == null ? 0 : value.toString().length();
            if (currentLength > max) {
                max = currentLength;
            }
        }
        return max;
    }

    private void printTableLine(Record firstRecord, List<Integer> widthOfColumns) {
        Iterator it = firstRecord.getRecord().entrySet().iterator();
        int currentColumn = 0;
        while (it.hasNext()) {
            it.next();
            System.out.print("+");
            for (int i = 0; i < widthOfColumns.get(currentColumn); i++) {
                System.out.print("-");
            }
            currentColumn++;
        }
        System.out.println("+");
    }

    private List<Integer> getAllColumnsWidth(RecordSet recordSet, Record firstRecord) {
        Iterator it = firstRecord.getRecord().entrySet().iterator();
        List<Integer> widthOfColumns = new ArrayList<>();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            widthOfColumns.add(getColumnWidth(pair.getKey(), recordSet));
        }
        return widthOfColumns;
    }

    private void printFirstRow(Record firstRecord, List<Integer> widthOfColumns) {
        Iterator it = firstRecord.getRecord().entrySet().iterator();
        int currentColumn = 0;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Object value = pair.getValue();
            System.out.print("|");
            if (value == null) {
                System.out.print("");
                for (int i = 0; i < widthOfColumns.get(currentColumn); i++)
                    System.out.print(" ");
            } else {
                System.out.print(value);
                for (int i = 0; i < widthOfColumns.get(currentColumn) - value.toString().length(); i++)
                    System.out.print(" ");
            }
            currentColumn++;
        }
        System.out.println("|");
    }

    private void printAllRows(RecordSet recordSet, Record firstRecord, List<Integer> widthOfColumns) {
        int currentColumn = 0;
        while(recordSet.hasNext()) {
            Iterator it4 = recordSet.next().getRecord().entrySet().iterator();
            while (it4.hasNext()) {
                Map.Entry pair = (Map.Entry)it4.next();
                System.out.print("|");
                Object value = pair.getValue();
                if (value == null) {
                    System.out.print("");
                    for (int i = 0; i < widthOfColumns.get(currentColumn); i++)
                        System.out.print(" ");
                } else {
                    System.out.print(value);
                    for (int i = 0; i < widthOfColumns.get(currentColumn) - value.toString().length(); i++)
                        System.out.print(" ");
                }
                currentColumn++;
            }
            System.out.println("|");
            currentColumn = 0;
            printTableLine(firstRecord, widthOfColumns);
        }
    }

    private void printHeaders(Record firstRecord, List<Integer> widthOfColumns) {
        int currentColumn = 0;
        Iterator it = firstRecord.getRecord().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.print("|");
            System.out.print(ANSI_GREEN + pair.getKey() + ANSI_RESET);
            for (int i = 0; i < widthOfColumns.get(currentColumn) - pair.getKey().toString().length(); i++) {
                System.out.print(" ");
            }
            currentColumn++;
        }
        System.out.println("|");
    }

    public void printTable(RecordSet recordSet) {
        Record firstRecord = recordSet.next();
        if (recordSet.isEmpty()) {
            return;
        }
        List<Integer> widthOfColumns = getAllColumnsWidth(recordSet, firstRecord);

        printTableLine(firstRecord, widthOfColumns);

        printHeaders(firstRecord, widthOfColumns);
        printTableLine(firstRecord, widthOfColumns);

        printFirstRow(firstRecord, widthOfColumns);
        printTableLine(firstRecord, widthOfColumns);

        printAllRows(recordSet, firstRecord, widthOfColumns);
    }

    public static void main(String[] args) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("ID", 1);
        map.put("Name", "Khaled");
        map.put("Part", "SQL Parser");

        Map<String, Object> map2 = new LinkedHashMap<>();
        map2.put("ID", 5);
        map2.put("Name", "Tolba");
        map2.put("Part", "SQL Parser");

        Map<String, Object> map3 = new LinkedHashMap<>();
        map3.put("ID", 4);
        map3.put("Name", "Anas");
        map3.put("Part", "XML Parser");

        Map<String, Object> map4 = new LinkedHashMap<>();
        map4.put("ID", 9);
        map4.put("Name", null);
        map4.put("Part", "XML Parser                          ");

        Record record = new Record(map);
        Record record2 = new Record(map2);
        Record record3 = new Record(map3);
        Record record4 = new Record(map4);

        ArrayList<Record> records = new ArrayList<>();
        records.add(record);
        records.add(record2);
        records.add(record3);
        records.add(record4);

        RecordSet recordSet = new RecordSet(records);
        recordSet.orderBy(true, "ID");
        new Formatter().printTable(recordSet);

    }
}