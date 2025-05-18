package com.adrianoL.domain.exception;

public class RoleNotFoundException extends ResourceNotFoundException {

    public RoleNotFoundException(String role) {
        super("Role: %s not found.".formatted(role));
    }

  public RoleNotFoundException(String role, Throwable cause) {
    super("Role: %s not found.".formatted(role), cause);
  }
}
