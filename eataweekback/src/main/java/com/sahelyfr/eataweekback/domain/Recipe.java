package com.sahelyfr.eataweekback.domain;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Recipe {

  private int id;
  private String name;
  private String imageLink;
  private String externalSiteLink;
  private int startMonth;
  private int endMonth;
  private String steps;
  private List<Ingredient> ingredients;
  private LocalDate creationDate;
  private LocalDate lastUpdateDate;

  public boolean hasInstructions() {
    // If both steps and weblink are null, return false
    return !(this.externalSiteLink == null && (this.steps == null && this.steps.isEmpty()));
  }

  @Override
  public String toString() {
    return "Recipe [id=" + id + ", name=" + name + ", imageLink=" + imageLink + ", externalSiteLink=" + externalSiteLink
        + ", startMonth=" + startMonth + ", endMonth=" + endMonth + ", steps=" + steps + ", ingredients=" + ingredients
        + ", creationDate=" + creationDate + ", lastUpdateDate=" + lastUpdateDate + "]";
  }

}
