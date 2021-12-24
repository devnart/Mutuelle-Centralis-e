package com.example.mt.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {
    private String username;
    private String password;

    DBConnection dbConnection = new DBConnection();

    public String getPassword(String email) {
        dbConnection.getConnection();
        String pwd = null;
        try {
            String query = "SELECT password FROM officials WHERE email = '" + email + "'";
            System.out.println("wahya 1");
            System.out.println(email);
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
            ResultSet resultSet =  preparedStatement.executeQuery();

            if(resultSet.next()){
                pwd = resultSet.getString("password");
            }
            return pwd;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
