package com.example.m2.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.m2.DAO.Client;
import com.example.m2.DAO.User;
import com.example.m2.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class Login {
    public Login(){

    }
    @FXML
    private Button button;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Label message;

    HelloApplication main = new HelloApplication();
    User user = new User();

    public void checkLogin() throws IOException {


        try
        {
            if((this.email.getText().isEmpty() || this.password.getText().isEmpty())){
                message.setText("Please fill all the fields");

            } else if (userLogin(email.getText(), password.getText())){
                message.setText("Success!");
                main.changeScene("dashboard.fxml");
            } else {
                message.setText("Wrong email or password");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean userLogin(String email, String password) {

        try {
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword(email));
            return result.verified;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
