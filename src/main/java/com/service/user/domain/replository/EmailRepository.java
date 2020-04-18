package com.service.user.domain.replository;

import com.service.user.domain.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Integer> {}
