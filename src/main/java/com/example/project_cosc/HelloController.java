package com.example.project_cosc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {


    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) {
        DBUtils connectNow = new DBUtils();
        Connection connection = connectNow.getConnection();

        String query = "SELECT * FROM Employee";

        try{
            Statement statement = connection.createStatement();
            ResultSet qO = statement.executeQuery(query);

            while(qO.next() ){
                welcomeText.setText(qO.getString("firstname"));

            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
