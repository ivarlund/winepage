package com.project.uniqo.DAL;

import com.project.uniqo.models.Wine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WineDAO {

    @Autowired
    DBHelper dbHelper;

    @Autowired
    ProducerDAO producerDAO;

    public List<Wine> getWines2() {
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

                wine.setProducer(producerDAO.getProducerById(wine.getProducerId()));

                wines.add(wine);
            }

            return wines;
        } catch (Exception e) {
            e.printStackTrace();
            return wines;
        }

    }

    //NEW WINE SQL QUERY
    public List<Wine> getWines() {
        Statement statement = null;
        String query = "SELECT Wine.*, Grape.Name as grapename FROM Wine, Grape, WineGrape\n" +
                "WHERE Wine.Id = WineGrape.WineId\n" +
                "AND Grape.Id = WineGrape.GrapeId;";
        List<Wine> wines = new ArrayList<>();
        try {
            statement = dbHelper.getDBConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData rs = resultSet.getMetaData();
            for (int i = 0; i < rs.getColumnCount(); i++) {
                System.out.println(rs.getColumnLabel(i + 1));
            }
            while (resultSet.next()) {
                if (wineInList(wines, Integer.parseInt(resultSet.getString("Id")))) {
                    Wine wine = wines.get(Integer.parseInt(resultSet.getString("Id")) - 1);
                    wine.getGrapes().add(resultSet.getString("grapename"));
                } else {
                    wines.add(createWineModel(resultSet));
                }

            }
            return wines;
        } catch (Exception e) {
            e.printStackTrace();
            return wines;
        }

    }

    public Wine createWineModel(ResultSet resultSet) {
        Wine wine = new Wine();

        try {
            wine.setId(Integer.parseInt(resultSet.getString("Id")));
            wine.setName(resultSet.getString("Name"));
            wine.setType(resultSet.getString("Type"));
            wine.setCountry(resultSet.getString("Country"));
            wine.setRegion(resultSet.getString("Region"));
            wine.setYear(Integer.parseInt(resultSet.getString("Year")));
            wine.setDescription(resultSet.getString("Description"));
            wine.setProducerId(Integer.parseInt(resultSet.getString("ProducerId")));
            wine.getGrapes().add(resultSet.getString("grapename"));

            wine.setImgPath(resultSet.getString("imgPath"));

            wine.setProducer(producerDAO.getProducerById(wine.getProducerId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wine;
    }

    public boolean wineInList(List<Wine> wines, int Id) {
        boolean found = wines.stream()
                .anyMatch(w -> w.getId() == Id);
        return found;
    }
}
