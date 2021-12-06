package com.example.project_cosc.LOGIN;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.project_cosc.DBUtils;
import com.example.project_cosc.EmpApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class LogInController implements Initializable {


    @FXML
    private TextField User;
    @FXML
    private TextField Password;
    @FXML
    private Button logInBtn;

    DBUtils connectNow = new DBUtils();
    Connection connection = connectNow.getConnection();
    PreparedStatement preparedStatement;
    Stage stage;


    @FXML
    public void handleButtonAction(MouseEvent event) {

        if (event.getSource() == logInBtn) {
            if (logIn().equals("Success")) {
                try {

                    Parent parent = FXMLLoader.load(EmpApplication.class.getResource("Employees.fxml"));

                    stage = new Stage(StageStyle.DECORATED);
                    stage.setTitle("Company");
                    stage.setScene(new Scene(parent));
                    ((Node)(event.getSource())).getScene().getWindow().hide();

                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }


    private String logIn() {
        String status = "Success";
        String email = User.getText();
        String password = Password.getText();
        if(email.isEmpty() || password.isEmpty()) {
            status = "Error";
        } else {

            String sql = "SELECT * FROM admin Where email = ? and password = ?";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }

        return status;
    }

}