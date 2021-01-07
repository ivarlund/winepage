package com.project.uniqo.services;

import com.project.uniqo.DAL.WineDAO;
import com.project.uniqo.models.Wine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class WineService {

    @Autowired
    WineDAO wineDAO;

    public HashMap<Integer, Wine> fetchWineDbData() {
        HashMap<Integer, Wine> wines = wineDAO.getWines();
        return wines;
    }

    public List<Wine> fetchWineDbData2() {
        List<Wine> wines = wineDAO.getWines2();

        return wines;
    }

    public HashMap<Integer, Wine> fetchWineBySearch(String searchTerm) {
        HashMap<Integer, Wine> wines = wineDAO.getWineBySearch(searchTerm);
        return wines;
    }

    public LinkedHashMap<Integer, Wine> sortWineDbData(String sort) {
        LinkedHashMap<Integer, Wine> wines = wineDAO.getWinesSorted(sort);
        return wines;
    }

    public void addWine(Wine wine) {
        wineDAO.addWine(wine);
    }

    public Wine fetchWineById(String id) {
        return wineDAO.getWineById(id);
    }
}
