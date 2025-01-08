package com.sahelyfr.eataweekback.application.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public record IngredientDto(int id, String name, MeasureDto measure, boolean enabled, LocalDateTime creationDate,
                LocalDateTime lastUpdateDate) {

}
