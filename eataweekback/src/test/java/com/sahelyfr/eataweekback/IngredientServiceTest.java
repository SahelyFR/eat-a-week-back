package com.sahelyfr.eataweekback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.sahelyfr.eataweekback.domain.Ingredient;
import com.sahelyfr.eataweekback.domain.service.IngredientService;
import com.sahelyfr.eataweekback.domain.service.implementations.IngredientServiceImpl;
import com.sahelyfr.eataweekback.infrastructure.IngredientRepository;
import com.sahelyfr.eataweekback.infrastructure.MeasureRepository;
import com.sahelyfr.eataweekback.infrastructure.entities.IngredientEntity;
import com.sahelyfr.eataweekback.infrastructure.entities.MeasureEntity;
import com.sahelyfr.eataweekback.domain.Measure;

class IngredientServiceTest extends ApplicationTest {

  @Mock
  IngredientRepository ingredientRepositoryMock;
  @Mock
  MeasureRepository measureRepositoryMock;

  @InjectMocks
  private IngredientService ingredientService = new IngredientServiceImpl(ingredientRepositoryMock);

  private static final Measure GRAMMES = Measure.builder().name("grammes").shortName("g").build();
  private static final MeasureEntity GRAMMES_ENTITY = MeasureEntity.builder().name("grammes").shortName("g").build();

  private static final List<IngredientEntity> INGREDIENTS_FROM_DB = List.of(new IngredientEntity[] {
      IngredientEntity.builder().id(1).name("farine")
          .measure(MeasureEntity.builder().name("grammes").shortName("g").build()).enabled(true).build(),
      IngredientEntity.builder().id(2).name("lait")
          .measure(MeasureEntity.builder().name("litres").shortName("L").build()).enabled(true).build(),
      IngredientEntity.builder().id(3).name("miel")
          .measure(MeasureEntity.builder().name("cuiller à café").shortName("tsp").build()).enabled(false).build()
  });

  private static final Ingredient INGREDIENT_TO_ADD = Ingredient.builder().id(1).name("cannelle").measure(GRAMMES)
      .enabled(true).build();

  private static final IngredientEntity MOCKED_INGREDIENT_SAVED = IngredientEntity.builder()
      .id(1)
      .name("cannelle")
      .measure(GRAMMES_ENTITY)
      .enabled(true)
      .build();

  @BeforeEach
  void setup() {
    when(measureRepositoryMock.findByShortName(any()))
        .thenReturn(Optional.of(MeasureEntity.builder().shortName("g").build()));
  }

  @Test
  @DisplayName("When calling getAllIngredients and there are some then it should return a list of them")
  void getAllIngredientsSuccessTest() {

    when(ingredientRepositoryMock.findAll()).thenReturn(INGREDIENTS_FROM_DB);

    List<Ingredient> ingredients = ingredientService.getAllIngredients();

    assertEquals(3, ingredients.size());
    assertTrue(ingredients.stream().filter(m -> m.getName().equals("farine")).findAny().isPresent());
    assertTrue(ingredients.stream().filter(m -> m.getName().equals("lait")).findAny().isPresent());
    assertTrue(ingredients.stream().filter(m -> m.getName().equals("miel")).findAny().isPresent());

  }

  @Test
  @DisplayName("When calling getAllActiveIngredients and there aren't any then it should raise a NoSuchElementException")
  void getAllIngredientsFailingTest() {

    when(ingredientRepositoryMock.findAll()).thenReturn(new ArrayList<>());

    assertThrows(NoSuchElementException.class, () -> ingredientService.getAllActiveIngredients());
  }

  @Test
  @DisplayName("When calling getAllActiveIngredients and there are some then it should return a list of them")
  void getAllEnabledIngredientsSuccessTest() {

    when(ingredientRepositoryMock.findAll()).thenReturn(INGREDIENTS_FROM_DB);

    List<Ingredient> ingredients = ingredientService.getAllActiveIngredients();

    assertEquals(2, ingredients.size());
    assertTrue(ingredients.stream().filter(m -> m.getName().equals("farine")).findAny().isPresent());
    assertTrue(ingredients.stream().filter(m -> m.getName().equals("lait")).findAny().isPresent());
    assertFalse(ingredients.stream().filter(m -> m.getName().equals("miel")).findAny().isPresent());

  }

