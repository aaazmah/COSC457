package com.example.project_cosc.EQUIPMENT;

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


public class EquipmentController implements Initializable {
    @FXML
    public TableView<EquipmentModel> table;
    public TableColumn<EquipmentModel, String> col_model;
    public TableColumn<EquipmentModel, String> col_brand;
    public TableColumn<EquipmentModel, String> col_year;
    public TableColumn<EquipmentModel, String> col_name;
    public TableColumn<EquipmentModel, String> col_functional;
    public TableColumn<EquipmentModel, String> col_fuel;
    public TableColumn<EquipmentModel, String> col_required;
    public TableColumn<EquipmentModel, Integer> col_id;

    @FXML
    public TextField field_eModel;
    public TextField field_eBrand;
    public TextField field_year;
    public TextField field_name;
    public TextField field_functional;
    public TextField field_fuel;
    public TextField field_required;
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

        ObservableList<EquipmentModel> EquipmentModelObservableList = FXCollections.observableArrayList();


        String query = "select model, brand, year, name, functional, fueltype, required, id from Equipment order by id";

        try{
            Statement statement = connection.createStatement();
            ResultSet qO = statement.executeQuery(query);

            while(qO.next()){
                String querymodel= qO.getString("model");
                String querybrand = qO.getString("brand");
                String queryyear = qO.getString("year");
                String queryname = qO.getString("name");
                String queryfunctional = qO.getString("functional");
                String queryfueltype = qO.getString("fueltype");
                String queryrequired = qO.getString("required");
                int queryid = qO.getInt("id");
                EquipmentModelObservableList.add(new EquipmentModel(querymodel,querybrand,queryyear,queryname,queryfunctional,queryfueltype, queryrequired, queryid));

            }

            col_model.setCellValueFactory(new PropertyValueFactory<>("model"));
            col_brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
            col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
            col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_functional.setCellValueFactory(new PropertyValueFactory<>("functional"));
            col_fuel.setCellValueFactory(new PropertyValueFactory<>("fueltype"));
            col_required.setCellValueFactory(new PropertyValueFactory<>("required"));
            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));

            table.setItems(EquipmentModelObservableList);


        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void Add(ActionEvent event){

        try {
            String st = "INSERT INTO Equipment ( model, brand, year, name, functional, fueltype,required, id) VALUES (?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(st);
            preparedStatement.setString(1, field_eModel.getText());
            preparedStatement.setString(2, field_eBrand.getText());
            preparedStatement.setString(3, field_year.getText());
            preparedStatement.setString(4, field_name.getText());
            preparedStatement.setString(5, field_functional.getText());
            preparedStatement.setString(6, field_fuel.getText() );
            preparedStatement.setString(7, field_required.getText() );
            preparedStatement.setInt(8, Integer.parseInt(field_id.getText()));




            int status = preparedStatement.executeUpdate();


            if(status==1)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Equipment");
                alert.setContentText("Equipment was added!");
                alert.showAndWait();
                fetchData();
                field_eModel.setText("");
                field_eBrand.setText("");
                field_year.setText("");
                field_name.setText("");
                field_functional.setText("");
                field_fuel.setText("");
                field_required.setText("");
                field_id.setText("");

            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Fail");
                alert.setHeaderText("Equipment");
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
            String st = "DELETE FROM `Equipment` WHERE `id` = ?";

            preparedStatement = connection.prepareStatement(st);
            preparedStatement.setInt(1, Integer.parseInt(field_idDelete.getText()) );
            int status = preparedStatement.executeUpdate();


            if(status==1)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Equipment");
                alert.setContentText("Equipment was removed!");
                alert.showAndWait();
                fetchData();
                field_eModel.setText("");
                field_eBrand.setText("");
                field_year.setText("");
                field_name.setText("");
                field_functional.setText("");
                field_fuel.setText("");
                field_required.setText("");
                field_id.setText("");

            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fail");
                alert.setHeaderText("Equipment");
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
            String st = "UPDATE Equipment SET model = ?, brand = ?, year=?, name =?, functional =?, fueltype = ?, required =?, id = ? WHERE id = ?;";
            preparedStatement = connection.prepareStatement(st);
            preparedStatement.setString(1, field_eModel.getText());
            preparedStatement.setString(2, field_eBrand.getText());
            preparedStatement.setString(3, field_year.getText());
            preparedStatement.setString(4, field_name.getText());
            preparedStatement.setString(5, field_functional.getText());
            preparedStatement.setString(6, field_fuel.getText() );
            preparedStatement.setString(7, field_required.getText());
            preparedStatement.setInt(8, Integer.parseInt(field_id.getText()));
            preparedStatement.setInt(9, Integer.parseInt(field_id.getText()));



            int status = preparedStatement.executeUpdate();


            if(status==1)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Equipment");
                alert.setContentText("Equipment was changed !");
                alert.showAndWait();
                fetchData();
                field_eModel.setText("");
                field_eBrand.setText("");
                field_year.setText("");
                field_name.setText("");
                field_functional.setText("");
                field_fuel.setText("");
                field_required.setText("");
                field_id.setText("");

            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fail");
                alert.setHeaderText("Equipment");
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
