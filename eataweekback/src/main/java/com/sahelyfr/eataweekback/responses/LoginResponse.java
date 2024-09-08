package com.sahelyfr.eataweekback.responses;

public class LoginResponse {

  private String token;
  private long expiresIn;

  public long getExpiresIn() {
    return expiresIn;
  }

  public LoginResponse setToken(String token) {
    this.token = token;
    return this;
  }

  public LoginResponse setExpiresIn(long expiresIn) {
    this.expiresIn = expiresIn;
    return this;
  }

  public String getToken() {
    return token;
  }

}
