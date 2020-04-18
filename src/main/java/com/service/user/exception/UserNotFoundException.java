package com.service.user.exception;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(Integer id) {
    super("user with id " + id + " not found");
  }
}
