package com.service.user.port.in.service;

import com.service.user.domain.entity.Email;
import com.service.user.domain.entity.PhoneNumber;
import com.service.user.domain.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserService {

  User createNewUserWithContacts(User user);

  User updateUser(User newUser, Integer id);

  User addNewEmailToUser(List<Email> newEmails, Integer id);

  User addNewPhoneNumberToUser(List<PhoneNumber> newPhoneNUmbers, Integer id);

  User findUserById(Integer userId);

  void deleteUser(Integer id);
}
