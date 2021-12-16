package com.example.m2.controller;

import com.example.m2.DAO.Stats;
import com.example.m2.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Statis implements Initializable {

    HelloApplication m = new HelloApplication();

    public void clientTab() throws IOException {

        m.changeScene("clients-view.fxml");

    }

    public void statsTab() throws IOException {

        m.changeScene("chart-view.fxml");

    }

    public void addTab() throws IOException {

        m.changeScene("dashboard.fxml");

    }

    @FXML
    LineChart<String, Number> lineChart;

    Stats stats = new Stats();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Series 1");

        // foreach item in the hashmap
        for (String key : stats.index().keySet()) {
          series.getData().add(new XYChart.Data<>(key, stats.index().get(key)));
        }

        lineChart.getData().add(series);

    }
}
