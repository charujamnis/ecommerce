package com.dtcc.ecommerce.repository;

import com.dtcc.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

        User findByEmail(String email);
}
