package com.example.project_cosc.VEHICLE;


import com.example.project_cosc.*;
import com.example.project_cosc.VEHICLE.VehicleModel;
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
    public TableView<VehicleModel> table;
    public TableColumn<VehicleModel, String> col_brand;
    public TableColumn<VehicleModel, String> col_model;
    public TableColumn<VehicleModel, String> col_fuel;
    public TableColumn<VehicleModel, String> col_name;
    public TableColumn<VehicleModel, String> col_func;
    public TableColumn<VehicleModel, Integer> col_id;

    @FXML
    public TextField field_brand;
    public TextField field_model;
    public TextField field_fuel;
    public TextField field_name;
    public TextField field_func;
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

        ObservableList<VehicleModel> VehicleModelObservableList = FXCollections.observableArrayList();


        String query = "SELECT `vehicle`.`brand`,\n" +
                "    `vehicle`.`model`,\n" +
                "    `vehicle`.`fueltype`,\n" +
                "    `vehicle`.`name`,\n" +
                "    `vehicle`.`functional`,\n" +
                "    `vehicle`.`id`\n" +
                "FROM `company`.`vehicle`";

        try{
            Statement statement = connection.createStatement();
            ResultSet qO = statement.executeQuery(query);

            while(qO.next()){
                String querybrand = qO.getString("brand");
                String querymodel = qO.getString("model");
                String queryfield_fuel = qO.getString("fueltype");
                String queryname = qO.getString("name");
                String queryfunctional = qO.getString("functional");
                int queryid = qO.getInt("id");
                VehicleModelObservableList.add(new VehicleModel(querybrand,querymodel,queryfield_fuel,queryname,queryfunctional,queryid));

            }

            col_brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
            col_model.setCellValueFactory(new PropertyValueFactory<>("model"));
            col_fuel.setCellValueFactory(new PropertyValueFactory<>("fueltype"));
            col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_func.setCellValueFactory(new PropertyValueFactory<>("functional"));
            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));

            table.setItems(VehicleModelObservableList);


        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void Add(ActionEvent event){

        try {
            String st = "INSERT INTO vehicle ( brand, model, fueltype, name, functional, id) VALUES (?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(st);
            preparedStatement.setString(1, field_brand.getText());
            preparedStatement.setString(2, field_model.getText());
            preparedStatement.setString(3, field_fuel.getText());
            preparedStatement.setString(4, field_name.getText());
            preparedStatement.setString(5, field_func.getText());
            preparedStatement.setInt(6, Integer.parseInt(field_id.getText()) );


            int status = preparedStatement.executeUpdate();


            if(status==1)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Vehicle");
                alert.setContentText("Vehicle was added!");
                alert.showAndWait();
                fetchData();
                field_brand.setText("");
                field_model.setText("");
                field_fuel.setText("");
                field_name.setText("");
                field_func.setText("");
                field_id.setText("");

            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Fail");
                alert.setHeaderText("Vehicle");
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
            String st = "DELETE FROM `vehicle` WHERE `id` = ?";

            preparedStatement = connection.prepareStatement(st);
            preparedStatement.setInt(1, Integer.parseInt(field_idDelete.getText()) );
            int status = preparedStatement.executeUpdate();


            if(status==1)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Vehicle");
                alert.setContentText("Vehicle was removed!");
                alert.showAndWait();
                fetchData();
                field_idDelete.setText("");

            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fail");
                alert.setHeaderText("Vehicle");
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
            String st = "UPDATE vehicle SET brand = ?, model = ?, fueltype=?, name =?, functional =?, id = ? WHERE id = ?;";
            preparedStatement = connection.prepareStatement(st);
            preparedStatement.setString(1, field_brand.getText());
            preparedStatement.setString(2, field_model.getText());
            preparedStatement.setString(3, field_fuel.getText());
            preparedStatement.setString(4, field_name.getText());
            preparedStatement.setString(5, field_func.getText());
            preparedStatement.setInt(6, Integer.parseInt(field_id.getText()) );
            preparedStatement.setInt(7, Integer.parseInt(field_id.getText()) );



            int status = preparedStatement.executeUpdate();


            if(status==1)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Vehicle");
                alert.setContentText("Vehicle was changed !");
                alert.showAndWait();
                fetchData();
                field_brand.setText("");
                field_model.setText("");
                field_fuel.setText("");
                field_name.setText("");
                field_func.setText("");
                field_id.setText("");

            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fail");
                alert.setHeaderText("Vehicle");
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
