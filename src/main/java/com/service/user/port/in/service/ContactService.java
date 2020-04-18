package com.service.user.port.in.service;

import com.service.user.domain.entity.Email;
import com.service.user.domain.entity.PhoneNumber;

public interface ContactService {

  Email updateExistingEmail(Email newEmail, Integer id);

  PhoneNumber updateExistingPhoneNumber(PhoneNumber newPhoneNumber, Integer id);
}
