package com.project.uniqo.DAL;

import com.project.uniqo.models.Wine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Repository
public class WineDAO {

    @Autowired
    DBHelper dbHelper;

    @Autowired
    ProducerDAO producerDAO;

    public void addWine(Wine wine) {
        Statement statement = null;
        String query = "INSERT INTO Wine (Name, Type, Country, Region, Year, Description, ProducerId) " +
                "VALUES ('" + wine.getName() + "','" + wine.getType() + "','" + wine.getCountry() + "','" +
                wine.getRegion() + "'," + wine.getYear() + ",'" + wine.getDescription() + "'," + wine.getProducerId() + "');";
        try {
            statement = dbHelper.getDBConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println(resultSet.getString("Name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<Integer, Wine> getWines() {
        Statement statement = null;
        String query = "SELECT Wine.*, Grape.Name as grapename FROM Wine, Grape, WineGrape\n" +
                "WHERE Wine.Id = WineGrape.WineId\n" +
                "AND Grape.Id = WineGrape.GrapeId;";
        HashMap<Integer, Wine> wines = new HashMap<>();
        try {
            statement = dbHelper.getDBConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (wines.containsKey(Integer.parseInt(resultSet.getString("Id")))) {
                    wines.get(Integer.parseInt(resultSet.getString("Id"))).getGrapes().add(resultSet.getString("grapename"));
                } else {
                    wines.put(Integer.parseInt(resultSet.getString("Id")), createWineModel(resultSet));
                }
            }
            return wines;
        } catch (Exception e) {
            e.printStackTrace();
            return wines;
        }
    }

    public LinkedHashMap<Integer, Wine> getWinesSorted(String sort) {
        Statement statement = null;
        String query = "SELECT Wine.*, Grape.Name as grapename FROM Wine, Grape, WineGrape\n" +
                "WHERE Wine.Id = WineGrape.WineId\n" +
                "AND Grape.Id = WineGrape.GrapeId\n" +
                "ORDER BY " + sort + ";";
        System.out.println(query);
        LinkedHashMap<Integer, Wine> wines = new LinkedHashMap<>();

        try {
            statement = dbHelper.getDBConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (wines.containsKey(Integer.parseInt(resultSet.getString("Id")))) {
                    wines.get(Integer.parseInt(resultSet.getString("Id"))).getGrapes().add(resultSet.getString("grapename"));
                } else {
                    wines.put(Integer.parseInt(resultSet.getString("Id")), createWineModel(resultSet));
                }
            }
            return wines;
        } catch (Exception e) {
            e.printStackTrace();
            return wines;
        }
    }

    public HashMap<Integer, Wine> getWineBySearch(String searchTerm) {
        Statement statement = null;

        String query ="SELECT Wine.*, Grape.Name as grapename\n" +
                "FROM Wine, Grape, WineGrape, Producer\n" +
                "WHERE Wine.Name LIKE '%" + searchTerm + "%'\n" +
                "AND Wine.Id = WineGrape.WineId\n" +
                "AND Grape.Id = WineGrape.GrapeId\n" +
                "AND Wine.ProducerId = Producer.Id\n" +
                "OR Producer.Name LIKE '%" + searchTerm + "%'\n" +
                "AND Wine.ProducerId = Producer.Id\n" +
                "AND Wine.Id = WineGrape.WineId\n" +
                "AND Grape.Id = WineGrape.GrapeId\n" +
                "OR Grape.Name LIKE '%" + searchTerm + "%'\n" +
                "AND Wine.Id = WineGrape.WineId\n" +
                "AND Grape.Id = WineGrape.GrapeId\n" +
                "AND Wine.ProducerId = Producer.Id\n" +
                "OR Wine.Type LIKE '%" + searchTerm + "%'\n" +
                "AND Wine.Id = WineGrape.WineId\n" +
                "AND Grape.Id = WineGrape.GrapeId\n" +
                "AND Wine.ProducerId = Producer.Id\n" +
                "OR Wine.Country LIKE '%" + searchTerm + "%'\n" +
                "AND Wine.Id = WineGrape.WineId\n" +
                "AND Grape.Id = WineGrape.GrapeId\n" +
                "AND Wine.ProducerId = Producer.Id\n" +
                "OR Wine.Region LIKE '%" + searchTerm + "%'\n" +
                "AND Wine.Id = WineGrape.WineId\n" +
                "AND Grape.Id = WineGrape.GrapeId\n" +
                "AND Wine.ProducerId = Producer.Id\n" +
                ";";
        HashMap<Integer, Wine> wines = new HashMap<>();
        try {
            statement = dbHelper.getDBConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (wines.containsKey(Integer.parseInt(resultSet.getString("Id")))) {
                    wines.get(Integer.parseInt(resultSet.getString("Id"))).getGrapes().add(resultSet.getString("grapename"));
                } else {
                    wines.put(Integer.parseInt(resultSet.getString("Id")), createWineModel(resultSet));
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

    // check if wine with Id exist in list NOT NECESSARY ANYMORE CUS I USE HASHMAP
    public boolean wineInList(List<Wine> wines, int Id) {
        boolean found = wines.stream()
                .anyMatch(w -> w.getId() == Id);
        return found;
    }

    // Basic select *
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

}
