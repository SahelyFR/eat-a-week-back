package com.sahelyfr.eataweekback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.sahelyfr.eataweekback.domain.Measure;
import com.sahelyfr.eataweekback.domain.service.MeasureService;
import com.sahelyfr.eataweekback.domain.service.implementations.MeasureServiceImpl;
import com.sahelyfr.eataweekback.infrastructure.MeasureRepository;
import com.sahelyfr.eataweekback.infrastructure.entities.MeasureEntity;

class MeasureServiceTest extends ApplicationTest {

  @Mock
  MeasureRepository measureRepositoryMock;

  @InjectMocks
  private MeasureService measureService = new MeasureServiceImpl(measureRepositoryMock);

  static final List<MeasureEntity> MEASURE_FROM_DB = List.of(new MeasureEntity[] {
      MeasureEntity.builder().name("grammes").shortName("g").enabled(true).build(),
      MeasureEntity.builder().name("litres").shortName("L").enabled(true).build(),
      MeasureEntity.builder().name("centigrammes").shortName("cg").enabled(false).build()
  });

  private static final Measure MEASURE_TO_ADD = Measure.builder().name("grammes").shortName("g").enabled(true).build();

  @Test
  @DisplayName("When calling getAllEnabledMeasures and there are some then it should return a list of them")
  void getAllEnabledMeasuresSuccessTest() {

    when(measureRepositoryMock.findAll()).thenReturn(MEASURE_FROM_DB);

    List<Measure> measures = measureService.getAllEnabledMeasures();

    assertEquals(2, measures.size());
    assertTrue(measures.stream().filter(m -> m.getShortName().equals("g")).findAny().isPresent());
    assertTrue(measures.stream().filter(m -> m.getShortName().equals("L")).findAny().isPresent());
    assertFalse(measures.stream().filter(m -> m.getShortName().equals("cg")).findAny().isPresent());

  }

  @Test
  @DisplayName("When calling getAllEnabledMeasures and there aren't any then it should raise a NoSuchElementException")
  void getAllEnabledMeasuresFailingTest() {

    when(measureRepositoryMock.findAll()).thenReturn(new ArrayList<>());

    assertThrows(NoSuchElementException.class, () -> measureService.getAllEnabledMeasures());
  }

  @Test
  @DisplayName("When calling getByShortName and there are some then it should return a list of them")
  void getByShortNameSuccessTest() {

    when(measureRepositoryMock.findByShortName("g")).thenReturn(
        Optional.of(MeasureEntity.builder().name("grammes").shortName("g").enabled(true).build()));

    Measure measure = measureService.getByShortName("g");

    assertNotNull(measure);
    assertEquals("g", measure.getShortName());
    assertEquals("grammes", measure.getName());
    assertTrue(measure.isEnabled());
  }

  @Test
  @DisplayName("When calling getByShortName and there aren't any then it should raise a NoSuchElementException")
  void getByShortNameFailingTest() {

    when(measureRepositoryMock.findByShortName("g")).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> measureService.getByShortName("g"));
  }

  @Test
  @DisplayName("When calling addMeasure and the save is correct it should the saved measure with a new id")
  void addMeasureSuccessTest() {

    MeasureEntity mockedMeasure = MeasureEntity.builder().name("grammes").shortName("g").enabled(true).build();

    when(measureRepositoryMock.save(Mockito.any(MeasureEntity.class))).thenReturn(mockedMeasure);

    Measure retrievedMeasure = measureService.addMeasure(MEASURE_TO_ADD);

    assertEquals(MEASURE_TO_ADD, retrievedMeasure);
  }

  @Test
  @DisplayName("When calling addMeasure and the provided shortName already exists it should throw an IllegalArgumentException")
  void addMeasureFailingTest() {

    when(measureRepositoryMock.findByShortName("g")).thenReturn(
        Optional.of(MeasureEntity.builder().name("grammes").shortName("g").enabled(true).build()));

    try {
      measureService.addMeasure(Measure.builder().name("grammes").shortName("g").enabled(true).build());
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("This measure already exists"));
    } catch (Exception e) {
      fail("Expected an IllegalArgumentException to be thrown");
    }

  }

  @Test
  @DisplayName("When calling updateMeasure and the save is correct it should the saved measure with a new id")
  void updateMeasureSuccessTest() {

    MeasureEntity mockedMeasure = MeasureEntity.builder().name("grammes").shortName("g").enabled(true).build();

    when(measureRepositoryMock.findByShortName("g")).thenReturn(
        Optional.of(MeasureEntity.builder().name("grammes").shortName("g").enabled(true).build()));

    when(measureRepositoryMock.save(Mockito.any(MeasureEntity.class))).thenReturn(mockedMeasure);

    Measure updatedMeasure = measureService.updateMeasure(MEASURE_TO_ADD, "g");

    assertEquals(MEASURE_TO_ADD, updatedMeasure);
  }

  @Test
  @DisplayName("When calling updateMeasure and the shortname does not exist it should throw an NoSuchElementException")
  void updateMeasureFailingTest() {
    when(measureRepositoryMock.findByShortName("g")).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> measureService.updateMeasure(MEASURE_TO_ADD, "g"));
  }

  @Test
  @DisplayName("When calling updateMeasure and the shortname is different from dto and entity it should throw an IllegalArgumentException")
  void updateMeasureFailingTest2() {

    when(measureRepositoryMock.findByShortName("g")).thenReturn(
        Optional.of(MeasureEntity.builder().name("grammes").shortName("g").enabled(true).build()));

    try {
      measureService.updateMeasure(Measure.builder().name("milligrammes").shortName("mg").enabled(true).build(), "g");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("You can't change the short-name of a measure"));
    } catch (Exception e) {
      fail("Expected an IllegalArgumentException to be thrown");
    }
  }

}
