package com.project.uniqo.DAL;

import com.project.uniqo.models.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProducerDAO {

//    private DBHelper dbHelper = new DBHelper();
    @Autowired
    DBHelper dbHelper;


    public List<Producer> getProducers() {
        Statement statement = null;
        String query = "SELECT * FROM Producer";
        List<Producer> producers = new ArrayList<>();
        try {
            statement = dbHelper.getDBConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Producer producer = new Producer();

                producer.setId(Integer.parseInt(resultSet.getString("Id")));
                producer.setName(resultSet.getString("Name"));
                producer.setCountry(resultSet.getString("Country"));
                producer.setDescription(resultSet.getString("Description"));

                producers.add(producer);
            }

            return producers;
        } catch (Exception e) {
            e.printStackTrace();
            return producers;
        }

    }}
