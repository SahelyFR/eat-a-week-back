package com.sahelyfr.eataweekback.service;

import java.util.List;
import java.util.Optional;

import com.sahelyfr.eataweekback.model.Recipe;
import com.sahelyfr.eataweekback.repository.RecipeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class RecipeServiceImpl{

    @Autowired
    private RecipeRepository recipeRepository;

    public Optional<Recipe> getRecipe(final Long id) {
        return recipeRepository.findById(id);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public void deleteRecipe(final Long id) {
        recipeRepository.deleteById(id);
    }

    public Recipe saveRecipe(Recipe re) {
        return recipeRepository.save(re);
    }

    public List<Recipe> getRecipesBySeason(String season) {
        return recipeRepository.findBySeason(season);
    }
}
