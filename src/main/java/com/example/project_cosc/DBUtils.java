package com.example.project_cosc;


import java.sql.*;

public class DBUtils {
    public Connection connection;

    public Connection getConnection(){

        try{
            connection =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/company", "root", "root");


        }catch(Exception e){
            e.printStackTrace();
        }

        return connection;
    }


}
