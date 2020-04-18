package com.service.user.exception;

public class EmailNotFoundException extends RuntimeException {

  public EmailNotFoundException(Integer id) {
    super("email with id " + id + " not found");
  }
}
