package com.example.m2.controller;

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

    public void userLogIn(ActionEvent event) throws IOException {

        checkLogin();

        }

    public void checkLogin() throws IOException {
        HelloApplication m = new HelloApplication();
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("C:\\Users\\hamza\\Desktop\\Youcode\\B3\\m2\\src\\main\\resources\\com\\example\\m2\\json\\func.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);

            for(int i = 0; i < employeeList.size(); i++) {
                JSONObject employee = (JSONObject) employeeList.get(i);
                String email = (String) employee.get("email");
                String password = (String) employee.get("password");

                if((this.email.getText().isEmpty() || this.password.getText().isEmpty())){
                    message.setText("Please fill all the fields");
                    break;
                } else if (email.equals(this.email.getText()) && password.equals(this.password.getText())){
                    message.setText("Success!");
                    m.changeScene("dashboard.fxml");
                    break;
                } else {
                    message.setText("Wrong email or password");
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
