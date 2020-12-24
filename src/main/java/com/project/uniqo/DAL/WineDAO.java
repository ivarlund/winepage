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
    public List<Wine> getWines2() {
        Statement statement = null;
        String query = "Select Wine.Id, Wine.Name, Wine.Type, Wine.Country, Wine.Region, Wine.Year, Wine.Description, Wine.ProducerId, Wine.imgPath, Grape.Name as grapename from Wine, Grape, WineGrape\n" +
                "Where Wine.Id = WineGrape.WineId\n" +
                "and Grape.Id = WineGrape.GrapeId;";
        List<Wine> wines = new ArrayList<>();
        try {
            statement = dbHelper.getDBConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData rs = resultSet.getMetaData();
            for(int i = 0; i < rs.getColumnCount(); i++) {
                System.out.println(rs.getColumnLabel(i+1));
            }
            while (resultSet.next()) {
                boolean found = wines.stream()
                        .anyMatch(w -> {
                            try {
                                return w.getId() == Integer.parseInt(resultSet.getString("Id"));
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            return false;
                        });

                if (found) {
                    Wine tmp = wines.get(Integer.parseInt(resultSet.getString("Id")) - 1);
                    tmp.getGrapes().add(resultSet.getString("grapename"));
                } else {
                    Wine wine = new Wine();

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

                    wines.add(wine);
                }

            }
            for(Wine wine : wines) {
                System.out.println(wine.getName());
                System.out.println(wine.getImgPath());
                for(String s: wine.getGrapes()) {
                    System.out.println(s);
                }
            }

            return wines;
        } catch (Exception e) {
            e.printStackTrace();
            return wines;
        }

    }
}
