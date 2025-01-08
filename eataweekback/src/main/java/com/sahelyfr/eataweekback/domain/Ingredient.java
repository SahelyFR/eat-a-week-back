package com.sahelyfr.eataweekback.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ingredient {

  private int id;
  private String name;
  private Measure measure;
  private boolean enabled;
  private List<Recipe> recipesUsingIt;
  private LocalDateTime creationDate;
  private LocalDateTime lastUpdateDate;
}
