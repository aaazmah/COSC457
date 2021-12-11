package com.example.project_cosc;


import java.sql.*;

public class DBUtils {
    public Connection connection;

    public Connection getConnection(){

        try{
            connection =
                    DriverManager.getConnection(
                            "jdbc:mysql://project.csrygf1sujxh.us-east-1.rds.amazonaws.com/company", "admin", "password");


        }catch(Exception e){
            e.printStackTrace();
        }

        return connection;
    }


}
