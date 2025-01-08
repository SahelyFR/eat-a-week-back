package com.sahelyfr.eataweekback.application;

import java.util.List;

import com.sahelyfr.eataweekback.application.dto.RecipeDto;
import com.sahelyfr.eataweekback.domain.service.MenuService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {

  private MenuService menuService;

  public MenuController(MenuService menuService) {
    this.menuService = menuService;
  }

  @GetMapping("/menu/{season}")
  public List<RecipeDto> getASeasonMenuForCompleteWeek(@PathVariable("season") String season) {
    return menuService.getASeasonMenuForCompleteWeek(season);
  }

  @GetMapping("/menu/{season}/{recipeQuantity}")
  public List<RecipeDto> getASeasonMenuForAGivenMealNumber(@PathVariable("season") String season,
      @PathVariable("recipeQuantity") int qty) {
    return menuService.getASeasonMenuForAGivenMealNumber(season, qty);
  }

}
