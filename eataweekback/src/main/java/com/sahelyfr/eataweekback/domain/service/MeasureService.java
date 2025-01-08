package com.sahelyfr.eataweekback.domain.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.sahelyfr.eataweekback.domain.Measure;

public interface MeasureService {
  List<Measure> getAllEnabledMeasures();

  Measure getByShortName(String measureShortName) throws NoSuchElementException;

  Measure addMeasure(Measure newMeasure) throws IllegalArgumentException;

  List<Measure> addMeasures(List<Measure> newMeasure);

  Measure updateMeasure(Measure measure, String shortName);

}
