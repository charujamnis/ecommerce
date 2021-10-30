package com.dtcc.ecommerce.service;

import com.dtcc.ecommerce.dto.UserRegistrationDTO;
import com.dtcc.ecommerce.model.Role;
//import com.dtcc.ecommerce.model.User;
import com.dtcc.ecommerce.model.User;
import com.dtcc.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDTO registrationDAO) {
        User user = new User(registrationDAO.getFirstName(),
                registrationDAO.getLastName(),
                registrationDAO.getEmail(),
               // registrationDAO.getPassword(),
                passwordEncoder.encode(registrationDAO.getPassword()),
                Arrays.asList(new Role("ROLE_USER")));

        return userRepository.save(user);  //save the user in the database.
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(username);
        if(user==null){
            System.out.println("user is null");
            throw new UsernameNotFoundException("Invalid username or password");
        }
        //this is Spring security User object
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));
    }

    //from spring class.
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
