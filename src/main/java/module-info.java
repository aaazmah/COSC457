module com.example.project_cosc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.project_cosc to javafx.fxml;
    exports com.example.project_cosc;
}