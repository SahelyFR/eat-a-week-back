package com.sahelyfr.eataweekback.application.dto.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.sahelyfr.eataweekback.application.dto.RecipeDto;
import com.sahelyfr.eataweekback.domain.Recipe;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

  RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

  @Mapping(source = "image", target = "imageLink")
  @Mapping(source = "weblink", target = "externalSiteLink")
  Recipe toDomain(RecipeDto recipeDto);

  @Mapping(target = "startingMonth", source = "startMonth")
  @Mapping(target = "endingMonth", source = "endMonth")
  @Mapping(target = "image", source = "imageLink")
  @Mapping(target = "weblink", source = "externalSiteLink")
  RecipeDto toDto(Recipe recipeEntity);

  List<RecipeDto> toDto(List<Recipe> recipeEntities);

  List<Recipe> toDomain(List<RecipeDto> recipeDtos);
}
