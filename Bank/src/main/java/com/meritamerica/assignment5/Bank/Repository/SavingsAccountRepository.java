package com.meritamerica.assignment5.Bank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment5.models.SavingsAccount;



public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Integer> {

}
