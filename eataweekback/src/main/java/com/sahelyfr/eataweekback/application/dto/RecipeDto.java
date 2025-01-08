package com.sahelyfr.eataweekback.application.dto;

import java.time.LocalDateTime;
import java.util.List;

public record RecipeDto(int id, String name, String weblink, String image, List<IngredientDto> ingredients,
        String steps, int startingMonth, int endingMonth, LocalDateTime creationDate,
        LocalDateTime lastUpdateDate) {

}
