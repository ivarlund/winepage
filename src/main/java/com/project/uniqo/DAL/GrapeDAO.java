package com.project.uniqo.DAL;

import com.project.uniqo.models.Grape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@Repository
public class GrapeDAO {

    @Autowired
    DBHelper dbHelper;

    public ArrayList<Grape> getAllGrapes() {
        Statement statement = null;
        String query = "SELECT * FROM Grape;";
        ArrayList<Grape> grapes = new ArrayList<>();
        try {
            statement = dbHelper.getDBConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                grapes.add(createGrapeModel(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return grapes;
    }

    public Grape createGrapeModel(ResultSet resultSet) {
        Grape grape = new Grape();
        try {
            grape.setId(Integer.parseInt(resultSet.getString("Id")));
            grape.setName(resultSet.getString("Name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return grape;
    }
}
