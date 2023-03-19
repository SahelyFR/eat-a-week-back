package com.sahelyfr.eataweekback.model;

import lombok.Data;

@Data
public class UserDto {

  private String username;
  private String password;
  private boolean enabled;
  private String email;
}
