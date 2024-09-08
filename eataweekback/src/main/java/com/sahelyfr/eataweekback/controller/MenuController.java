package com.sahelyfr.eataweekback.controller;

import java.util.List;

import com.sahelyfr.eataweekback.model.Recipe;
import com.sahelyfr.eataweekback.service.MenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MenuController {
  @Autowired
  private MenuService menuService;

  @GetMapping("/menu/{season}")
  public List<Recipe> getASeasonMenuForCompleteWeek(@PathVariable("season") String season) {
    return menuService.getASeasonMenuForCompleteWeek(season);
  }

  @GetMapping("/menu/{season}/{recipeQuantity}")
  public List<Recipe> getASeasonMenuForAGivenMealNumber(@PathVariable("season") String season,
      @PathVariable("recipeQuantity") int qty) {
    return menuService.getASeasonMenuForAGivenMealNumber(season, qty);
  }

}