  @Test
  @DisplayName("When calling getIngredientById and there is one then it should return it")
  void getIngredientByIdSuccessTest() {

    when(ingredientRepositoryMock.findById(1))
        .thenReturn(Optional.of(IngredientEntity.builder().id(1)
            .name("farine")
            .measure(GRAMMES_ENTITY)
            .enabled(true)
            .build()));

    Ingredient ingredient = ingredientService.getIngredientById(1);

    assertNotNull(ingredient);
    assertEquals("farine", ingredient.getName());
    assertEquals("g", ingredient.getMeasure().getShortName());
    assertTrue(ingredient.isEnabled());
  }

  @Test
  @DisplayName("When calling getIngredientById and there aren't any then it should raise a NoSuchElementException")
  void getIngredientByIdFailingTest() {

    when(ingredientRepositoryMock.findById(any())).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> ingredientService.getIngredientById(1));
  }

  @Test

  @DisplayName("When calling createIngredient and the save is correct it should return the saved ingredient with a new id")
  void createIngredientSuccessTest() {

    when(ingredientRepositoryMock.save(any(IngredientEntity.class)))
        .thenReturn(MOCKED_INGREDIENT_SAVED);

    try {
      Ingredient retrievedIngredient = ingredientService.createIngredient(INGREDIENT_TO_ADD);
      assertEquals(INGREDIENT_TO_ADD, retrievedIngredient);

    } catch (Exception e) {
      fail("No excpetion expected there", e);
    }

  }

  @Test

  @DisplayName("When calling createIngredient and the provided ingrdient name already exists it should throw an IllegalArgumentException")
  void addMeasureFailingTest() {

    when(ingredientRepositoryMock.findByName(any()))
        .thenReturn(Optional.of(MOCKED_INGREDIENT_SAVED));

    try {
      ingredientService
          .createIngredient(Ingredient.builder().id(1).name("carotte").measure(GRAMMES).enabled(true).build());
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("This ingredient already exists"));
    } catch (Exception e) {
      fail("Expected an IllegalArgumentException to be thrown");
    }

  }

  @Test

  @DisplayName("When calling updateIngredient and the save is correct it should the saved ingredient with a new id")
  void updateIngredientSuccessTest() {

    when(ingredientRepositoryMock.findById(any()))
        .thenReturn(Optional.of(IngredientEntity.builder()
            .id(1)
            .name("cannelle")
            .measure(GRAMMES_ENTITY)
            .enabled(false)
            .build()));

    when(ingredientRepositoryMock.save(any(IngredientEntity.class)))
        .thenReturn(MOCKED_INGREDIENT_SAVED);

    Ingredient updatedIngredient = ingredientService.updateIngredient(INGREDIENT_TO_ADD, 1);

    assertEquals(INGREDIENT_TO_ADD, updatedIngredient);
  }

  @Test
  @DisplayName("When calling updateIngredient and the ingredient id does not exist it should throw an NoSuchElementException")
  void updateIngredientFailingTest() {
    when(ingredientRepositoryMock.findById(any())).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> ingredientService.updateIngredient(INGREDIENT_TO_ADD, 2));
  }

  @Test

  @DisplayName("When calling updateIngredient and the shortname is different from dto and entity it should throw an IllegalArgumentException")
  void updateIngredientFailingTest2() {

    when(ingredientRepositoryMock.findById(any()))
        .thenReturn(Optional.of(MOCKED_INGREDIENT_SAVED));

    try {
      ingredientService
          .updateIngredient(Ingredient.builder().id(1).name("otherName").measure(GRAMMES).enabled(true).build(), 1);
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("You can't change the ingredient's name"));
    } catch (Exception e) {
      fail("Expected an IllegalArgumentException to be thrown");
    }
  }

}
