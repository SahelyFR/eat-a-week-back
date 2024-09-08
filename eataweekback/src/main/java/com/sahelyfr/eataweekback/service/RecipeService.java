package com.sahelyfr.eataweekback.service;

import com.sahelyfr.eataweekback.dto.Recipe;
import com.sahelyfr.eataweekback.exceptions.InconsistentDataException;
import com.sahelyfr.eataweekback.exceptions.NullArgumentException;

import java.util.List;

public interface RecipeService {

    Recipe getRecipe(Long recipeId);
    List<Recipe> getAllRecipes();
    List<Recipe> getCurrentRecipes() throws Exception;
    List<Recipe> getRecipesBySeason(String season);
    void deleteRecipe(Long recipeId);
    Recipe createRecipe(Recipe newRecipe);
    Recipe updateRecipe(Long recipeId, Recipe recipe) throws NullArgumentException, InconsistentDataException;
}
