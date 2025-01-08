package com.sahelyfr.eataweekback.application;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahelyfr.eataweekback.application.dto.IngredientDto;
import com.sahelyfr.eataweekback.application.dto.mappers.IngredientMapper;
import com.sahelyfr.eataweekback.domain.Ingredient;
import com.sahelyfr.eataweekback.domain.service.IngredientService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("ingredients")
public class IngredientController {

  private IngredientService ingredientService;

  public IngredientController(IngredientService ingredientService) {
    this.ingredientService = ingredientService;
  }

  @GetMapping("/")
  public List<IngredientDto> getAllIngredients() {
    return IngredientMapper.INSTANCE.toDto(ingredientService.getAllIngredients());
  }

  @GetMapping("/enabled")
  public List<IngredientDto> getAllActiveIngredients() {
    return IngredientMapper.INSTANCE.toDto(ingredientService.getAllActiveIngredients());
  }

  @GetMapping("/{ingredientId}")
  public IngredientDto getIngredientById(@PathVariable int ingredientId) {
    return IngredientMapper.INSTANCE.toDto(ingredientService.getIngredientById(ingredientId));
  }

  @PostMapping("/")
  public IngredientDto addIngredient(@RequestBody IngredientDto newIngredient) throws BadRequestException {
    Ingredient ingredient = IngredientMapper.INSTANCE.toDomain(newIngredient);

    return IngredientMapper.INSTANCE.toDto(ingredientService.createIngredient(ingredient));
  }

  @PutMapping("/{ingredientId}")
  public IngredientDto updateIngredient(@PathVariable int ingredientId, @RequestBody IngredientDto ingredientToUpdate) {
    Ingredient ingredient = IngredientMapper.INSTANCE.toDomain(ingredientToUpdate);

    return IngredientMapper.INSTANCE.toDto(ingredientService.updateIngredient(ingredient, ingredientId));
  }

}
