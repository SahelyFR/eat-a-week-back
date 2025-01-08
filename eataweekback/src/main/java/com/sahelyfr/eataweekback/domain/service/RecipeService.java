package com.sahelyfr.eataweekback.domain.service;

import com.sahelyfr.eataweekback.application.exceptions.InconsistentDataException;
import com.sahelyfr.eataweekback.application.exceptions.NotFoundSeasonException;
import com.sahelyfr.eataweekback.application.exceptions.NullArgumentException;
import com.sahelyfr.eataweekback.domain.Recipe;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.coyote.BadRequestException;

public interface RecipeService {

    Recipe getRecipeById(int recipeId) throws NoSuchElementException;

    List<Recipe> getAllRecipes();

    List<Recipe> getRecipesBySeason(String season) throws NotFoundSeasonException, NullArgumentException;

    Recipe createRecipe(Recipe newRecipe) throws BadRequestException;

    Recipe updateRecipe(int recipeId, Recipe recipe) throws NullArgumentException, InconsistentDataException,
            BadRequestException;
}
