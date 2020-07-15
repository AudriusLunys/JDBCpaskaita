package jdbc;
import JDBC.Baldai;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/*
PLAN:
1. Add mysql db driver maven or classpath DONE
2. Get connection
3. Create and submit SQL query
4. Process the result set
 */
public class ConnectionMain {
    public static void main(String[] args) throws SQLException {
        Connection connection = createConnection();
        List<Baldai> balduSarasas = fetchBaldaiFromDatabase(connection);
        printOutBaldai(balduSarasas);
    }
    private static Connection createConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
        String username = "root";
        String password = "Hjrt5579";
        return DriverManager.getConnection(url, username, password);
    }
    private static List<Baldai> fetchBaldaiFromDatabase(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM baldai");
        List<Baldai> balduSarasas = new ArrayList<Baldai>();
        while(resultSet.next()) {
            Baldai baldai = new Baldai();
            baldai.setId(resultSet.getInt("id"));
            baldai.setName(resultSet.getString("name"));
            balduSarasas.add(baldai);
            connection.close();
        }
        statement.close();
        resultSet.close();
        return balduSarasas;
    }
    private static void printOutBaldai(List<Baldai> balduSarasas) {
        for (Baldai baldas : balduSarasas) {
            System.out.println("ID: " + baldas.getId() + " Name: " + baldas.getName());
        }
    }
}