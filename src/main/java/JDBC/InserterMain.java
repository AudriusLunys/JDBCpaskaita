package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InserterMain {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
        String username = "root";
        String password = "Hjrt5579";

        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();
            String sqlString = "INSERT INTO baldai (name) VALUES ('etazerka')";
            statement.executeUpdate(sqlString);
            System.out.println("all good");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
