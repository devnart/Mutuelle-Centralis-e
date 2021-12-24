package com.example.mt.controller;

import com.example.mt.DAO.Client;
import com.example.mt.HelloApplication;
import com.example.mt.interfaces.DashboardInterface;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Dashboard implements DashboardInterface {

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

    JSONArray employeeList = new JSONArray();
    HelloApplication m = new HelloApplication();
    Client client = new Client();
    ValidationSupport validationSupport = new ValidationSupport();
    private static final Logger log = LogManager.getLogger(Dashboard.class);


    public void clientTab() throws IOException {

        m.changeScene("clients-view.fxml");

    }

    public void statsTab() throws IOException {

        m.changeScene("chart-view.fxml");

    }
    public void addUser() {

        validationSupport.registerValidator(fname, Validator.createRegexValidator("First Name must be between 2 and 50 characters", "^.{2,50}$", Severity.ERROR));
        validationSupport.registerValidator(lname, Validator.createRegexValidator("Last Name must be between 2 and 50 characters", "^.{2,50}$", Severity.ERROR));
        validationSupport.registerValidator(email, Validator.createRegexValidator("Email is not valid", "^\\S+@\\S+\\.\\S+$", Severity.ERROR));
        validationSupport.registerValidator(phone, Validator.createRegexValidator("Phone number must be start with 6 or 7", "^[67]\\d{8}$", Severity.ERROR));
        validationSupport.registerValidator(address, Validator.createEmptyValidator("Address is required", Severity.ERROR));
        validationSupport.registerValidator(date, Validator.createEmptyValidator("Date is required", Severity.ERROR));
        validationSupport.registerValidator(badge, Validator.createRegexValidator("Badge must be 10 characters", "^.{10}$", Severity.ERROR));
        validationSupport.registerValidator(company, Validator.createRegexValidator("First Name must be between 1 and 50 characters", "^.{1,50}$", Severity.ERROR));
        validationSupport.registerValidator(country_list, Validator.createEmptyValidator("Country is required", Severity.ERROR));

        if(cin.isSelected()){
            validationSupport.registerValidator(id_number, Validator.createRegexValidator("ID number is not valid", "^[A-Z]{2}\\d{6}$", Severity.ERROR));
        } else if(passport.isSelected()){
            validationSupport.registerValidator(id_number, Validator.createRegexValidator("ID number is not valid", "^[A-Z]{2}\\d{7}$", Severity.ERROR));
        }

        HashMap employeeDetails = new HashMap();

        employeeDetails.put("badge", badge.getText());
        employeeDetails.put("first_name", fname.getText());
        employeeDetails.put("last_name", lname.getText());
        employeeDetails.put("company",company.getText());
        employeeDetails.put("email", email.getText());
        employeeDetails.put("country_list", country_list.getValue());
        employeeDetails.put("phone", phone.getText());
        employeeDetails.put("address", address.getText());
        employeeDetails.put("hire_date", date.getValue());
        employeeDetails.put("passport", passport.isSelected());
        employeeDetails.put("cin", cin.isSelected());
        employeeDetails.put("id_number", id_number.getText());

        client.store(employeeDetails);
        log.info("Employee details stored");
        sendMail(employeeDetails.get("email").toString(),"Welcome","We are glad to have you as our client");
        log.info("Email sent");

    }

    public void initialize(){
        log.info("Dashboard initialized");
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("C:\\Users\\hamza\\Desktop\\Youcode\\B3\\mt\\src\\main\\resources\\com\\example\\mt\\json\\country.json"))
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
            log.error("Country File not found");
            e.printStackTrace();
        } catch (IOException e) {
            log.error("IO Exception", e);
            e.printStackTrace();
        } catch (ParseException e) {
            log.error("Parse Exception", e);
            e.printStackTrace();
        }
    }

    public void sendMail(String to, String subject, String body) {

        final String username = "hr.fb001@gmail.com";
        final String password = "myozwsviddybwnau";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Done - Email sent successfully");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
