package com.sahelyfr.eataweekback.domain.service.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import com.sahelyfr.eataweekback.domain.Ingredient;
import com.sahelyfr.eataweekback.domain.Recipe;
import com.sahelyfr.eataweekback.domain.service.IngredientService;
import com.sahelyfr.eataweekback.infrastructure.IngredientRepository;
import com.sahelyfr.eataweekback.infrastructure.entities.IngredientEntity;
import com.sahelyfr.eataweekback.infrastructure.mappers.IngredientEntityMapper;
import com.sahelyfr.eataweekback.infrastructure.mappers.RecipeEntityMapper;

@Service("ingredientService")
public class IngredientServiceImpl implements IngredientService {

  private IngredientRepository ingredientRepository;

  public IngredientServiceImpl(IngredientRepository ingredientRepository) {
    this.ingredientRepository = ingredientRepository;
  }

  public List<Ingredient> getIngredientsById(List<Integer> ingredientIds) {
    List<Ingredient> ingredientList = new ArrayList<>();

    for (int ingredientId : ingredientIds) {
      ingredientList.add(getIngredientById(ingredientId));
    }

    return ingredientList;
  }

  @Override
  public List<Ingredient> getAllIngredients() {
    List<Ingredient> ingredientList = ingredientRepository.findAll().stream()
        .map(IngredientEntityMapper.INSTANCE::toDomain)
        .toList();

    if (!ingredientList.isEmpty()) {
      return ingredientList;
    }

    throw new NoSuchElementException();
  }

  @Override
  public List<Ingredient> getAllActiveIngredients() {

    List<Ingredient> ingredientList = getAllIngredients().stream()
        .filter(Ingredient::isEnabled)
        .toList();

    if (!ingredientList.isEmpty()) {
      return ingredientList;
    }

    throw new NoSuchElementException();
  }

  @Override
  public Ingredient getIngredientById(int ingredientId) {

    return ingredientRepository.findById(ingredientId)
        .map(IngredientEntityMapper.INSTANCE::toDomain)
        .orElseThrow(NoSuchElementException::new);
  }

  @Override
  public List<Recipe> getRecipesUsingIngredient(int ingredientId) {

    List<Recipe> recipesList = ingredientRepository.findRecipesUsingIngredient(ingredientId).stream()
        .map(RecipeEntityMapper.INSTANCE::toDomain)
        .toList();

    if (!recipesList.isEmpty()) {
      return recipesList;
    }

    throw new NoSuchElementException();
  }

  @Override
  public Ingredient createIngredient(Ingredient newIngredient) throws BadRequestException {

    if (newIngredient.getName() == null || newIngredient.getName().isEmpty()) {
      throw new BadRequestException("Ingredient's name is mandatory and none was provided : "
          + newIngredient);
    }

    if (ingredientRepository.findByName(newIngredient.getName()).isPresent()) {
      throw new IllegalArgumentException("This ingredient already exists : " + newIngredient);
    }

    if (newIngredient.getMeasure() == null || newIngredient.getMeasure().getShortName().isEmpty()) {
      throw new BadRequestException(
          "Ingredient's linked measure unit short-name is mandatory and none was provided : " + newIngredient);
    }

    return saveIngredient(newIngredient);
  }

  @Override
  public Ingredient updateIngredient(Ingredient ingredient, int ingredientId) {

    IngredientEntity existingIngredient = ingredientRepository.findById(ingredientId)
        .orElseThrow(NoSuchElementException::new);

    if (!existingIngredient.getName().equals(ingredient.getName())) {
      throw new IllegalArgumentException("You can't change the ingredient's name");
    }

    return saveIngredient(ingredient);
  }

  private Ingredient saveIngredient(Ingredient ingredient) {
    return IngredientEntityMapper.INSTANCE
        .toDomain(ingredientRepository.save(IngredientEntityMapper.INSTANCE.toEntity(ingredient)));
  }

}
