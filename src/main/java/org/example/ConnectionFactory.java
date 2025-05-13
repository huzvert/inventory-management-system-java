package org.example;

import java.sql.*;
import java.lang.*;

public class ConnectionFactory {


    static final java.lang.String driver = "com.mysql.cj.jdbc.driver";
    public static final java.lang.String url = "jdbc:mysql://localhost:3306/inventory";
    public static final java.lang.String username = "root";
    public static final java.lang.String password = "password";

    Connection conn = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public ConnectionFactory() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, username, password);
            statement = conn.createStatement();

            System.out.println("Connected successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void executeQuery() {
        try  {
            Statement statement = conn.createStatement();
            String sqlQuery = "SELECT productcode FROM currentstock";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // Process the ResultSet
            while (resultSet.next()) {
                // Retrieve data from the result set
                String id = resultSet.getString("productcode");


                // Do something with the data (e.g., print it)
                System.out.println(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
                System.out.println("Connection closed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        ConnectionFactory c = new ConnectionFactory();

        // Perform database operations here

        // Close the connection when done
        c.executeQuery();
        c.closeConnection();

    }

    public boolean checkLogin(String username, String password, String userType) {
        return (username.equals(username) && password.equals(password)) ;
        //||(username.equals("admin") && password.equals("password") && userType.equals("admin"));
        //return false;
    }
}