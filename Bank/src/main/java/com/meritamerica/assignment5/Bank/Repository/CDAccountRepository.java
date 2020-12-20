package com.meritamerica.assignment5.Bank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment5.models.CDAccount;



public interface CDAccountRepository extends JpaRepository<CDAccount, Integer> {

}
