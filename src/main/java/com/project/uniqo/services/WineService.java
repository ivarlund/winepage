package com.project.uniqo.services;

import com.project.uniqo.DAL.WineDAO;
import com.project.uniqo.models.Wine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Service
public class WineService {

    @Autowired
    WineDAO wineDAO;

    public HashMap<Integer, Wine> getAllWines() {
        return wineDAO.getAllWines();
    }

    public HashMap<Integer, Wine> getWinesBySearch(String searchTerm) {
        return wineDAO.getWinesBySearch(searchTerm);
    }

    public LinkedHashMap<Integer, Wine> getAllWinesSorted(String sort) {
        return wineDAO.getAllWinesSorted(sort);
    }

    public Wine getWineById(String id) {
        return wineDAO.getWineById(id);
    }

    public void addWine(Wine wine, ArrayList<String> grapes) {
        wineDAO.addWine(wine, grapes);
    }

    public void editWine(Wine wine) {
        wineDAO.editWine(wine);
    }

    public void deleteWine(String id) {
        wineDAO.deleteWine(id);
    }

    public void addWineGrapes(String wineId, ArrayList<String> grapes) {
        wineDAO.deleteWineGrape(wineId);
        for (String grapeId: grapes) {
            wineDAO.addWineGrape(wineId, grapeId);
        }
    }

    public HashMap<Integer, Wine> getAllWines2() {
        return wineDAO.getWinesWithoutGrapes();
    }

}
