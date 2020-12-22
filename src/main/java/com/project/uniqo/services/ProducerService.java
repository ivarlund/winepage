package com.project.uniqo.services;

import com.project.uniqo.DAL.ProducerDAO;
import com.project.uniqo.models.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerService {

    @Autowired
    ProducerDAO producerDAO;

    public List<Producer> fetchProducerDbData() {
        List<Producer> producers = producerDAO.getProducers();

        return producers;
    }
}
