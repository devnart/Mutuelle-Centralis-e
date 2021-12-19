package com.example.m2.model;

import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class ClientModel {
    private String badge;
    private String company;
    private String fname;
    private String lname;
    private String id;
    private String email;
    private String tele;
    private String address;
    private Date date;
    private Timestamp created_at;

    public ClientModel(String badge, String company, Date date, String fname, String lname, String id, String email, String tele, String address, Timestamp created_at) {
        this.badge = badge;
        this.company = company;
        this.fname = fname;
        this.lname = lname;
        this.id = id;
        this.email = email;
        this.tele = tele;
        this.address = address;
        this.date = date;
        this.created_at = created_at;
    }

    public String getBadge() {
        return badge;
    }

    public Timestamp getCreated_at(){
        return created_at;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    //    public void GetClients() {
//        JSONParser jsonParser = new JSONParser();
//        try (FileReader reader = new FileReader("C:\\Users\\hamza\\Desktop\\Youcode\\B3\\m2\\src\\main\\resources\\com\\example\\m2\\json\\employee.json"))
//        {
//            //Read JSON file
//            Object obj = jsonParser.parse(reader);
//
//            JSONArray clients = (JSONArray) obj;
//
//            for (Object client : clients) {
//                JSONObject clientObject = (JSONObject) client;
//                this.badge = (String) clientObject.get("badge");
//                this.company = (String) clientObject.get("company");
//                this.fname = (String) clientObject.get("first_name");
//                this.lname = (String) clientObject.get("last_name");
//                this.id = (String) clientObject.get("id_number");
//                this.email = (String) clientObject.get("email");
//                this.tele = (String) clientObject.get("phone");
//                this.date = (String) clientObject.get("date_of_birth");
//                this.address = (String) clientObject.get("address");
//            }
//
//            clients.add(new Client(badge, company, fname, lname, id, email, tele, address, date));
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
}
