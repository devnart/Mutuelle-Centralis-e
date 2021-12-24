package com.example.mt.controller;


import com.example.mt.HelloApplication;

import com.example.mt.interfaces.ClientInterface;
import com.example.mt.model.ClientModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.SearchableComboBox;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Clients implements Initializable, ClientInterface {

    @FXML
    private TableColumn<ClientModel, String> badgeCol;
    @FXML
    private TableColumn<ClientModel, String> companyCol;
    @FXML
    private TableColumn<ClientModel, String> dateCol;
    @FXML
    private TableColumn<ClientModel, String> fnameCol;
    @FXML
    private TableColumn<ClientModel, String> lnameCol;
    @FXML
    private TableColumn<ClientModel, String> idCol;
    @FXML
    private TableColumn<ClientModel, String> teleCol;
    @FXML
    private TableColumn<ClientModel, String> emailCol;
    @FXML
    private TableColumn<ClientModel, String> addressCol;
    @FXML
    private TableColumn<ClientModel, String> createdCol;
    @FXML
    private TableView<ClientModel> table;
    @FXML
    private TextField searchInput;
    @FXML
    private Button searchBtn;
    @FXML
    private SearchableComboBox<String> companies;

    HelloApplication m = new HelloApplication();
    private static final Logger log = LogManager.getLogger(Clients.class);

    com.example.mt.DAO.Client client = new com.example.mt.DAO.Client();

    public void addTab() throws IOException {

        m.changeScene("dashboard.fxml");

    }

    public void statsTab() throws IOException {

        m.changeScene("chart-view.fxml");

    }

    public ObservableList<ClientModel> getClients(){

        return client.index();


    }

    @Override
    public void initialize(URL url, java.util.ResourceBundle resources) {

        ObservableList<String> companiesList = FXCollections.observableArrayList(getCompanies());
        companies.setItems(companiesList);

        badgeCol.setCellValueFactory(new PropertyValueFactory<ClientModel,String>("badge"));
        companyCol.setCellValueFactory(new PropertyValueFactory<ClientModel,String>("company"));
        dateCol.setCellValueFactory(new PropertyValueFactory<ClientModel,String>("date"));
        fnameCol.setCellValueFactory(new PropertyValueFactory<ClientModel,String>("fname"));
        lnameCol.setCellValueFactory(new PropertyValueFactory<ClientModel,String>("lname"));
        idCol.setCellValueFactory(new PropertyValueFactory<ClientModel,String>("id"));
        teleCol.setCellValueFactory(new PropertyValueFactory<ClientModel,String>("tele"));
        emailCol.setCellValueFactory(new PropertyValueFactory<ClientModel,String>("email"));
        addressCol.setCellValueFactory(new PropertyValueFactory<ClientModel,String>("address"));
        createdCol.setCellValueFactory(new PropertyValueFactory<ClientModel,String>("created_at"));

        table.setItems(getClients());
    }

    public ObservableList<ClientModel> search(String search){
        return client.search(search);
    }

    public ObservableList<ClientModel> getByCompany(String company){
        ObservableList<ClientModel> byCompany = FXCollections.observableArrayList(client.getByCompany(company));
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
        log.info("Getting companies");
        return client.getAllCompany();
    }

}
