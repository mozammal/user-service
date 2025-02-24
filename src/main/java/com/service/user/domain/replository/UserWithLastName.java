package com.service.user.domain.replository;

import com.service.user.domain.entity.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserWithLastName implements Specification<User> {

    private String lastName;

    public UserWithLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public Predicate toPredicate(
            Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (lastName == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        return criteriaBuilder.equal(root.get("lastName"), this.lastName);
    }
}
