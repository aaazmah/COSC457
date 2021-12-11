package com.example.project_cosc.WAGE;


import com.example.project_cosc.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class WageController implements Initializable {
    @FXML
    public TableView<WageModel> table;
    public TableColumn<WageModel, Integer> col_rate;
    public TableColumn<WageModel, Integer> col_worked;
    public TableColumn<WageModel, Integer> col_total;
    public TableColumn<WageModel, Integer> col_pay;
    public TableColumn<WageModel, Integer> col_ytd;
    public TableColumn<WageModel, Integer> col_id;

    @FXML
    public TextField field_hourlyRate;
    public TextField field_hourlyWorked;
    public TextField field_weeklyTotal;
    public TextField field_weeklyPay;
    public TextField field_ytd;
    public TextField field_id;
    public TextField field_idDelete;


    DBUtils connectNow = new DBUtils();
    Connection connection = connectNow.getConnection();
    PreparedStatement preparedStatement;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fetchData();

    }

    public void fetchData(){

        ObservableList<WageModel> WageModelObservableList = FXCollections.observableArrayList();


        String query = "select hourlyrate, hoursworked, weeklytotal, weeklypay, ytd, id from wage order by id";

        try{
            Statement statement = connection.createStatement();
            ResultSet qO = statement.executeQuery(query);

            while(qO.next()){
                int queryhourlyrate= qO.getInt("hourlyrate");
                int queryhoursworked = qO.getInt("hoursworked");
                int queryweeklytotal = qO.getInt("weeklytotal");
                int queryweeklypay = qO.getInt("weeklypay");
                int queryytd = qO.getInt("ytd");
                int queryid = qO.getInt("id");
                WageModelObservableList.add(new WageModel(queryhourlyrate,queryhoursworked,queryweeklytotal,queryweeklypay,queryytd,queryid));

            }

            col_rate.setCellValueFactory(new PropertyValueFactory<>("hourlyrate"));
            col_worked.setCellValueFactory(new PropertyValueFactory<>("hoursworked"));
            col_total.setCellValueFactory(new PropertyValueFactory<>("weeklytotal"));
            col_pay.setCellValueFactory(new PropertyValueFactory<>("weeklypay"));
            col_ytd.setCellValueFactory(new PropertyValueFactory<>("ytd"));
            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));

            table.setItems(WageModelObservableList);


        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void Add(ActionEvent event){

        try {
            String st = "INSERT INTO wage ( hourlyrate, hoursworked, weeklytotal, weeklypay, ytd, id) VALUES (?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(st);
            preparedStatement.setInt(1, Integer.parseInt(field_hourlyRate.getText()));
            preparedStatement.setInt(2, Integer.parseInt(field_hourlyWorked.getText()));
            preparedStatement.setInt(3, Integer.parseInt(field_weeklyTotal.getText()));
            preparedStatement.setInt(4, Integer.parseInt(field_weeklyPay.getText()));
            preparedStatement.setInt(5, Integer.parseInt(field_ytd.getText()));
            preparedStatement.setInt(6, Integer.parseInt(field_id.getText()) );


            int status = preparedStatement.executeUpdate();


            if(status==1)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Wage");
                alert.setContentText("Wage was added!");
                alert.showAndWait();
                fetchData();
                field_hourlyRate.setText("");
                field_hourlyWorked.setText("");
                field_weeklyTotal.setText("");
                field_weeklyPay.setText("");
                field_ytd.setText("");
                field_id.setText("");

            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Fail");
                alert.setHeaderText("Wage");
                alert.setContentText("Something went wrong! Check again please");
                alert.showAndWait();
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    void Delete(ActionEvent event){

        try {
            String st = "DELETE FROM `wage` WHERE `id` = ?";

            preparedStatement = connection.prepareStatement(st);
            preparedStatement.setInt(1, Integer.parseInt(field_idDelete.getText()) );
            int status = preparedStatement.executeUpdate();


            if(status==1)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Wage");
                alert.setContentText("Wage was removed!");
                alert.showAndWait();
                fetchData();
                field_idDelete.setText("");

            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fail");
                alert.setHeaderText("Wage");
                alert.setContentText("Something went wrong! Check again please");
                alert.showAndWait();
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    void Edit(ActionEvent event){
        try {
            String st = "UPDATE wage SET hourlyrate = ?, hoursworked = ?, weeklytotal=?, weeklypay =?, ytd =?, id = ? WHERE id = ?;";
            preparedStatement = connection.prepareStatement(st);
            preparedStatement.setInt(1, Integer.parseInt(field_hourlyRate.getText()));
            preparedStatement.setInt(2, Integer.parseInt(field_hourlyWorked.getText()));
            preparedStatement.setInt(3, Integer.parseInt(field_weeklyTotal.getText()));
            preparedStatement.setInt(4, Integer.parseInt(field_weeklyPay.getText()));
            preparedStatement.setInt(5, Integer.parseInt(field_ytd.getText()));
            preparedStatement.setInt(6, Integer.parseInt(field_id.getText()) );
            preparedStatement.setInt(7, Integer.parseInt(field_id.getText()) );



            int status = preparedStatement.executeUpdate();


            if(status==1)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Wage");
                alert.setContentText("Wage was changed !");
                alert.showAndWait();
                fetchData();
                field_hourlyRate.setText("");
                field_hourlyWorked.setText("");
                field_weeklyTotal.setText("");
                field_weeklyPay.setText("");
                field_ytd.setText("");
                field_id.setText("");

            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fail");
                alert.setHeaderText("Wage");
                alert.setContentText("Something went wrong! Check again please");
                alert.showAndWait();
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    Stage stage;
    @FXML
    void new_emp(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(EmpApplication.class.getResource("Employees.fxml"));

        stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Company");
        stage.setScene(new Scene(parent));
        ((Node)(event.getSource())).getScene().getWindow().hide();

        stage.show();
    }

    @FXML
    void new_equipment(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(EquipmentApplication.class.getResource("Equipment.fxml"));

        stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Company");
        stage.setScene(new Scene(parent));
        ((Node)(event.getSource())).getScene().getWindow().hide();

        stage.show();
    }

    @FXML
    void new_vehicle(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(VehicleApplication.class.getResource("Vehicle.fxml"));

        stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Company");
        stage.setScene(new Scene(parent));
        ((Node)(event.getSource())).getScene().getWindow().hide();

        stage.show();
    }

    @FXML
    void new_job(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(JobApplication.class.getResource("Job.fxml"));

        stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Company");
        stage.setScene(new Scene(parent));
        ((Node)(event.getSource())).getScene().getWindow().hide();

        stage.show();
    }

    @FXML
    void new_wage(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(WageApplication.class.getResource("Wage.fxml"));

        stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Company");
        stage.setScene(new Scene(parent));
        ((Node)(event.getSource())).getScene().getWindow().hide();

        stage.show();
    }

}
