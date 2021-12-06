package com.example.project_cosc.JOB;


import com.example.project_cosc.*;
import com.example.project_cosc.JOB.JobModel;
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


public class JobController implements Initializable {
    @FXML
    public TableView<JobModel> table;
    public TableColumn<JobModel, String> col_type;
    public TableColumn<JobModel, String> col_address;
    public TableColumn<JobModel, String> col_name;
    public TableColumn<JobModel, String> col_amount;
    public TableColumn<JobModel, String> col_form;
    public TableColumn<JobModel, Integer> col_id;

    @FXML
    public TextField field_job;
    public TextField field_address;
    public TextField field_name;
    public TextField field_amount;
    public TextField field_form;
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

        ObservableList<JobModel> JobModelObservableList = FXCollections.observableArrayList();


        String query = "select jobtype, address, name, amount, formofpayment, id from job order by id";

        try{
            Statement statement = connection.createStatement();
            ResultSet qO = statement.executeQuery(query);

            while(qO.next()){
                String queryjobtype = qO.getString("jobtype");
                String queryaddress = qO.getString("address");
                String queryname = qO.getString("name");
                String queryamount = qO.getString("amount");
                String queryformofpayment = qO.getString("formofpayment");
                int queryid = qO.getInt("id");
                JobModelObservableList.add(new JobModel(queryjobtype,queryaddress,queryname,queryamount,queryformofpayment,queryid));

            }

            col_type.setCellValueFactory(new PropertyValueFactory<>("jobtype"));
            col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
            col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
            col_form.setCellValueFactory(new PropertyValueFactory<>("formofpayment"));
            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));

            table.setItems(JobModelObservableList);


        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void Add(ActionEvent event){

        try {
            String st = "INSERT INTO Job ( jobtype, address, name, amount, formofpayment, id) VALUES (?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(st);
            preparedStatement.setString(1, field_job.getText());
            preparedStatement.setString(2, field_address.getText());
            preparedStatement.setString(3, field_name.getText());
            preparedStatement.setString(4, field_amount.getText());
            preparedStatement.setString(5, field_form.getText());
            preparedStatement.setInt(6, Integer.parseInt(field_id.getText()) );


            int status = preparedStatement.executeUpdate();


            if(status==1)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Job");
                alert.setContentText("Job was added!");
                alert.showAndWait();
                fetchData();
                field_job.setText("");
                field_address.setText("");
                field_name.setText("");
                field_amount.setText("");
                field_form.setText("");
                field_id.setText("");

            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Fail");
                alert.setHeaderText("Job");
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
            String st = "DELETE FROM `Job` WHERE `id` = ?";

            preparedStatement = connection.prepareStatement(st);
            preparedStatement.setInt(1, Integer.parseInt(field_idDelete.getText()) );
            int status = preparedStatement.executeUpdate();


            if(status==1)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Job");
                alert.setContentText("Job was removed!");
                alert.showAndWait();
                fetchData();

                field_idDelete.setText("");

            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fail");
                alert.setHeaderText("Job");
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
            String st = "UPDATE Job SET jobtype = ?, address = ?, name=?, amount =?, formofpayment =?, id = ? WHERE id = ?;";
            preparedStatement = connection.prepareStatement(st);
            preparedStatement.setString(1, field_job.getText());
            preparedStatement.setString(2, field_address.getText());
            preparedStatement.setString(3, field_name.getText());
            preparedStatement.setString(4, field_amount.getText());
            preparedStatement.setString(5, field_form.getText());
            preparedStatement.setInt(6, Integer.parseInt(field_id.getText()) );
            preparedStatement.setInt(7, Integer.parseInt(field_id.getText()) );



            int status = preparedStatement.executeUpdate();


            if(status==1)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Job");
                alert.setContentText("Job was changed !");
                alert.showAndWait();
                fetchData();
                field_job.setText("");
                field_address.setText("");
                field_name.setText("");
                field_amount.setText("");
                field_form.setText("");
                field_id.setText("");

            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fail");
                alert.setHeaderText("Job");
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
