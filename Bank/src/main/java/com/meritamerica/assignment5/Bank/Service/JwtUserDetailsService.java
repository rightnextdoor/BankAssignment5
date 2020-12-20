package com.meritamerica.assignment5.Bank.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.meritamerica.assignment5.Bank.Repository.UserRepository;
import com.meritamerica.assignment5.models.User;

@Component
public class JwtUserDetailsService {

	 @Autowired
	    private UserRepository userInfoRepository;


	   
	 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	        User user = userInfoRepository.getUserByUsername(username);
	        if (user == null) {
	            throw new UsernameNotFoundException("User not found with username: " + username);
	        }
	        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
	                new ArrayList<>());
	    }
}
