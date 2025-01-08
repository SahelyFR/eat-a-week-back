package com.sahelyfr.eataweekback.application;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahelyfr.eataweekback.application.dto.MeasureDto;
import com.sahelyfr.eataweekback.application.dto.mappers.MeasureMapper;
import com.sahelyfr.eataweekback.domain.Measure;
import com.sahelyfr.eataweekback.domain.service.MeasureService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/measures")
public class MeasureController {

  private MeasureService measureService;

  public MeasureController(MeasureService measureService) {
    this.measureService = measureService;
  }

  @GetMapping("/")
  public List<MeasureDto> getAllEnabledMeasures() {
    return MeasureMapper.INSTANCE.toDto(measureService.getAllEnabledMeasures());
  }

  @GetMapping("/{measureShortName}")
  public MeasureDto getMeasureByShortName(@PathVariable String measureShortName) {
    return MeasureMapper.INSTANCE.toDto(measureService.getByShortName(measureShortName));
  }

  @PostMapping()
  public MeasureDto createNewMeasure(@RequestBody MeasureDto measure) {
    Measure newMeasure = MeasureMapper.INSTANCE.toDomain(measure);

    return MeasureMapper.INSTANCE.toDto(measureService.addMeasure(newMeasure));
  }

  @PostMapping("/lot")
  public void createNewMeasures(@RequestBody List<MeasureDto> measures) {
    List<Measure> measuresToAdd = MeasureMapper.INSTANCE.toDomain(measures);

    measureService.addMeasures(measuresToAdd);
  }

  @PutMapping("/{shortName}")
  public MeasureDto putMethodName(@PathVariable String shortName, @RequestBody MeasureDto measure) {
    Measure measureToUpdate = MeasureMapper.INSTANCE.toDomain(measure);

    return MeasureMapper.INSTANCE.toDto(measureService.updateMeasure(measureToUpdate, shortName));
  }

}
