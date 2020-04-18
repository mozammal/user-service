package com.service.user.exception;

public class PhonenNumberNotFoundException extends RuntimeException {
  public PhonenNumberNotFoundException(Integer id) {
    super("phone with id " + id + " not found");
  }
}
