# Java DBMS
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/db90bf05197e4a8dbd537b5b2e081e62)](https://www.codacy.com/app/yakout/DBMS?utm_source=github.com&utm_medium=referral&utm_content=yakout/DBMS&utm_campaign=badger)

> XML, JSON and [Protocol Buffers](https://developers.google.com/protocol-buffers/) based [RDBMS](https://en.wikipedia.org/wiki/Relational_database_management_system) that supports [SQL](https://en.wikipedia.org/wiki/SQL) syntax with [JDBC](https://en.wikipedia.org/wiki/Java_Database_Connectivity) API implementation.

## Supported SQL statements ##
> ### CREATE
>> - ``` CREATE DATABASE dbname; ```
>> - ``` CREATE TABLE table_name (column_name1 data_type, column_name2 data_type); ```

> ### DROP
>> - ``` DROP TABLE table_name; ```
>> - ``` DROP DATABASE database_name; ```

> ### INSERT
>> - ``` INSERT INTO table_name (ID,Name) VALUES (1,'Ahmed'); ```
>> - ``` INSERT INTO table_name VALUES (1,'Ahmed', ...); ```

> ### SELECT
>> - ``` SELECT col1, col2, col3 FROM table_name WHERE col1!='value'; ```
>> - ```SELECT * FROM table_name WHERE TRUE; ```
>> - ```SELECT * FROM table_name where col1 <= col2;```
>> - ```SELECT * FROM table_name where ID == 6;```
>> - ```SELECT * FROM table_name order by col1 ASC;```
>> - ```SELECT * FROM table_name order by col2 DESC;```
>> - ```SELECT * FROM table_name order by Name DESC where Name < 'S';```
>> - ```SELECT DISTINCT City FROM Customers;```

> ### DELETE
>> - ```DELETE FROM table_name WHERE some_column=some_value;```
>> - ```DELETE FROM table_name; // delete all rows but keeps the table```
>> - ```DELETE * FROM table_name; // same as above```

> ### UPDATE
>> - ```UPDATE table_name SET column1='value1',column2='value2' WHERE some_column='some_value'; // with condition```
>> - ```UPDATE tableName SET columnName='someValue', columnName='someValue'; // with no condition```

> ### ALTER
>> -  ```ALTER TABLE table_name ADD column_name datatype```
>> - ```ALTER TABLE table_name DROP COLUMN column_name```

> ### Union
>> - ```SELECT column_name(s) FROM table1 UNION SELECT column_name(s) FROM table2;```
>> - ```SELECT column_name(s) FROM table1 UNION ALL SELECT column_name(s) FROM table2;```
>> - ```SELECT city FROM Customers UNION all SELECT city FROM Suppliers union select city from City ORDER BY City;```


## Supported Data Types
##### int, varchar, date, float.

## Contribution Guidelines
This project is public, feel free to create pull requests or open issues.

## Screen Shots
![picture alt](https://github.com/yakout/DBMS/blob/master/screenshots/ScreenShot_1.png)
![picture alt](https://github.com/yakout/DBMS/blob/master/screenshots/ScreenShot_2.png)
