package com.sahelyfr.eataweekback.application.exceptions;

public class NotFoundSeasonException extends Exception {

  private static final String MESSAGE = "Provided month does not correspond to a season : %s";

  public NotFoundSeasonException(int variable1) {
    super(String.format(MESSAGE, variable1));
  }
}
