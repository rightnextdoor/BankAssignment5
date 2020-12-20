package com.meritamerica.assignment5.Bank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment5.models.CheckingAccount;



public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Integer> {

}
