package com.service.user.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Violation {

  private final String fieldName;

  private final String message;

  public Violation(String fieldName, String message) {
    this.fieldName = fieldName;
    this.message = message;
  }
}
