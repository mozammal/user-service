package com.service.user.domain.replository;

import com.service.user.domain.entity.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserWithFirstName implements Specification<User> {

  private String firstName;

  public UserWithFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Override
  public Predicate toPredicate(
      Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
    if (firstName == null) {
      return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
    }
    return criteriaBuilder.equal(root.get("firstName"), this.firstName);
  }
}
