package com.sahelyfr.eataweekback.application;

import com.sahelyfr.eataweekback.application.dto.RecipeDto;
import com.sahelyfr.eataweekback.application.dto.mappers.RecipeMapper;
import com.sahelyfr.eataweekback.application.exceptions.InconsistentDataException;
import com.sahelyfr.eataweekback.application.exceptions.NotFoundSeasonException;
import com.sahelyfr.eataweekback.application.exceptions.NullArgumentException;
import com.sahelyfr.eataweekback.domain.Recipe;
import com.sahelyfr.eataweekback.domain.service.RecipeService;

import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sahelyfr.eataweekback.infrastructure.utils.Utils.checkNotNull;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public List<RecipeDto> getAllRecipes() {
        return RecipeMapper.INSTANCE.toDto(recipeService.getAllRecipes());
    }

    @GetMapping("/{id}")
    public RecipeDto getRecipe(@PathVariable("id") final int id) throws NullArgumentException {
        checkNotNull("id", id);

        Recipe recipe = recipeService.getRecipeById(id);
        System.out.println("Domain recipe :");
        System.out.println(recipe.toString());

        return RecipeMapper.INSTANCE.toDto(recipe);
    }

    @GetMapping("/season/{season}")
    public List<RecipeDto> getRecipesBySeason(@PathVariable("season") String season)
            throws NullArgumentException, NotFoundSeasonException {
        return RecipeMapper.INSTANCE.toDto(recipeService.getRecipesBySeason(season));
    }

    @PostMapping()
    public RecipeDto createRecipe(@RequestBody RecipeDto newRecipe)
            throws NullArgumentException, BadRequestException {
        checkNotNull("newRecipe", newRecipe);

        Recipe recipeToAdd = RecipeMapper.INSTANCE.toDomain(newRecipe);

        return RecipeMapper.INSTANCE.toDto(recipeService.createRecipe(recipeToAdd));
    }

    @PutMapping("/{id}")
    public RecipeDto updateRecipe(@PathVariable("id") final int recipeId, @RequestBody RecipeDto updatedRecipe)
            throws NullArgumentException, InconsistentDataException, BadRequestException {

        Recipe recipeToUpdate = RecipeMapper.INSTANCE.toDomain(updatedRecipe);

        return RecipeMapper.INSTANCE.toDto(recipeService.updateRecipe(recipeId, recipeToUpdate));
    }
}
