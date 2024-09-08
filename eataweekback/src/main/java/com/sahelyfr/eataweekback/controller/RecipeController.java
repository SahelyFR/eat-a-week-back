package com.sahelyfr.eataweekback.controller;

import com.sahelyfr.eataweekback.exceptions.InconsistentDataException;
import com.sahelyfr.eataweekback.exceptions.NullArgumentException;
import com.sahelyfr.eataweekback.dto.Recipe;
import com.sahelyfr.eataweekback.service.RecipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.sahelyfr.eataweekback.utils.Utils.checkNotNull;

@RestController("/recipes")
@CrossOrigin(origins = "http://localhost:3000")
public class RecipeController {
    
    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public Iterable<Recipe> getAllRecipes(){
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{season}")
    public Iterable<Recipe> getRecipesBySeason(@PathVariable("season") String season){
        return recipeService.getRecipesBySeason(season);
    }

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable("id") final Long id) throws NullArgumentException {
        checkNotNull("id", id);
        return recipeService.getRecipe(id);
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@PathVariable("id") final Long recipeId, @RequestBody Recipe updatedRecipe) throws NullArgumentException, InconsistentDataException {
        return recipeService.updateRecipe(recipeId, updatedRecipe);
    }

    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public Recipe createRecipe(@RequestBody Recipe newRecipe) throws NullArgumentException {
        System.out.println(newRecipe);
        checkNotNull("newRecipe", newRecipe);

        return recipeService.createRecipe(newRecipe);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable("recipeId") final Long recipeId) throws NullArgumentException {
        checkNotNull("recipeId", recipeId);
        recipeService.deleteRecipe(recipeId);
    }
}
