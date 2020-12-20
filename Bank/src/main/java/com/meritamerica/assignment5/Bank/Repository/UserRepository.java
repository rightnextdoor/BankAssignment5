package com.meritamerica.assignment5.Bank.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.meritamerica.assignment5.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	 @Query("SELECT u FROM User u WHERE u.username = :username")
	    public User getUserByUsername(@Param("username") String username);

}
