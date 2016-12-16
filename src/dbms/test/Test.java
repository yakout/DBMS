package dbms.test;

import dbms.exception.*;
import dbms.sqlparser.SQLParser;

import java.io.FileNotFoundException;


public class Test {
    public static void main(String[] args) throws FileNotFoundException, IncorrectDataEntryException,
            TableNotFoundException, DatabaseNotFoundException,
            SyntaxErrorException, TableAlreadyCreatedException {
        try {
            SQLParser.getInstance().parse("CREATE DATABASE db1;").execute();
            SQLParser.getInstance().parse("CREATE TABLE table1 (ID int, Name varchar, Gender varchar, Date date);").execute(); // FAIL
            SQLParser.getInstance().parse("INSERT INTO table1 VALUES (15, 'hamada14', 'Male', '1996-08-17');").execute();
            SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender, Date) VALUES (16, 'what_ever', 'Female', '1996-12-15');").execute();
            SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender, Date) VALUES (17, 'Yakouts GF', 'Male', '1995-08-08');").execute();
            SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (18, 'tolbas', 'Female');").execute();
            SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (18, 'tolbas', 'Female');").execute();
            SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (18, 'tolbas', 'Female');").execute();
            SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (18, 'tolbas', 'Female');").execute();
            SQLParser.getInstance().parse("SELECT * FROM table1 where id < 18 UNION SELECT * FROM table1 WHERE gender = 'Male';").execute();


        } catch (DatabaseNotFoundException
                | TableNotFoundException | SyntaxErrorException
                | DataTypeNotSupportedException | TableAlreadyCreatedException
                | DatabaseAlreadyCreatedException | IncorrectDataEntryException e) {
            e.printStackTrace();
        }
    }
}
