module com.example.mt {
    requires javafx.controls;
    requires javafx.fxml;
    requires apache.log4j.extras;
    requires org.controlsfx.controls;
    requires json.simple;
    requires java.mail;
    requires bcrypt;
    requires java.sql;

    opens com.example.mt to javafx.fxml;
    exports com.example.mt;
    exports com.example.mt.controller;
    opens com.example.mt.controller to javafx.fxml;
    exports com.example.mt.model;
    opens com.example.mt.model to javafx.fxml;
}