package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ScannerInserter {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
        String username = "root";
        String password = "Hjrt5579";
        Scanner scn = new Scanner(System.in);
        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();
            System.out.println("vardas");
            String vardas = scn.next();
            System.out.println("kaina");
            int kaina = scn.nextInt();
            System.out.println("svoris");
            int svoris = scn.nextInt();
            String sqlString = "INSERT INTO baldai (name, kaina, svoris) " +
                    "VALUES ('" + vardas + "', " + kaina + ", " + svoris + ")";

            statement.executeUpdate(sqlString);
            System.out.println("all good");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
