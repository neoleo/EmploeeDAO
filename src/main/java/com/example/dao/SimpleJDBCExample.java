package com.example.dao;

import java.sql.*;

public class SimpleJDBCExample {

    public static void main(String[] args) {
        // Create the "url"
        // assume database server is running on the localhost
        String url = "jdbc:derby://localhost:1527/EmployeeDB";
        String username = "rumos";
        String password = "tiger";

        // Create a simple query
        String query = "select * from EMPLOYEE";

        // A try-with-resources example
        // Connection and Statement implement java.lan.AutoCloseable
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int empID = rs.getInt("ID");
                String first = rs.getString("FIRSTNAME");
                String last = rs.getString("LASTNAME");
                Date birth_date = rs.getDate("BIRTHDATE");
                float salary = rs.getFloat("SALARY");
                System.out.println("Employee ID:   " + empID + "\n"
                        + "Employee Name: " + first.trim() + " " + last.trim() + "\n"
                        + "Birth Date:    " + birth_date + "\n"
                        + "Salary:        " + salary + "\n");

            }
        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
            System.exit(0);
        }
        // No need to close the Connection and Statement objects, the compiler
        // will generate these for us and call the close() statement on this
        // objects in the order we obtained them in the try

        query = "INSERT INTO Employee VALUES (001, 'Bill','Murray','1950-09-21', 150000)";
        try (Connection con = DriverManager.getConnection(url, username, password)) {

            Statement stmt = con.createStatement();
            int rs = stmt.executeUpdate(query);

            if (rs == 1)
                System.out.println("New employee record added");

        } catch (SQLException e) {
            System.out.println("Exception creating connection2: " + e);
            System.exit(0);
        }
    }
}
