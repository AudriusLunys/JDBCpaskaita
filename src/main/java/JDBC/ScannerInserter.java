package JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Lenteleje baldai prideti dar du stulpelius - kaina ir svoris
Parasyti programa, kuri nuskaitytu user input (scanner) 3 prekiu pavadinimus, kaina, svori.
Parasyti metoda kuris ikeltu duomenis i lentele
Parasyti metoda kuris nuskaitytu duomenis is lenteles ir atvaizduotu ekrane
*/
public class ScannerInserter {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
        String username = "root";
        String password = "Hjrt5579";
        List<Baldai> balduSarasas = fetchBaldaiFromDB(url, username, password);
        Scanner scn = new Scanner(System.in);

        addValuesToBaldai(url, username, password, scn);
        fetchBaldaiFromDB(url, username, password);


    }

    private static List<Baldai> fetchBaldaiFromDB(String url, String username, String password) {
        List<Baldai> balduSarasas = null;
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM baldai");
            balduSarasas = new ArrayList<Baldai>();
            while (resultSet.next()) {
                Baldai baldai = new Baldai();
                baldai.setId(resultSet.getInt("id"));
                baldai.setName(resultSet.getString("name"));
                baldai.setKaina(resultSet.getInt("kaina"));
                baldai.setSvoris(resultSet.getInt("svoris"));
                balduSarasas.add(baldai);

                }
               for (Baldai baldas : balduSarasas) {
                System.out.println("ID: " + baldas.getId() + " Name: " + baldas.getName() + " kaina " + baldas.getKaina()
                        + " svoris " + baldas.getSvoris());
                connection.close();

            }
            statement.close();
            resultSet.close();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return balduSarasas;
    }

    private static void addValuesToBaldai(String url, String username, String password, Scanner scn) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            for (int i = 0; i < 1; i++) {
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
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }




    }
}
