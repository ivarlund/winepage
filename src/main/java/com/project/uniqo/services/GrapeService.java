package com.project.uniqo.services;

import com.project.uniqo.DAL.GrapeDAO;
import com.project.uniqo.models.Grape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GrapeService {

    @Autowired
    GrapeDAO grapeDAO;

    public ArrayList<Grape> getAllGrapes() {
        return grapeDAO.getAllGrapes();
    }
}
