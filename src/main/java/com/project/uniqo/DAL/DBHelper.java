package com.project.uniqo.DAL;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class DBHelper {

    private static String URL = "jdbc:sqlserver://COMPVAR\\SQLEXPRESS;database=UniqoDB;integratedSecurity=true;";

    public Connection getDBConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
