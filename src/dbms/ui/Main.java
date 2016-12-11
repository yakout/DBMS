package dbms.ui;

import dbms.exception.*;
import dbms.sqlparser.SQLParser;

import java.io.Console;

public class Main {
    private final static SQLParser sqlParserObj = SQLParser.getInstance();
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static Console console = System.console();

    private static void printHelpPanel() {
        System.console()
                .format("> select ~ selecting a column from a specific table or all columns %nand it can be "
                        + "under some conditions using the where keyword.%n"
                        + "> create ~ creating a database or creating a table %nwith specified columns' "
                        + "attributes and data types.%n"
                        + "> drop ~ dropping or deleting a database or a table.%n"
                        + "> insert into ~ inserting specific values into specific columns in a table.%n"
                        + "> delete ~ deleting all data stored in a certain table %nor specific data stored "
                        + "in certain columns using the where keyword.%n"
                        + "> update ~ updating all table's columns or updating specific columns %nunder some conditions using "
                        + "the where keyword.%n");
    }

    private static String readQuery() {
        String inputQuery = new String();
        String input = new String();
        while (true) {
            if (inputQuery.compareTo("") == 0) {
                input = console.readLine("sql> ").trim();
                if (input.compareTo("%n") != 0) {
                    inputQuery = inputQuery.concat(input);
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
                input = console.readLine();
                if (input.compareTo("%n") != 0) {
                    inputQuery = inputQuery.concat(" " + input.trim());
                    int len = inputQuery.trim().length();
                    if (len >= 1 && inputQuery.charAt(len - 1) == ';') {
                        break;
                    }
                }
            }
        }
        return inputQuery;
    }

    public static void main(String[] args) {
        if (console == null) {
            System.err.print("No console!");
            System.exit(1);
        }
        while (true) {
            String inputQuery = readQuery();
            if (inputQuery.toLowerCase().compareTo(".quit") == 0) {
                break;
            } else if (inputQuery.toLowerCase().compareTo(".help") == 0) {
                printHelpPanel();
            } else {
                try {
                    sqlParserObj.parse(inputQuery).execute();
                } catch (IncorrectDataEntryException
                        | DataTypeNotSupportedException
                        | SyntaxErrorException
                        | TableNotFoundException
                        | DatabaseNotFoundException
                        | TableAlreadyCreatedException
                        | DatabaseAlreadyCreatedException e) {
                    console.format(ANSI_RED + e.toString() + ANSI_RESET + "%n");
                }
            }

        }
    }
}