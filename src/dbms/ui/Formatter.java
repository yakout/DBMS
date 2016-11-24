package dbms.ui;


import java.util.Scanner;

public class Formatter {

    public static void main(String[] args) {
        // *** main just for Testing ***
        System.out.print("sql> ");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.nextLine().contains(".quit")) {
            System.out.print("sql> ");
        }
    }
}
