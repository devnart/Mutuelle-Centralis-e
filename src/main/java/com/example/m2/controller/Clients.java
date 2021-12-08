package com.example.m2.controller;

import com.example.m2.HelloApplication;
import com.example.m2.model.Client;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class Clients implements Initializable {

    @FXML
    private TableColumn<Client, String> badgeCol;
    @FXML
    private TableColumn<Client, String> companyCol;
    @FXML
    private TableColumn<Client, String> dateCol;
    @FXML
    private TableColumn<Client, String> fnameCol;
    @FXML
    private TableColumn<Client, String> lnameCol;
    @FXML
    private TableColumn<Client, String> idCol;
    @FXML
    private TableColumn<Client, String> teleCol;
    @FXML
    private TableColumn<Client, String> emailCol;
    @FXML
    private TableColumn<Client, String> addressCol;
    @FXML
    private TableView<Client> table;

    public void parseUsers() throws IOException {

        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("C:\\Users\\hamza\\Desktop\\Youcode\\B3\\m2\\src\\main\\resources\\com\\example\\m2\\json\\employee.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray country = (JSONArray) obj;

            for(int i=0;i<country.size();i++){
                JSONObject country_obj = (JSONObject) country.get(i);
                String badge = (String) country_obj.get("badge");
                String company = (String) country_obj.get("company");
                String date = (String) country_obj.get("date_of_birth");
                String fname = (String) country_obj.get("first_name");
                String lname = (String) country_obj.get("last_name");
                String id = (String) country_obj.get("id_number");
                String tele = (String) country_obj.get("phone");
                String email = (String) country_obj.get("email");
                String address = (String) country_obj.get("address");

                Client client = new Client(badge,company,date,fname,lname,id,tele,email,address);
                clients.add(client);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    ObservableList<Client> clients = FXCollections.observableArrayList();

    public void addTab() throws IOException {

        HelloApplication m = new HelloApplication();
        m.changeScene("dashboard.fxml");

    }

    @Override
    public void initialize(URL url, java.util.ResourceBundle resources) {

        try {
            parseUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }

        badgeCol.setCellValueFactory(new PropertyValueFactory<Client,String>("badge"));
        companyCol.setCellValueFactory(new PropertyValueFactory<Client,String>("company"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Client,String>("date"));
        fnameCol.setCellValueFactory(new PropertyValueFactory<Client,String>("fname"));
        lnameCol.setCellValueFactory(new PropertyValueFactory<Client,String>("lname"));
        idCol.setCellValueFactory(new PropertyValueFactory<Client,String>("id"));
        teleCol.setCellValueFactory(new PropertyValueFactory<Client,String>("tele"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Client,String>("email"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Client,String>("address"));

        table.setItems(clients);
    }






}
