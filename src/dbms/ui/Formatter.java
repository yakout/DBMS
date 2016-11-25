package dbms.ui;

import dbms.util.Result;
import dbms.util.ResultSet;

import java.util.*;

public class Formatter {

    private int lengthOfColumn(Object key, ResultSet resultSet) {
        int max = 0;
        for(Result result : resultSet) {
            int currentLength = result.getString(key.toString()).length();
            if (result.getString(key.toString()).length() > max) {
                max = currentLength;
            }
        }
        return max;
    }

    void printTable(ResultSet resultSet) {
        // print upper border
        int numberOfColumns = 0;
        int widthOfCurrentColumn = 0;
        Result firstRow = resultSet.next();
        Iterator it = firstRow.getResult().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            widthOfCurrentColumn = lengthOfColumn(pair.getKey(), resultSet);
            System.out.print("+");
            for (int i = 0; i < widthOfCurrentColumn; i++) {
                System.out.print("-");
            }
            it.remove(); // avoids a ConcurrentModificationException
            numberOfColumns++;
        }
        System.out.print("+");

        // print column names

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.print("|");
            System.out.print(pair.getKey());
            for (int i = 0; i < widthOfCurrentColumn - pair.getKey().toString().length(); i++) {
                System.out.print(" ");
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
        System.out.print("|");

    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("ID", 1);
        map.put("Name", "Yakout");
        map.put("Part", "SQL Parser");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("ID", 2);
        map2.put("Name", "Tolba");
        map2.put("Part", "SQL Parser");

        Map<String, Object> map3 = new HashMap<>();
        map3.put("ID", 3);
        map3.put("Name", "Anas");
        map3.put("Part", "XML Parser");

        Map<String, Object> map4 = new HashMap<>();
        map4.put("ID", 4);
        map4.put("Name", "Khaled");
        map4.put("Part", "XML Parser");

        Result result = new Result(map);
        Result result2 = new Result(map2);
        Result result3 = new Result(map3);
        Result result4 = new Result(map4);

        ArrayList<Result> results = new ArrayList<>();
        results.add(result);
        results.add(result2);
        results.add(result3);
        results.add(result4);

        ResultSet resultSet = new ResultSet(results);
        new Formatter().printTable(resultSet);
    }
}
