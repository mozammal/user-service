package com.service.user.adapter.web;

import com.service.user.domain.entity.Email;
import com.service.user.domain.entity.PhoneNumber;
import com.service.user.domain.entity.User;
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

  @Operation(
      summary = "Add new user with contacts",
      description =
          "Add new user with contacts, user and contacts need to be valid, return the saved user with contact")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Contact created",
            content = @Content(schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input")
      })
  @PostMapping(
      value = "/users",
      consumes = {"application/json", "application/xml"})
  User createNewUserWithContacts(@Valid @RequestBody User user) {
    return userService.createNewUserWithContacts(user);
  }

  @Operation(
      summary = "Add new emails to existing user",
      description =
          "Add new emails to existing user, emails need to be valid, return the saved user with contacts")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Email created",
            content = @Content(schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input")
      })
  @PostMapping("/users/{id}/emails")
  User addNewEmailToUser(
      @Valid @RequestBody List<Email> newEmails, @PathVariable @NotNull Integer id) {
    return userService.addNewEmailToUser(newEmails, id);
  }

  @Operation(
      summary = "Update existing email",
      description =
          "Update existing email, emails need to be valid with id, return the updated email")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Email updated",
            content = @Content(schema = @Schema(implementation = Email.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input")
      })
  @PutMapping("/emails/{id}")
  Email updateExistingEmail(@Valid @RequestBody Email newEmail, @PathVariable @NotNull Integer id) {
    return userService.updateExistingEmail(newEmail, id);
  }

  @Operation(
      summary = "Add phonenumber",
      description =
          "Add phonenumber, needs to provide valid user id, return the updated user with contacts")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "PhoneNumber created",
            content = @Content(schema = @Schema(implementation = PhoneNumber.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input")
      })
  @PostMapping("/users/{id}/phonenumbers")
  User addNewPhoneNumberToUser(
      @Valid @RequestBody List<PhoneNumber> newPhoneNUmbers, @PathVariable @NotNull Integer id) {
    return userService.addNewPhoneNumberToUser(newPhoneNUmbers, id);
  }

  @Operation(
      summary = "Update existing phonenumber",
      description = "Update phonenumber, needs to provide with valid phone number id")
  @PutMapping("/phonenumbers/{id}")
  PhoneNumber updateExistingPhoneNumber(
      @RequestBody PhoneNumber newPhoneNumber, @PathVariable @NotNull Integer id) {
    return userService.updateExistingPhoneNumber(newPhoneNumber, id);
  }

  @Operation(
      summary = "find user by id",
      description = "find user details by id, needs to provide with valid user id")
  @GetMapping("/users/{userId}")
  User findUserById(@PathVariable @NotNull Integer userId) {
    return userService.findUserById(userId);
  }

  @Operation(
      summary = "delete users by id",
      description = "delete a user by id, needs to provide with valid user id")
  @DeleteMapping("/users/{id}")
  void deleteUser(@PathVariable @NotNull Integer id) {
    userService.deleteUser(id);
  }

  @Operation(
      summary = "serach user by name",
      description =
          "search user by firstName, lastName or for both, you need to provide firstName, lastName or both params for specific search")
  @GetMapping("/search_users")
  List<User> findUsersByFirstOrLastName(
      @RequestParam(value = "firstName", required = false) String firstName,
      @RequestParam(value = "lastName", required = false) String lastName,
      Pageable pageable) {

    firstName = firstName != null ? firstName.toLowerCase() : firstName;
    lastName = lastName != null ? lastName.toLowerCase() : lastName;
    return userService.findUsersByFirstOrLastNameOrBoth(
        new UserSearchService.FindUserByFirstOrLastNameOrBothCommand(firstName, lastName));
  }
}
