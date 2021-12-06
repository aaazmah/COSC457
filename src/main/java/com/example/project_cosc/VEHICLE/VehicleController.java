package com.example.project_cosc.VEHICLE;


import com.example.project_cosc.*;
import com.example.project_cosc.EMP.EmpModel;
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


public class VehicleController implements Initializable {
    @FXML
    public TableView<EmpModel> table;
    public TableColumn<EmpModel, String> col_fname;
    public TableColumn<EmpModel, String> col_lname;
    public TableColumn<EmpModel, String> col_address;
    public TableColumn<EmpModel, String> col_dob;
    public TableColumn<EmpModel, Integer> col_wage;
    public TableColumn<EmpModel, Integer> col_ssn;

    @FXML
    public TextField field_firstname;
    public TextField field_lastname;
    public TextField field_address;
    public TextField field_dob;
    public TextField field_wage;
    public TextField field_ssn;
    public TextField field_ssnDelete;


    DBUtils connectNow = new DBUtils();
    Connection connection = connectNow.getConnection();
    PreparedStatement preparedStatement;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fetchData();

    }

    public void fetchData(){

        ObservableList<EmpModel> EmpModelObservableList = FXCollections.observableArrayList();


        String query = "select firstname, lastname, address, BDATE, wage, SSN from employee order by firstname";

        try{
            Statement statement = connection.createStatement();
            ResultSet qO = statement.executeQuery(query);

            while(qO.next()){
                String queryfirstname = qO.getString("firstname");
                String querylastname = qO.getString("lastname");
                String queryaddress = qO.getString("address");
                String queryBDATE = qO.getString("BDATE");
                int querywage = qO.getInt("wage");
                int querySSN = qO.getInt("SSN");
                EmpModelObservableList.add(new EmpModel(queryfirstname,querylastname,queryaddress,queryBDATE,querySSN,  querywage));

            }

            col_fname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            col_lname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
            col_dob.setCellValueFactory(new PropertyValueFactory<>("BDATE"));
            col_wage.setCellValueFactory(new PropertyValueFactory<>("wage"));
            col_ssn.setCellValueFactory(new PropertyValueFactory<>("SSN"));

            table.setItems(EmpModelObservableList);


        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void Add(ActionEvent event){

        try {
            String st = "INSERT INTO employee ( firstname, lastname, address, ssn, wage, BDATE) VALUES (?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(st);
            preparedStatement.setString(1, field_firstname.getText());
            preparedStatement.setString(2, field_lastname.getText());
            preparedStatement.setString(3, field_address.getText());
            preparedStatement.setString(6, field_dob.getText());
            preparedStatement.setInt(5, Integer.parseInt(field_wage.getText()));
            preparedStatement.setInt(4, Integer.parseInt(field_ssn.getText()) );


            int status = preparedStatement.executeUpdate();


            if(status==1)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Employee");
                alert.setContentText("Employee was added!");
                alert.showAndWait();
                fetchData();
                field_firstname.setText("");
                field_lastname.setText("");
                field_address.setText("");
                field_dob.setText("");
                field_wage.setText("");
                field_ssn.setText("");

            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Fail");
                alert.setHeaderText("Employee");
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
            String st = "DELETE FROM `Employee` WHERE `ssn` = ?";

            preparedStatement = connection.prepareStatement(st);
            preparedStatement.setInt(1, Integer.parseInt(field_ssnDelete.getText()) );
            int status = preparedStatement.executeUpdate();


            if(status==1)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Employee");
                alert.setContentText("Employee was removed!");
                alert.showAndWait();
                fetchData();
                field_firstname.setText("");
                field_lastname.setText("");
                field_address.setText("");
                field_dob.setText("");
                field_wage.setText("");
                field_ssn.setText("");

            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fail");
                alert.setHeaderText("Employee");
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
            String st = "UPDATE Employee SET firstname = ?, lastname = ?, address=?, BDATE =?, wage =?, SSN = ? WHERE SSN = ?;";
            preparedStatement = connection.prepareStatement(st);
            preparedStatement.setString(1, field_firstname.getText());
            preparedStatement.setString(2, field_lastname.getText());
            preparedStatement.setString(3, field_address.getText());
            preparedStatement.setString(4, field_dob.getText());
            preparedStatement.setInt(5, Integer.parseInt(field_wage.getText()));
            preparedStatement.setInt(6, Integer.parseInt(field_ssn.getText()) );
            preparedStatement.setInt(7, Integer.parseInt(field_ssn.getText()) );



            int status = preparedStatement.executeUpdate();


            if(status==1)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Employee");
                alert.setContentText("Employee was changed !");
                alert.showAndWait();
                fetchData();
                field_firstname.setText("");
                field_lastname.setText("");
                field_address.setText("");
                field_dob.setText("");
                field_wage.setText("");
                field_ssn.setText("");

            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fail");
                alert.setHeaderText("Employee");
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

