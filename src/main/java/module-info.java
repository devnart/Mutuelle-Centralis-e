module com.example.m2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires org.controlsfx.controls;
    requires java.sql;
    requires bcrypt;
    requires apache.log4j.extras;
    requires java.mail;


    opens com.example.m2 to javafx.fxml;
    exports com.example.m2;
    exports com.example.m2.controller;
    opens com.example.m2.controller to javafx.fxml;
    exports com.example.m2.model;
    opens com.example.m2.model to javafx.fxml;

}