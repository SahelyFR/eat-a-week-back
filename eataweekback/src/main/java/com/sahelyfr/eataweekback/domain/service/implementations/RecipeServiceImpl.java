package com.sahelyfr.eataweekback.domain.service.implementations;

import com.sahelyfr.eataweekback.application.exceptions.InconsistentDataException;
import com.sahelyfr.eataweekback.application.exceptions.NotFoundSeasonException;
import com.sahelyfr.eataweekback.application.exceptions.NullArgumentException;
import com.sahelyfr.eataweekback.domain.Recipe;
import com.sahelyfr.eataweekback.domain.enums.Season;
import com.sahelyfr.eataweekback.domain.service.RecipeService;
import com.sahelyfr.eataweekback.infrastructure.RecipeRepository;
import com.sahelyfr.eataweekback.infrastructure.entities.RecipeEntity;
import com.sahelyfr.eataweekback.infrastructure.mappers.RecipeEntityMapper;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service("recipeService")
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe getRecipeById(int recipeId) throws NoSuchElementException {
        Optional<RecipeEntity> recipeEntity = recipeRepository.findById(recipeId);
        System.out.println("Entity recipe :");
        System.out.println(recipeEntity.get().toString());

        return recipeEntity.map(RecipeEntityMapper.INSTANCE::toDomain)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll().stream()
                .map(RecipeEntityMapper.INSTANCE::toDomain)
                .toList();
    }

    private List<Recipe> getRecipeBetweenMonths(int startMonth, int endMonth) {
        return recipeRepository.findBetweenMonths(startMonth, endMonth)
                .stream()
                .map(RecipeEntityMapper.INSTANCE::toDomain)
                .toList();
    }

    @Override
    public List<Recipe> getRecipesBySeason(String season) throws NotFoundSeasonException, NullArgumentException {

        Season givenSeason = Season.getCurrent();
        if (!season.equals("current")) {
            givenSeason = Season.valueOf(season.toUpperCase());
        }

        return getRecipeBetweenMonths(givenSeason.startMonth, givenSeason.endMonth);
    }

    @Override
    public Recipe createRecipe(Recipe newRecipe) throws BadRequestException {

        return RecipeEntityMapper.INSTANCE.toDomain(saveRecipe(newRecipe));
    }

    private RecipeEntity saveRecipe(Recipe recipe) throws BadRequestException {

        if (recipe.getName() == null || recipe.getName().isEmpty()) {
            throw new BadRequestException("Recipe's name is mandatory and none was provided : " + recipe);
        }

        if (!recipe.hasInstructions()) {
            throw new BadRequestException(
                    "Both steps and webLink fields are empty, you should have at least one of them.");
        }

        if (recipe.getIngredients() == null || recipe.getIngredients().isEmpty()) {
            throw new BadRequestException(
                    "Ingredient's list is mandatory and none was provided : " + recipe);
        }

        return recipeRepository.save(RecipeEntityMapper.INSTANCE.toEntity(recipe));

    }

    @Override
    public Recipe updateRecipe(int recipeId,
            Recipe recipe)
            throws NullArgumentException, InconsistentDataException, BadRequestException {

        if (recipeRepository.findById(recipeId).isEmpty()) {
            throw new NoSuchElementException();
        }

        if (recipe.getId() != recipeId) {
            throw new InconsistentDataException("recipeId", recipeId, "recipe", recipe.getId());
        }

        return RecipeEntityMapper.INSTANCE.toDomain(saveRecipe(recipe));

    }

}
