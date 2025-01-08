package com.sahelyfr.eataweekback.infrastructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.sahelyfr.eataweekback.domain.Recipe;
import com.sahelyfr.eataweekback.infrastructure.entities.RecipeEntity;

@Mapper(componentModel = "spring")
public interface RecipeEntityMapper {

  RecipeEntityMapper INSTANCE = Mappers.getMapper(RecipeEntityMapper.class);

  @Mapping(target = "startingMonth", source = "startMonth")
  @Mapping(target = "endingMonth", source = "endMonth")
  RecipeEntity toEntity(Recipe recipe);

  @Mapping(source = "image", target = "imageLink")
  @Mapping(source = "weblink", target = "externalSiteLink")
  @Mapping(source = "startingMonth", target = "startMonth")
  @Mapping(source = "endingMonth", target = "endMonth")
  Recipe toDomain(RecipeEntity entity);

}
