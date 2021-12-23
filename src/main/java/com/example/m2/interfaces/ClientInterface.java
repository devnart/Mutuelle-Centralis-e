package com.example.m2.interfaces;

import com.example.m2.model.ClientModel;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;

public interface ClientInterface {

    public ObservableList<ClientModel> getClients();

    public ObservableList<ClientModel> search(String search);

    public ObservableList<ClientModel> getByCompany(String company);

    public ArrayList<String> getCompanies();

}