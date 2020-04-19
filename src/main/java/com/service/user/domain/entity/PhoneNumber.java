package com.service.user.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PhoneNumber {

  @Id
  @GeneratedValue
  private Integer id;

  @NotBlank(message = "phone umber cant be blank")
  @Size(min = 1, max = 255)
  private String number;

  @JsonIgnore @ManyToOne private User user;

  @Version
  private long version = 0L;
}
