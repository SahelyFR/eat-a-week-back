package com.sahelyfr.eataweekback.domain.service;

import java.util.List;

import com.sahelyfr.eataweekback.application.dto.RecipeDto;

public interface MenuService {

  List<RecipeDto> getAllRecipes();

  List<RecipeDto> getASeasonMenuForCompleteWeek(String seasonName);

  List<RecipeDto> getASeasonMenuForAGivenMealNumber(String seasonName, int quantity);

}
