package com.service.user.port.in.service;

import com.service.user.domain.entity.User;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.List;

public interface UserSearchService {

  List<User> findUsersByFirstOrLastNameOrBoth(FindUserByFirstOrLastNameOrBothCommand command);

  @Value
  class FindUserByFirstOrLastNameOrBothCommand {

    @NotBlank private final String firstName;

    @NotBlank private final String lastName;

    public FindUserByFirstOrLastNameOrBothCommand(
        @NotBlank String firstName, @NotBlank String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
    }
  }
}
