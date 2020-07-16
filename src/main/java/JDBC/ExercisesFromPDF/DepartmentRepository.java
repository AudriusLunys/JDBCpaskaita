package JDBC.ExercisesFromPDF;

import JDBC.Baldai;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//Implement findAll() - returns a list of Department objects representing all table rows

public class DepartmentRepository {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/humanresources?useSSL=false";
        String username = "root";
        String password = "Hjrt5579";

        List<Department> departments = new ArrayList<Department>();
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");
            while (resultSet.next()) {
                Department department = new Department();
                department.setDepartmentId(resultSet.getInt("departmentId"));
                department.setName(resultSet.getString("name"));
                departments.add(department);
                //connection.close();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        for (Department department : departments) {
            System.out.println(department.getDepartmentId() + " "+ department.getName());
        }

    }


}

