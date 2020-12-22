package com.project.uniqo.DAL;

import com.project.uniqo.models.Wine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WineDAO {

    private DBHelper dbHelper = new DBHelper();

    public List<Wine> getWines() {
        Statement statement = null;
        String query = "SELECT * FROM Wine";
        List<Wine> wines = new ArrayList<>();
        try {
            statement = dbHelper.getDBConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Wine wine = new Wine();

                wine.setId(Integer.parseInt(resultSet.getString("Id")));
                wine.setName(resultSet.getString("Name"));
                wine.setType(resultSet.getString("Type"));
                wine.setCountry(resultSet.getString("Country"));
                wine.setRegion(resultSet.getString("Region"));
                wine.setYear(Integer.parseInt(resultSet.getString("Year")));
                wine.setDescription(resultSet.getString("Description"));
                wine.setProducerId(Integer.parseInt(resultSet.getString("ProducerId")));

                wines.add(wine);
            }

            return wines;
        } catch (Exception e) {
            e.printStackTrace();
            return wines;
        }

    }
}
