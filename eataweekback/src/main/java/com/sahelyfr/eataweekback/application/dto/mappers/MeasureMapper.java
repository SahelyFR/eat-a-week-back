package com.sahelyfr.eataweekback.application.dto.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sahelyfr.eataweekback.application.dto.MeasureDto;
import com.sahelyfr.eataweekback.domain.Measure;

@Mapper(componentModel = "spring")
public interface MeasureMapper {

  MeasureMapper INSTANCE = Mappers.getMapper(MeasureMapper.class);

  Measure toDomain(MeasureDto measureDto);

  MeasureDto toDto(Measure measure);

  List<MeasureDto> toDto(List<Measure> measures);

  List<Measure> toDomain(List<MeasureDto> measures);

}
