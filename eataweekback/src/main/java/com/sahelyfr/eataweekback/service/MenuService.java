package com.sahelyfr.eataweekback.service;

import com.sahelyfr.eataweekback.model.Recipe;
import com.sahelyfr.eataweekback.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Collections;

@Service
public class MenuService {

    @Autowired
    private RecipeRepository rr;
    static final int WEEK_DAYS = 7;

    public List<Recipe> getAllRecettes() {
        return rr.findAll();
    }

    public List<Recipe> getASeasonMenuForCompleteWeek(String season) {
        List<Recipe> recetteList = rr.findBySeason(season);
        Collections.shuffle(recetteList);
        return recetteList.subList(0, WEEK_DAYS);
    }

    public List<Recipe> getASeasonMenuForAGivenMealNumber(String season, int quantity) {
        List<Recipe> recetteList = rr.findBySeason(season);
        Collections.shuffle(recetteList);
        return recetteList.subList(0, quantity);
    }
}
