package com.project.uniqo.services;

import com.project.uniqo.DAL.WineDAO;
import com.project.uniqo.models.Wine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WineService {

    @Autowired
    WineDAO wineDAO;

    public List<Wine> fetchWineDbData() {
        List<Wine> wines = wineDAO.getWines();

        return wines;
    }
}
