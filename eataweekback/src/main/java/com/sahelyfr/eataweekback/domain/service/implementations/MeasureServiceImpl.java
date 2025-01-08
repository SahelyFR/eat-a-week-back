package com.sahelyfr.eataweekback.domain.service.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.sahelyfr.eataweekback.domain.Measure;
import com.sahelyfr.eataweekback.domain.service.MeasureService;
import com.sahelyfr.eataweekback.infrastructure.entities.MeasureEntity;
import com.sahelyfr.eataweekback.infrastructure.mappers.MeasureEntityMapper;
import com.sahelyfr.eataweekback.infrastructure.MeasureRepository;

@Service("measureService")
public class MeasureServiceImpl implements MeasureService {

  private MeasureRepository measureRepository;

  public MeasureServiceImpl(MeasureRepository measureRepository) {
    this.measureRepository = measureRepository;
  }

  @Override
  public List<Measure> getAllEnabledMeasures() {
    List<Measure> results = measureRepository.findAll().stream()
        .filter(MeasureEntity::isEnabled)
        .map(MeasureEntityMapper.INSTANCE::toDomain)
        .toList();

    if (!results.isEmpty()) {
      return results;
    }

    throw new NoSuchElementException();
  }

  @Override
  public Measure getByShortName(String measureShortName) throws NoSuchElementException {

    return measureRepository.findByShortName(measureShortName).map(MeasureEntityMapper.INSTANCE::toDomain)
        .orElseThrow(NoSuchElementException::new);

  }

  @Override
  public Measure addMeasure(Measure newMeasure) throws IllegalArgumentException {

    if (measureRepository.findByShortName(newMeasure.getShortName()).isPresent()) {
      throw new IllegalArgumentException("This measure already exists : " + newMeasure);
    }

    return MeasureEntityMapper.INSTANCE.toDomain(saveMeasure(newMeasure));
  }

  @Override
  public List<Measure> addMeasures(List<Measure> newMeasures) {

    List<Measure> savedMeasures = new ArrayList<>();

    for (Measure measure : newMeasures) {
      savedMeasures.add(addMeasure(measure));
    }

    return savedMeasures;
  }

  @Override
  public Measure updateMeasure(Measure measure, String shortName) {

    MeasureEntity existingMeasure = measureRepository.findByShortName(shortName)
        .orElseThrow(NoSuchElementException::new);

    if (!existingMeasure.getShortName().equals(measure.getShortName())) {
      throw new IllegalArgumentException("You can't change the short-name of a measure");
    }

    existingMeasure.setEnabled(measure.isEnabled());
    existingMeasure.setName(measure.getName());

    return MeasureEntityMapper.INSTANCE.toDomain(measureRepository.save(existingMeasure));
  }

  private MeasureEntity saveMeasure(Measure measure) {
    return measureRepository.save(MeasureEntityMapper.INSTANCE.toEntity(measure));
  }

}
