package com.sahelyfr.eataweekback.service.implementations;

import com.sahelyfr.eataweekback.domain.Season;
import com.sahelyfr.eataweekback.dto.Recipe;
import com.sahelyfr.eataweekback.dto.converter.RecipeConverter;
import com.sahelyfr.eataweekback.exceptions.InconsistentDataException;
import com.sahelyfr.eataweekback.exceptions.NullArgumentException;
import com.sahelyfr.eataweekback.repository.RecipeRepository;
import com.sahelyfr.eataweekback.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.sahelyfr.eataweekback.utils.Utils.checkNotNull;

@Service("recipeService")
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe getRecipe(final Long id) {
        Optional<com.sahelyfr.eataweekback.model.Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            return RecipeConverter.fromEntityToDto(recipe.get());
        }
        throw new NoSuchElementException();
    }

    public List<Recipe> getAllRecipes() {
        List<com.sahelyfr.eataweekback.model.Recipe> foundRecipes = recipeRepository.findAll();
        List<Recipe> returnedRecipes = new ArrayList<>();
        for (com.sahelyfr.eataweekback.model.Recipe recipe : foundRecipes) {
            returnedRecipes.add(RecipeConverter.fromEntityToDto(recipe));
        }
        return returnedRecipes;
    }

    public void deleteRecipe(final Long id) {
        recipeRepository.deleteById(id);
    }

    public Recipe createRecipe(Recipe recipe) {
        return saveRecipe(recipe);
    }

    public Recipe updateRecipe(Long recipeId, Recipe recipe) throws NullArgumentException, InconsistentDataException {
        checkNotNull("id", recipeId);
        checkNotNull("recipe", recipe);
        if (!recipeId.equals(recipe.id())) {
            throw new InconsistentDataException("recipeId", recipeId, "com.sahelyfr.eataweekback.dto.recipe.id()",
                    recipe.id());
        }
        System.out.printf("Updating Recipe %s with %s%n", recipeId, recipe);

        return saveRecipe(recipe);
    }

    private Recipe saveRecipe(Recipe recipe) {
        com.sahelyfr.eataweekback.model.Recipe recipeToSave = RecipeConverter.fromDtoToEntity(recipe);
        return RecipeConverter.fromEntityToDto(recipeRepository.save(recipeToSave));
    }

    public List<Recipe> getCurrentRecipes() throws Exception {
        String seasonName = Season.getCurrent().name;
        checkNotNull("seasonName", seasonName);
        return RecipeConverter.fromEntitiesToDtos(recipeRepository.findBySeason(seasonName));
    }

    @Override
    public List<Recipe> getRecipesBySeason(String season) {
        return RecipeConverter.fromEntitiesToDtos(recipeRepository.findBySeason(season));
    }

}
