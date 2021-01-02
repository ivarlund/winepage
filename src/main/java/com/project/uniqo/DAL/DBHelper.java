package com.project.uniqo.DAL;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class DBHelper {

    public Connection getDBConnection() {
        try {
            String url = "jdbc:sqlserver://COMPVAR\\SQLEXPRESS;database=UniqoDB;integratedSecurity=true;";
            Connection conn = DriverManager.getConnection(url);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
