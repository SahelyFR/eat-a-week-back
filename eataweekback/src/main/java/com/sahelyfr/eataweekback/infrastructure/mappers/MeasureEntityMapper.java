package com.sahelyfr.eataweekback.infrastructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.sahelyfr.eataweekback.domain.Measure;
import com.sahelyfr.eataweekback.infrastructure.entities.MeasureEntity;

@Mapper(componentModel = "spring")
public interface MeasureEntityMapper {

  MeasureEntityMapper INSTANCE = Mappers.getMapper(MeasureEntityMapper.class);

  MeasureEntity toEntity(Measure measure);

  Measure toDomain(MeasureEntity measureEntity);
}
