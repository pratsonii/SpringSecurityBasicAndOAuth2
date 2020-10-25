package com.pratik.services;

import com.pratik.dao.UserDao;
import com.pratik.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        if(userRepository.findById(username).isPresent()){
            return userRepository.findById(username).get();
        }
        else {
            throw new UsernameNotFoundException(username);
        }
    }

    public ResponseEntity<String> saveNewUser(User user){

        if(userRepository.findById(user.getEmail()).isPresent()){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("User already present!");
        }

        userRepository.saveAndFlush(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registration Successful");
    }
}