package dbms.xml;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import dbms.exception.SyntaxErrorException;
import dbms.sqlparser.sqlInterpreter.BooleanExpression;

public class test {
	public static void main(String[] args) {
		Map<String, Object> row = new HashMap<>();
		row.put("col1", 5);
		row.put("col2", "test");
		row.put("col3", "test2");
		try {
			Queue<Object> postfix = new BooleanExpression().toPostfix("col2 != col3");
			System.out.println(Evaluator.getInstance().evaluate(row, postfix));
		} catch (SyntaxErrorException e1) {
			e1.printStackTrace();
		}
	}
}
