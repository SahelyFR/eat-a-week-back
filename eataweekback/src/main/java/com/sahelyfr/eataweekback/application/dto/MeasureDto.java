package com.sahelyfr.eataweekback.application.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record MeasureDto(String name, String shortName, boolean enabled, LocalDateTime creationDate,
    LocalDateTime lastUpdateDate) {

}
