package com.service.user.exception.handler;

import com.service.user.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

  @ResponseBody
  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String userNotFoundExceptionHandler(UserNotFoundException ex) {
    return ex.getLocalizedMessage();
  }

  @ResponseBody
  @ExceptionHandler(PhonenNumberNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String phoneNumberNotFoundExceptionHandler(PhonenNumberNotFoundException ex) {
    return ex.getLocalizedMessage();
  }

  @ResponseBody
  @ExceptionHandler(EmailNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String emailNotFoundExceptionHandler(EmailNotFoundException ex) {
    return ex.getLocalizedMessage();
  }

  @ResponseBody
  @ExceptionHandler(ConstraintViolationException.class)
  ResponseEntity<Object> onConstraintValidationException(ConstraintViolationException ex) {
    ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
    ex.getConstraintViolations()
        .forEach(
            violation -> {
              validationErrorResponse
                  .getViolations()
                  .add(
                      new Violation(
                          violation.getPropertyPath().toString(), violation.getMessage()));
            });
    return new ResponseEntity<>(validationErrorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  ResponseEntity<Object> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
    for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
      validationErrorResponse
          .getViolations()
          .add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
    }
    return new ResponseEntity<>(validationErrorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }
}
