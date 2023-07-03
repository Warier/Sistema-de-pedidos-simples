package com.warier.projeto.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "wariercraft";


    public static Connection getConnection() {
        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            conn.setAutoCommit(false);
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
