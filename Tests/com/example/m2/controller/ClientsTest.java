package com.example.m2.controller;

import com.example.m2.DAO.Client;
import com.example.m2.model.ClientModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ClientsTest {
    Client client = new Client();

    @Test
    void getClients() {
        Assertions.assertEquals(209, client.index().size());
    }

    @Test
    void search() {
        Assertions.assertEquals(1, client.search("Hamza").size());
    }

    @Test
    void getAllCompany() {
        Assertions.assertEquals(166, client.getAllCompany().size());
    }

  /*  @Test
    void store(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ClientModel clientModel = new ClientModel("12345678","youCode", Date.valueOf("2020-12-12"),"Hamza","bouchikhi","210","s@gmail.com","123456789","safi",timestamp);
        HashMap<String, ClientModel> data = new HashMap<>();
        data.put("Client",clientModel);
        client.store(data);
        Assertions.assertEquals(210,client.index().size());

    }*/





}