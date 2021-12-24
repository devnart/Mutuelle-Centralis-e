package com.example.mt.interfaces;

import com.example.mt.model.ClientModel;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface ClientInterface {

    public ObservableList<ClientModel> getClients();

    public ObservableList<ClientModel> search(String search);

    public ObservableList<ClientModel> getByCompany(String company);

    public ArrayList<String> getCompanies();

}