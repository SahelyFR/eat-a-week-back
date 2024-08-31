package com.sahelyfr.eataweekback.service;

import com.sahelyfr.eataweekback.model.Recette;
import com.sahelyfr.eataweekback.repository.RecetteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Collections;

@Service
public class MenuService {

    @Autowired
    private RecetteRepository rr;
    static final int WEEK_DAYS = 7;

    public List<Recette> getAllRecettes() {
        return rr.findAll();
    }

    public List<Recette> getASeasonMenuForCompleteWeek(String season) {
        List<Recette> recetteList = rr.findBySeason(season);
        Collections.shuffle(recetteList);
        return recetteList.subList(0, WEEK_DAYS);
    }

    public List<Recette> getASeasonMenuForAGivenMealNumber(String season, int quantity) {
        List<Recette> recetteList = rr.findBySeason(season);
        Collections.shuffle(recetteList);
        return recetteList.subList(0, quantity);
    }
}
