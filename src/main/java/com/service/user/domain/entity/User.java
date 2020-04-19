package com.service.user.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

  @Id private Integer id;

  @NotBlank
  @Size(min = 1, max = 255)
  private String firstName;

  @NotBlank
  @Size(min = 1, max = 255)
  private String lastName;

  @Valid
  @OneToMany(cascade = CascadeType.ALL,
          mappedBy = "user",
          orphanRemoval = true)
  private List<Email> emails = new ArrayList<>();

  @Valid
  @OneToMany(cascade = CascadeType.ALL,
          mappedBy = "user",
          orphanRemoval = true)
  private List<PhoneNumber> phoneNumbers = new ArrayList<>();

  @Version
  private long version = 0L;

  public void addEmail(Email email) {
    emails.add(email);
    email.setUser(this);
  }

  public void addPhoneNumber(PhoneNumber phoneNumber) {
    phoneNumbers.add(phoneNumber);
    phoneNumber.setUser(this);
  }
}
