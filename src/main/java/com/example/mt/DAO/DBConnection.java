package com.example.mt.DAO;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static final String URL = "jdbc:mysql://localhost:3306/mutuelle_centralisee";
    public static final String USER = "root";
    public static final String PASSWORD = "4094";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
