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
                producers.add(createProducerModel(resultSet));
            }

            return producers;
        } catch (Exception e) {
            e.printStackTrace();
            return producers;
        }

    }

    public Producer getProducerById(int id) {
        Statement statement = null;
        String query = "SELECT * FROM Producer WHERE Id = " + id;
        Producer producer = new Producer();
        try {
            statement = dbHelper.getDBConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                producer = createProducerModel(resultSet);
            }

            return producer;
        } catch (Exception e) {
            e.printStackTrace();
            return producer;
        }
    }

    public Producer createProducerModel(ResultSet resultSet) {
        Producer producer = new Producer();
        try {
            producer.setId(Integer.parseInt(resultSet.getString("Id")));
            producer.setName(resultSet.getString("Name"));
            producer.setCountry(resultSet.getString("Country"));
            producer.setDescription(resultSet.getString("Description"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return producer;
    }

    public List<Producer> getProducersBySearch(String searchTerm) {
        Statement statement = null;
        String query = "SELECT * FROM Producer WHERE Name LIKE '%" + searchTerm + "%'";
        List<Producer> producers = new ArrayList<>();
        try {
            statement = dbHelper.getDBConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                producers.add(createProducerModel(resultSet));
            }

            return producers;
        } catch (Exception e) {
            e.printStackTrace();
            return producers;
        }

    }
}
