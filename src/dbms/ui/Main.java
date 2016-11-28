package dbms.ui;

import java.io.Console;

import dbms.exception.SyntaxErrorException;
import dbms.sqlparser.SQLParser;

public class Main {
	private final static SQLParser sqlParserObj = SQLParser.getInstance();

	private static void printHelpPanel() {
		System.console()
				.format("> select ~ selecting a column from a specific table or all columns %nand it can be "
						+ "under some conditions using the where keyword.%n"
						+ "> create ~ creating a database or creating a table %nwith specified columns' "
						+ "attributes and data types.%n" + "> drop ~ dropping or deleting a database or a table.%n"
						+ "> insert into ~ inserting specific values into specific columns in a table.%n"
						+ "> delete ~ deleting all data stored in a certain table %nor specific data stored "
						+ "in certain columns using the where keyword.%n"
						+ "> update ~ updating all table's columns or updating specific columns %nunder some conditions using "
						+ "the where keyword.%n");
	}

	public static void main(String[] args) {
		Console console = System.console();

		if (console == null) {
			System.err.print("No console!");
			System.exit(1);
		}

		while (true) {
			String inputQuery = new String();
			String inp = new String();
			while (true) {
				if (inputQuery.compareTo("") == 0) {
					inp = console.readLine(">> ").trim();
					if (inp.compareTo("%n") != 0) {
						inputQuery = inputQuery.concat(inp);
						System.out.println(inputQuery);
						if (inputQuery.toLowerCase().compareTo(".help") == 0
								|| inputQuery.toLowerCase().compareTo(".quit") == 0) {
							break;
						}
						inputQuery = inputQuery.trim();
						int len = inputQuery.length();
						if (len >= 1 && inputQuery.charAt(len - 1) == ';') {
							break;
						}
					}
				} else {
					inp = console.readLine();
					if (inp.compareTo("%n") != 0) {
						inputQuery = inputQuery.concat(" " + inp.trim());
						System.out.println(inputQuery);
						int len = inputQuery.trim().length();
						if (len >= 1 && inputQuery.charAt(len - 1) == ';') {
							break;
						}
					}
				}
			}
			if (inputQuery.toLowerCase().compareTo(".quit") == 0) {
				break;
			} else if (inputQuery.toLowerCase().compareTo(".help") == 0) {
				printHelpPanel();
			} else {
				try {
					sqlParserObj.parse(inputQuery);
				} catch (SyntaxErrorException e) {
					console.format("Invalid SQL command.%n");
				}
			}

		}
	}
}