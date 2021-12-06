module com.example.project_cosc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports com.example.project_cosc;
    opens com.example.project_cosc to javafx.fxml;

    exports com.example.project_cosc.EMP;
    opens com.example.project_cosc.EMP to javafx.fxml;

    exports com.example.project_cosc.WAGE;
    opens com.example.project_cosc.WAGE to javafx.fxml;

    exports com.example.project_cosc.VEHICLE;
    opens com.example.project_cosc.VEHICLE to javafx.fxml;

    exports com.example.project_cosc.EQUIPMENT;
    opens com.example.project_cosc.EQUIPMENT to javafx.fxml;

    exports com.example.project_cosc.JOB;
    opens com.example.project_cosc.JOB to javafx.fxml;

}