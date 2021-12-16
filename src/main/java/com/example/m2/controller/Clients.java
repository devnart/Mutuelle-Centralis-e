package com.example.m2.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.m2.HelloApplication;
import com.example.m2.model.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.SearchableComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

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
    private TableColumn<Client, String> createdCol;
    @FXML
    private TableView<Client> table;
    @FXML
    private TextField searchInput;
    @FXML
    private Button searchBtn;
    @FXML
    private SearchableComboBox<String> companies;

    HelloApplication m = new HelloApplication();

    com.example.m2.DAO.Client client = new com.example.m2.DAO.Client();
    public void addTab() throws IOException {

        m.changeScene("dashboard.fxml");


    }

    public void statsTab() throws IOException {

        m.changeScene("chart-view.fxml");

    }

    public ObservableList<Client> getClients(){

        return client.index();


    }

    @Override
    public void initialize(URL url, java.util.ResourceBundle resources) {

        ObservableList<String> companiesList = FXCollections.observableArrayList(getCompanies());
        companies.setItems(companiesList);

        badgeCol.setCellValueFactory(new PropertyValueFactory<Client,String>("badge"));
        companyCol.setCellValueFactory(new PropertyValueFactory<Client,String>("company"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Client,String>("date"));
        fnameCol.setCellValueFactory(new PropertyValueFactory<Client,String>("fname"));
        lnameCol.setCellValueFactory(new PropertyValueFactory<Client,String>("lname"));
        idCol.setCellValueFactory(new PropertyValueFactory<Client,String>("id"));
        teleCol.setCellValueFactory(new PropertyValueFactory<Client,String>("tele"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Client,String>("email"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Client,String>("address"));
        createdCol.setCellValueFactory(new PropertyValueFactory<Client,String>("created_at"));

        table.setItems(getClients());
    }

    public ObservableList<Client> search(String search){
        return client.search(search);
    }

    public ObservableList<Client> getByCompany(String company){
        ObservableList<Client> byCompany = FXCollections.observableArrayList(client.getByCompany(company));
        return byCompany;
    }

    public void handleSearch() throws IOException {
            table.getItems().clear();
            table.setItems(search(searchInput.getText()));
    }

    public void handleSearchByCompany() throws IOException {
        table.getItems().clear();
        table.setItems(getByCompany(companies.getValue()));
    }

    public ArrayList<String> getCompanies(){
        return client.getAllCompany();
    }

}
