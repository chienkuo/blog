package me.akuo.blog.jdbc;

import java.sql.*;

public class Sample {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:f:\\sample.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("DROP TABLE if EXISTS person");
            statement.executeUpdate("CREATE TABLE person (id INTEGER, name STRING)");
            statement.executeUpdate("INSERT INTO person VALUES(1, 'leo')");
            statement.executeUpdate("INSERT INTO person VALUES(2, 'yui')");
            ResultSet rs = statement.executeQuery("SELECT * FROM person");
            while (rs.next()) {
                // read the result set
                System.out.println("name = " + rs.getString("name"));
                System.out.println("id = " + rs.getInt("id"));
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }
}