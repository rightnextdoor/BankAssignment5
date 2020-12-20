package com.meritamerica.assignment5.Bank.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.validation.ValidationException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment5.Bank.Repository.UserRepository;
import com.meritamerica.assignment5.Bank.Service.UserDetailsServiceImpl;

@RestController
public class UserInfoController {
	
	private UserRepository  userRepository;
	
	public UserInfoController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@PostMapping(" /authenticate/createUse")
	public Boolean create(@RequestBody Map<String, String> body) throws NoSuchAlgorithmException {
        String username = body.get("username");
        if (userRepository.getUserByUsername(username) != null){

            throw new ValidationException("Username already existed");

        }

        String password = body.get("password");
        String encodedPassword = new BCryptPasswordEncoder().encode(password);

        return true;
    }

}
