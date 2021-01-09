package com.project.uniqo.DAL;

import com.project.uniqo.models.Grape;
import com.project.uniqo.models.Wine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Repository
public class WineDAO {

    @Autowired
    DBHelper dbHelper;

    @Autowired
    ProducerDAO producerDAO;

    public Wine getWineById(String id) {
        Statement statement = null;
//        String query = "SELECT Wine.*, Grape.Name as grapename FROM Wine, Grape, WineGrape WHERE Wine.Id = " + id +
//                " AND " + id + " = WineGrape.WineId\n" +
//                "AND Grape.Id = WineGrape.GrapeId;";
        String query = "SELECT * FROM Wine WHERE Id = " + id + ";";
        Wine wine = new Wine();
        try {
            statement = dbHelper.getDBConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
                wine = createWineModel(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wine;
    }

    public void addWine(Wine wine, ArrayList<String> grapes) {
        String query = "INSERT INTO Wine (Name, Type, Country, Region, Year, Description, ProducerId, imgPath) " +
                "VALUES ('" + wine.getName() + "','" + wine.getType() + "','" + wine.getCountry() + "','" +
                wine.getRegion() + "'," + wine.getYear() + ",'" + wine.getDescription() + "'," + wine.getProducerId() + ",'" + wine.getImgPath() + "');";
        try {
            PreparedStatement statement = dbHelper.getDBConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int x = statement.executeUpdate();
            if (x != 0 && !grapes.isEmpty()) {
                ResultSet key = statement.getGeneratedKeys();
                if (key.next()) {
                    for (String s: grapes)
                        addWineGrape(String.valueOf(key.getLong(1)), s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editWine(Wine wine) {
        Statement statement = null;
        String query = createUpdateStatement(wine);
        System.out.println(query);
        try {
            statement = dbHelper.getDBConnection().createStatement();
            int x = statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteWine(String id) {
        Statement statement = null;
        String query = "DELETE FROM WineGrape WHERE WineId = " + id + "; DELETE FROM Wine WHERE Id = " + id + ";";
        try {
            statement = dbHelper.getDBConnection().createStatement();
            int x  = statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addWineGrape(String wineId, String grapeId) {
        Statement statement = null;
        String query = "INSERT INTO WineGrape (WineId, GrapeId) VALUES (" + wineId + ", " + grapeId + ");";
        System.out.println(query);
        try {
            statement = dbHelper.getDBConnection().createStatement();
            int x = statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteWineGrape(String wineId) {
        Statement statement = null;
        String query = "DELETE FROM WineGrape WHERE WineId = " + wineId + ";";
        try {
            statement = dbHelper.getDBConnection().createStatement();
            int x = statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<Integer, Wine> getAllWines() {
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
                    Grape grape = new Grape();
                    grape.setName(resultSet.getString("grapename"));
                    wines.get(Integer.parseInt(resultSet.getString("Id"))).getGrapes().add(grape);
                } else {
                    wines.put(Integer.parseInt(resultSet.getString("Id")), createWineModelWithGrapes(resultSet));
                }
            }
            return wines;
        } catch (Exception e) {
            e.printStackTrace();
            return wines;
        }
    }

    public LinkedHashMap<Integer, Wine> getAllWinesSorted(String sort) {
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
                    Grape grape = new Grape();
                    grape.setName(resultSet.getString("grapename"));
                    wines.get(Integer.parseInt(resultSet.getString("Id"))).getGrapes().add(grape);
                } else {
                    wines.put(Integer.parseInt(resultSet.getString("Id")), createWineModelWithGrapes(resultSet));
                }
            }
            return wines;
        } catch (Exception e) {
            e.printStackTrace();
            return wines;
        }
    }

    public HashMap<Integer, Wine> getWinesFiltered(String filter) {
        Statement statement = null;
        String query = "SELECT Wine.*, Grape.Name as grapename FROM Wine, Grape, WineGrape " +
                "WHERE Wine.Type = '" + filter + "' " +
                "AND Wine.Id = WineGrape.WineId " +
                "AND Grape.Id = WineGrape.GrapeId";
        System.out.println(query);
        HashMap<Integer, Wine> wines = new HashMap<>();
        try {
            statement = dbHelper.getDBConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (wines.containsKey(Integer.parseInt(resultSet.getString("Id")))) {
                    Grape grape = new Grape();
                    grape.setName(resultSet.getString("grapename"));
                    wines.get(Integer.parseInt(resultSet.getString("Id"))).getGrapes().add(grape);
                } else {
                    wines.put(Integer.parseInt(resultSet.getString("Id")), createWineModelWithGrapes(resultSet));
                }
            }
            return wines;
        } catch (Exception e) {
            e.printStackTrace();
            return wines;
        }
    }

    public HashMap<Integer, Wine> getWinesBySearch(String searchTerm) {
        Statement statement = null;

        String query = "SELECT Wine.*, Grape.Name as grapename\n" +
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
                    Grape grape = new Grape();
                    grape.setName(resultSet.getString("grapename"));
                    wines.get(Integer.parseInt(resultSet.getString("Id"))).getGrapes().add(grape);
                } else {
                    wines.put(Integer.parseInt(resultSet.getString("Id")), createWineModelWithGrapes(resultSet));
                }
            }
            return wines;
        } catch (Exception e) {
            e.printStackTrace();
            return wines;
        }
    }

    public HashMap<Integer, Wine> getWinesWithoutGrapes() {
        Statement statement = null;
        String query = "SELECT * FROM Wine";
        HashMap<Integer, Wine> wines = new HashMap<>();
        try {
            statement = dbHelper.getDBConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                wines.put(Integer.parseInt(resultSet.getString("Id")), createWineModel(resultSet));
            }
            return wines;
        } catch (Exception e) {
            e.printStackTrace();
            return wines;
        }

    }

    public Wine createWineModelWithGrapes(ResultSet resultSet) {
        Wine wine = new Wine();
        Grape grape = new Grape();
        try {
            wine.setId(Integer.parseInt(resultSet.getString("Id")));
            wine.setName(resultSet.getString("Name"));
            wine.setType(resultSet.getString("Type"));
            wine.setCountry(resultSet.getString("Country"));
            wine.setRegion(resultSet.getString("Region"));
            wine.setYear(Integer.parseInt(resultSet.getString("Year")));
            wine.setDescription(resultSet.getString("Description"));
            wine.setProducerId(Integer.parseInt(resultSet.getString("ProducerId")));
            grape.setName(resultSet.getString("grapename"));
            wine.getGrapes().add(grape);

            wine.setImgPath(resultSet.getString("imgPath"));

            wine.setProducer(producerDAO.getProducerById(wine.getProducerId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wine;
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
            wine.setImgPath(resultSet.getString("imgPath"));

            wine.setProducer(producerDAO.getProducerById(wine.getProducerId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wine;
    }

    public String createUpdateStatement(Wine wine) {
        String query = "UPDATE Wine SET ";
        System.out.println(wine.getName());
        ArrayList<String> queryArr = new ArrayList<>();
        if (wine.getName().length() > 0)
            queryArr.add("Name = '" + wine.getName() + "'");
        if (wine.getType().length() > 0)
            queryArr.add("Type = '" + wine.getType() + "'");
        if (wine.getCountry().length() > 0)
            queryArr.add("Country = '" + wine.getCountry() + "'");
        if (wine.getRegion().length() > 0)
            queryArr.add("Region = '" + wine.getRegion() + "'");
        if (wine.getYear() != 0)
            queryArr.add("Year = " + wine.getYear());
        if (wine.getDescription().length() > 0)
            queryArr.add("Description = '" + wine.getDescription() + "'");
        if (wine.getProducerId() != 0)
            queryArr.add("ProducerId = " + wine.getProducerId());
        if (wine.getImgPath().length() > 0)
            queryArr.add("imgPath = '" + wine.getImgPath() + "'");

        for (int i = 0; i < queryArr.size(); i++) {
            if (i == queryArr.size()-1) {
                query += queryArr.get(i);
                System.out.println("LAST ITEM : " + queryArr.get(i));
            } else {
                query += queryArr.get(i) + ",";
                System.out.println("ITEM : " + queryArr.get(i));
            }
        }
        return query + " WHERE Id = " + wine.getId() + ";";
    }

    // check if wine with Id exist in list NOT NECESSARY ANYMORE CUS I USE HASHMAP
    public boolean wineInList(List<Wine> wines, int Id) {
        boolean found = wines.stream()
                .anyMatch(w -> w.getId() == Id);
        return found;
    }

}
