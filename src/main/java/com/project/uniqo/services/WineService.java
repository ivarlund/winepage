package com.project.uniqo.services;

import com.project.uniqo.DAL.WineDAO;
import com.project.uniqo.models.Wine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class WineService {

    @Autowired
    WineDAO wineDAO;

    public HashMap<Integer, Wine> fetchWineDbData() {
//        List<Wine> wines = wineDAO.getWines();
        HashMap<Integer, Wine> wines = wineDAO.getWines();
        return wines;
    }

    public List<Wine> fetchWineDbData2() {
        List<Wine> wines = wineDAO.getWines2();

        return wines;
    }

    public HashMap<Integer, Wine> fetchWineBySearch(String searchTerm) {
//        List<Wine> wines = wineDAO.getWineBySearch(searchTerm);
        HashMap<Integer, Wine> wines = wineDAO.getWineBySearch(searchTerm);
        return wines;
    }
}
