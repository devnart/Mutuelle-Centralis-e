package com.example.m2.DAO;

import com.example.m2.model.Client;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

public class Stats {
    DBConnection dbConnection = new DBConnection();

    public HashMap<String, Integer> index() {
        ResultSet resultSet = null;
        HashMap<String,Integer> clients = new HashMap<>();
        try {
            String query = "SELECT date(created_at),count(work_badge) from client group by date(created_at)";
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
            resultSet =  preparedStatement.executeQuery();

            while (resultSet.next()) {
                clients.put(resultSet.getString(1) , resultSet.getInt(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clients;
    }
}
