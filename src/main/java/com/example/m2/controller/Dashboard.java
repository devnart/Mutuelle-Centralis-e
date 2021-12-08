package com.example.m2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import impl.org.controlsfx.*;
public class Dashboard {

    public Dashboard(){

    }


    @FXML
    private ComboBox<String> country_list;

    @FXML
    private TextField badge;

    @FXML
    private TextField company;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField email;

    @FXML
    private TextField phone;

    @FXML
    private TextArea address;

    @FXML
    private DatePicker date;

    @FXML
    private RadioButton passport;

    @FXML
    private RadioButton cin;

    @FXML
    private TextField id_number;

    @FXML
    private Button button;

ValidationSupport validationSupport = new ValidationSupport();

    public void addUser() {

        validationSupport.registerValidator(fname, Validator.createRegexValidator("First Name must be between 2 and 20 characters", "^.{2,20}$", Severity.ERROR));
        validationSupport.registerValidator(lname, Validator.createRegexValidator("Last Name must be between 2 and 20 characters", "^.{2,20}$", Severity.ERROR));
        JSONObject employeeDetails = new JSONObject();
        employeeDetails.put("badge", badge.getText());
        employeeDetails.put("first_name", fname.getText());
        employeeDetails.put("last_name", lname.getText());
        employeeDetails.put("company",company.getText());
        employeeDetails.put("email", email.getText());
        employeeDetails.put("phone", phone.getText());
        employeeDetails.put("address", address.getText());
        employeeDetails.put("date_of_birth", date.getValue().toString());
        employeeDetails.put("passport", passport.isSelected());
        employeeDetails.put("cin", cin.isSelected());
        employeeDetails.put("id_number", id_number.getText());

        System.out.println(employeeDetails);

        JSONArray employeeList = new JSONArray();
        employeeList.add(employeeDetails);

        try (FileWriter file = new FileWriter("src/main/resources/com/example/m2/json/employee.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(employeeList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize(){

        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("C:\\Users\\hamza\\Desktop\\Youcode\\B3\\m2\\src\\main\\resources\\com\\example\\m2\\json\\country.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray country = (JSONArray) obj;

            for(int i=0;i<country.size();i++){
                JSONObject country_obj = (JSONObject) country.get(i);
                String country_code = (String) country_obj.get("dial_code");

                country_list.getItems().add(country_code);
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
