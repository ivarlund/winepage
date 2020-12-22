package com.project.uniqo.services;

import com.project.uniqo.DAL.WineDAO;
import com.project.uniqo.models.Wine;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WineService {

    public List<Wine> fetchWineDbData() {
        WineDAO wineDAO = new WineDAO();
        List<Wine> wines = wineDAO.getWines();

        return wines;
    }
}
