package com.sahelyfr.eataweekback.domain.service.implementations;

import com.sahelyfr.eataweekback.application.dto.RecipeDto;
import com.sahelyfr.eataweekback.domain.enums.Season;
import com.sahelyfr.eataweekback.domain.service.MenuService;
import com.sahelyfr.eataweekback.infrastructure.RecipeRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Collections;

@Service
public class MenuServiceImpl implements MenuService {

    private static final int WEEK_DAYS = 7;

    private RecipeRepository recipeRepository;

    public MenuServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<RecipeDto> getAllRecipes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllRecipes'");
    }

    @Override
    public List<RecipeDto> getASeasonMenuForCompleteWeek(String seasonName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getASeasonMenuForCompleteWeek'");
    }

    @Override
    public List<RecipeDto> getASeasonMenuForAGivenMealNumber(String seasonName, int quantity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getASeasonMenuForAGivenMealNumber'");
    }

}
