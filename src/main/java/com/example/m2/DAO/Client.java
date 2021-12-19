package com.example.m2.DAO;

import com.example.m2.model.ClientModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Client {

    DBConnection dbConnection = new DBConnection();
    ClientModel clientModel;

    ObservableList<ClientModel> clientModels = FXCollections.observableArrayList();

    public ObservableList<ClientModel> index() {
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM client";
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
            resultSet =  preparedStatement.executeQuery();

            while (resultSet.next()) {
                String badge = resultSet.getString("work_badge");
                String company = resultSet.getString("company_name");
                Date hire_date = resultSet.getDate("hire_date");
                String first_name = resultSet.getString("firstname");
                String last_name = resultSet.getString("lastname");
                String id_number = resultSet.getString("cin");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String address = resultSet.getString("addresse");
                Timestamp created_at = resultSet.getTimestamp("created_at");

                clientModels.add(new ClientModel(badge,company,hire_date,first_name,last_name,id_number,email,phone,address,created_at));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clientModels;
    }

    public void store(HashMap list){

        try{
            String query = "INSERT INTO client (`work_badge`," +
                    "`company_name`," +
                    "`hire_date`," +
                    "`firstname`," +
                    "`lastname`," +
                    "`cin`,"+
                    "`passporte`," +
                    "`phone`," +
                    "`email`," +
                    "`addresse`) VALUES (?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1,list.get("badge").toString());
            preparedStatement.setString(2,list.get("company").toString());
            preparedStatement.setDate(3,Date.valueOf(list.get("hire_date").toString()));
            preparedStatement.setString(4,(String) list.get("first_name"));
            preparedStatement.setString(5,(String) list.get("last_name"));

            if ((Boolean) list.get("cin")) {
                preparedStatement.setString(6,(String) list.get("id_number"));
                preparedStatement.setString(7,null);

            } else if ((Boolean) list.get("passport")) {
                preparedStatement.setString(7,(String) list.get("id_number"));
                preparedStatement.setString(6,null);

            }
            preparedStatement.setString(8,(String) list.get("phone"));
            preparedStatement.setString(9,(String) list.get("email"));
            preparedStatement.setString(10,(String) list.get("address"));

            preparedStatement.execute();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<ClientModel> search(String item){
            ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM client WHERE firstname LIKE '%"+item+"%' OR lastname LIKE '%"+item+"%' OR phone LIKE '%"+item+"%' OR email LIKE '%"+item+"%' OR addresse LIKE '%"+item+"%'";
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
            resultSet =  preparedStatement.executeQuery();

            while (resultSet.next()) {
                String badge = resultSet.getString("work_badge");
                String company = resultSet.getString("company_name");
                Date hire_date = resultSet.getDate("hire_date");
                String first_name = resultSet.getString("firstname");
                String last_name = resultSet.getString("lastname");
                String id_number = resultSet.getString("cin");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String address = resultSet.getString("addresse");
                Timestamp created_at = resultSet.getTimestamp("created_at");

                clientModels.add(new ClientModel(badge,company,hire_date,first_name,last_name,id_number,email,phone,address,created_at));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clientModels;
    }

    public ArrayList<String> getAllCompany(){
        ArrayList<String> companies = new ArrayList<>();
        try {
            String query = "SELECT DISTINCT company_name FROM client";
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
            ResultSet resultSet =  preparedStatement.executeQuery();

            while (resultSet.next()) {
                companies.add(resultSet.getString("company_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return companies;
    }

    public ArrayList<ClientModel> getByCompany(String company){
        ArrayList<ClientModel> companies = new ArrayList<>();

        try {
            String query = "SELECT * FROM client WHERE company_name = '"+company+"'";
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
            ResultSet resultSet =  preparedStatement.executeQuery();

            while (resultSet.next()) {
                String badge = resultSet.getString("work_badge");
                String company_name = resultSet.getString("company_name");
                Date hire_date = resultSet.getDate("hire_date");
                String first_name = resultSet.getString("firstname");
                String last_name = resultSet.getString("lastname");
                String id_number = resultSet.getString("cin");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String address = resultSet.getString("addresse");
                Timestamp created_at = resultSet.getTimestamp("created_at");

                companies.add(new ClientModel(badge,company_name,hire_date,first_name,last_name,id_number,email,phone,address,created_at));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return companies;
    }

}
