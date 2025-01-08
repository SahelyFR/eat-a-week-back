package com.sahelyfr.eataweekback.application.dto.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sahelyfr.eataweekback.application.dto.IngredientDto;
import com.sahelyfr.eataweekback.domain.Ingredient;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

  IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);

  IngredientDto toDto(Ingredient ingredient);

  Ingredient toDomain(IngredientDto ingredientDto);

  List<IngredientDto> toDto(List<Ingredient> ingredients);

}
