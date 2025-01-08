package com.sahelyfr.eataweekback.domain;

import com.sahelyfr.eataweekback.domain.enums.Meal;

import lombok.Data;

@Data
public class MenuDay {

  private String dayOfWeek;
  private Meal meal;
  private Recipe recipe;
  private short numberOfParts;
}
