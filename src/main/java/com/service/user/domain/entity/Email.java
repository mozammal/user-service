package com.service.user.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Email {
  @Id @GeneratedValue private Integer id;

  @javax.validation.constraints.Email(message = "Email needs to be valid")
  @Size(min = 1, max = 255)
  private String mail;

  @JsonIgnore
  @ManyToOne private User user;
}
