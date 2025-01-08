package com.sahelyfr.eataweekback.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sahelyfr.eataweekback.infrastructure.entities.IngredientEntity;
import com.sahelyfr.eataweekback.infrastructure.entities.RecipeEntity;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity, Integer> {

  @Query(nativeQuery = true, value = "SELECT i FROM ingredients i WHERE i.enabled = TRUE")
  List<IngredientEntity> findAllEnabled();

  Optional<IngredientEntity> findByName(String ingredientName);

  @Query(nativeQuery = true, value = "SELECT re FROM recipes re JOIN recipe_ingredients ri ON ri.recette_id = re.id WHERE ri.ingredient_id = :ingredientId")
  List<RecipeEntity> findRecipesUsingIngredient(int ingredientId);

}
