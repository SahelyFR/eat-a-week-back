package com.sahelyfr.eataweekback.domain.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.coyote.BadRequestException;

import com.sahelyfr.eataweekback.domain.Ingredient;
import com.sahelyfr.eataweekback.domain.Recipe;

public interface IngredientService {

  List<Ingredient> getAllActiveIngredients() throws NoSuchElementException;

  List<Ingredient> getAllIngredients();

  Ingredient getIngredientById(int ingredientId);

  List<Recipe> getRecipesUsingIngredient(int ingredientId);

  Ingredient createIngredient(Ingredient newIngredient) throws BadRequestException;

  Ingredient updateIngredient(Ingredient ingredient, int ingredientId);
}
