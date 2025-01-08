package com.sahelyfr.eataweekback.infrastructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.sahelyfr.eataweekback.domain.Ingredient;
import com.sahelyfr.eataweekback.infrastructure.entities.IngredientEntity;

@Mapper(componentModel = "spring")
public interface IngredientEntityMapper {

  IngredientEntityMapper INSTANCE = Mappers.getMapper(IngredientEntityMapper.class);

  @Mapping(target = "recipes", source = "recipesUsingIt")
  IngredientEntity toEntity(Ingredient ingredient);

  @Mapping(source = "recipes", target = "recipesUsingIt")
  Ingredient toDomain(IngredientEntity ingredientEntity);

}
