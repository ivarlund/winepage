package com.project.uniqo.services;

import com.project.uniqo.DAL.ProducerDAO;
import com.project.uniqo.models.Producer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerService {

    public List<Producer> fetchProducerDbData() {
        ProducerDAO producerDAO = new ProducerDAO();
        List<Producer> producers = producerDAO.getProducers();

        return producers;
    }
}
