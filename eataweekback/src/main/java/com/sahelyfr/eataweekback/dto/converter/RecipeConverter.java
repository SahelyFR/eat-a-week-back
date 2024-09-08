package com.sahelyfr.eataweekback.dto.converter;

import com.sahelyfr.eataweekback.dto.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeConverter {

    public static Recipe fromEntityToDto(com.sahelyfr.eataweekback.model.Recipe entityRecipe){

        return new Recipe(entityRecipe.getId(), entityRecipe.getName(), entityRecipe.getWeblink(), entityRecipe.getImage(),
                entityRecipe.isSpring(), entityRecipe.isSummer(), entityRecipe.isAutumn(), entityRecipe.isWinter());
    }

    public static com.sahelyfr.eataweekback.model.Recipe fromDtoToEntity(Recipe dtoRecipe){
        com.sahelyfr.eataweekback.model.Recipe.Builder entityBuilder = new com.sahelyfr.eataweekback.model.Recipe.Builder();
        entityBuilder.image(dtoRecipe.image())
                .webLink(dtoRecipe.weblink())
                .spring(dtoRecipe.spring())
                .summer(dtoRecipe.summer())
                .autumn(dtoRecipe.autumn())
                .winter(dtoRecipe.winter());

        return entityBuilder.build(dtoRecipe.name());
    }

    public static List<Recipe> fromEntitiesToDtos(List<com.sahelyfr.eataweekback.model.Recipe> entities){
        List<Recipe> returnedRecipes = new ArrayList<>();
        for(com.sahelyfr.eataweekback.model.Recipe recipe : entities){
            returnedRecipes.add(fromEntityToDto(recipe));
        }
        return returnedRecipes;
    }
}
