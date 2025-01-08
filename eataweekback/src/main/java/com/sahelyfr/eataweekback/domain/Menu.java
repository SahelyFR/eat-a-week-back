package com.sahelyfr.eataweekback.domain;

import java.util.List;

import com.sahelyfr.eataweekback.infrastructure.entities.User;

import lombok.Data;

@Data
public class Menu {

  private int id;
  private int year;
  private int weekNumber;
  private User author;
  private List<MenuDay> days;
}
