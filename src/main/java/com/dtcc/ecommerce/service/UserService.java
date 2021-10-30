package com.dtcc.ecommerce.service;

import com.dtcc.ecommerce.dto.UserRegistrationDTO;
import com.dtcc.ecommerce.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDTO registrationDAO);
}
