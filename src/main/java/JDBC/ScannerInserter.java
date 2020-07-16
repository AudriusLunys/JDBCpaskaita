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
        Scanner scn = new Scanner(System.in);
        List<Baldai> balduSarasas = new ArrayList<Baldai>();

        addValuesToBaldai(url, username, password, scn);
        FetchBaldaiFromDB(url, username, password, balduSarasas);
        PrintBaldaiList(balduSarasas);

    }

    private static void PrintBaldaiList(List<Baldai> balduSarasas) {
        System.out.println("***DISPLAY BALDU SARASAS***");
        for (Baldai baldas : balduSarasas) {
            System.out.println(" ID " + baldas.getId() + " PREKES pavadinimas " + baldas.getName() +
                    " PREKES svoris " + baldas.getSvoris() + " PREKES kaina " + baldas.getKaina());
        }
    }

    private static void FetchBaldaiFromDB(String url, String username, String password, List<Baldai> balduSarasas) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM baldai");

            while (resultSet.next()) {
                Baldai baldai = new Baldai();
                baldai.setId(resultSet.getInt("id"));
                baldai.setName(resultSet.getString("name"));
                baldai.setSvoris(resultSet.getInt("svoris"));
                baldai.setKaina(resultSet.getInt("kaina"));
                balduSarasas.add(baldai);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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