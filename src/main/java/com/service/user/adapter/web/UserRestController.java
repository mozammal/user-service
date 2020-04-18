package com.service.user.adapter.web;

import com.service.user.domain.entity.Email;
import com.service.user.domain.entity.PhoneNumber;
import com.service.user.domain.entity.User;
import com.service.user.domain.replository.*;
import com.service.user.exception.UserNotFoundException;
import com.service.user.port.in.service.UserSearchService;
import com.service.user.port.in.service.UserServiceManagementImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Validated
@RequestMapping("/api")
@Slf4j
@Tag(name = "user", description = "user API")
@org.springframework.web.bind.annotation.RestController
public class UserRestController {

  private final UserServiceManagementImpl userService;

  public UserRestController(UserServiceManagementImpl userService) {
    this.userService = userService;
  }

  // {"id":12,"firstName":"mozammal","lastName":"hossain","emails":[{"mail":"mozammaltomal.100@gmail.com"},{"mail":"mozammaltomal_1001@yahoo.com"}],"phoneNumbers":[{"number":"01753193627"}]}

  // [{
  // "mail": "abdul@yahoo.com"
  // }

  @Operation(
      summary = "Add new contact",
      description = "Add new contact",
      tags = {"user"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Contact created",
            content = @Content(schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "409", description = "Contact already exists")
      })
  @PostMapping(
      value = "/users",
      consumes = {"application/json", "application/xml"})
  User createNewUserWithContacts(@Valid @RequestBody User user) {
    return userService.createNewUserWithContacts(user);
  }

  @PutMapping("/users/{id}")
  User updateUser(@Valid @RequestBody User newUser, @PathVariable @NotNull Integer id) {
    return userService.updateUser(newUser, id);
  }

  @PostMapping("/users/{id}/emails")
  User addNewEmailToUser(
      @Valid @RequestBody List<Email> newEmails, @PathVariable @NotNull Integer id) {
    return userService.addNewEmailToUser(newEmails, id);
  }

  @PutMapping("/emails/{id}")
  Email updateExistingEmail(@Valid @RequestBody Email newEmail, @PathVariable @NotNull Integer id) {
    return userService.updateExistingEmail(newEmail, id);
  }

  @PostMapping("/users/{id}/phonenumbers")
  User addNewPhoneNumberToUser(
      @Valid @RequestBody List<PhoneNumber> newPhoneNUmbers, @PathVariable @NotNull Integer id) {
    return userService.addNewPhoneNumberToUser(newPhoneNUmbers, id);
  }

  @PutMapping("/phonenumbers/{id}")
  PhoneNumber updateExistingPhoneNumber(
      @RequestBody PhoneNumber newPhoneNumber, @PathVariable @NotNull Integer id) {
    return userService.updateExistingPhoneNumber(newPhoneNumber, id);
  }

  @GetMapping("/users/{userId}")
  User findUserById(@PathVariable @NotNull Integer userId) {
    return userService.findUserById(userId);
  }

  @DeleteMapping("/users/{id}")
  void deleteUser(@PathVariable @NotNull Integer id) {
    userService.deleteUser(id);
  }

  @GetMapping("/searchUsers")
  List<User> findUsersByFirstOrLastName(
      @RequestParam(value = "firstName", required = false) String firstName,
      @RequestParam(value = "lastName", required = false) String lastName,
      Pageable pageable) {

    return userService.findUsersByFirstOrLastNameOrBoth(
        new UserSearchService.FindUserByFirstOrLastNameOrBothCommand(firstName, lastName));
  }
}
