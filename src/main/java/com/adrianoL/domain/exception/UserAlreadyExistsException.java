package com.adrianoL.domain.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class UserAlreadyExistsException extends RuntimeException {

  private Map<String, String> errors;

    public UserAlreadyExistsException(String message) {
        super(message);
    }

  public UserAlreadyExistsException(String message, Map<String, String> errors) {
    super(message);
    this.errors = errors;
  }

  public UserAlreadyExistsException(String message, Throwable cause) {
    super(message, cause);
  }

  public UserAlreadyExistsException(String message, Map<String, String> errors, Throwable cause) {
    super(message, cause);
    this.errors = errors;
  }

}
