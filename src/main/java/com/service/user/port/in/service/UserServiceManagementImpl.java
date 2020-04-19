package com.service.user.port.in.service;

import com.service.user.domain.entity.Email;
import com.service.user.domain.entity.PhoneNumber;
import com.service.user.domain.entity.User;
import com.service.user.domain.replository.*;
import com.service.user.exception.EmailNotFoundException;
import com.service.user.exception.PhonenNumberNotFoundException;
import com.service.user.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceManagementImpl implements UserSearchService, UserService, ContactService {

  private final UserRepository userRepository;

  private final PhoneNumberRepository phoneNumberRepository;

  private final EmailRepository emailRepository;

  public UserServiceManagementImpl(
      UserRepository userRepository,
      PhoneNumberRepository phoneNumberRepository,
      EmailRepository emailRepository) {
    this.userRepository = userRepository;
    this.phoneNumberRepository = phoneNumberRepository;
    this.emailRepository = emailRepository;
  }

  @Override
  public List<User> findUsersByFirstOrLastNameOrBoth(
      FindUserByFirstOrLastNameOrBothCommand command) {
    Specification<User> query =
        Specification.where(
            new UserWithFirstName(command.getFirstName())
                .and(new UserWithLastName(command.getLastName())));
    return userRepository.findAll(query);
  }

  @Override
  public User createNewUserWithContacts(User user) {
    List<Email> emails = new ArrayList<>(user.getEmails());
    List<PhoneNumber> phoneNumbers = new ArrayList<>(user.getPhoneNumbers());
    User newUser = new User();
    newUser.setId(user.getId());
    newUser.setLastName(user.getLastName());
    newUser.setFirstName(user.getFirstName());
    emails.forEach(
        email -> {
          email.setMail(email.getMail());
          newUser.addEmail(email);
        });

    phoneNumbers.forEach(
        phoneNumber -> {
          phoneNumber.setNumber(phoneNumber.getNumber());
          newUser.addPhoneNumber(phoneNumber);
        });

    return userRepository.save(newUser);
  }

  @Override
  public User addNewEmailToUser(List<Email> newEmails, Integer id) {
    return userRepository
        .findById(id)
        .map(
            user -> {
              newEmails.forEach(
                  email -> {
                    user.addEmail(email);
                  });
              return userRepository.save(user);
            })
        .orElseThrow(() -> new UserNotFoundException(id));
  }

  @Override
  public User addNewPhoneNumberToUser(List<PhoneNumber> newPhoneNUmbers, Integer id) {
    return userRepository
        .findById(id)
        .map(
            user -> {
              newPhoneNUmbers.forEach(
                  phoneNumber -> {
                    user.addPhoneNumber(phoneNumber);
                  });
              return userRepository.save(user);
            })
        .orElseThrow(() -> new UserNotFoundException(id));
  }

  @Override
  public User findUserById(Integer userId) {
    return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
  }

  @Override
  public void deleteUser(Integer id) {
    userRepository.deleteById(id);
  }

  @Override
  public Email updateExistingEmail(Email newEmail, Integer id) {
    return emailRepository
        .findById(id)
        .map(
            email -> {
              email.setMail(newEmail.getMail());
              return emailRepository.save(email);
            })
        .orElseThrow(() -> new EmailNotFoundException(id));
  }

  @Override
  public PhoneNumber updateExistingPhoneNumber(PhoneNumber newPhoneNumber, Integer id) {
    return phoneNumberRepository
        .findById(id)
        .map(
            phoneNumber -> {
              phoneNumber.setNumber(newPhoneNumber.getNumber());
              return phoneNumberRepository.save(phoneNumber);
            })
        .orElseThrow(() -> new PhonenNumberNotFoundException(id));
  }
}
