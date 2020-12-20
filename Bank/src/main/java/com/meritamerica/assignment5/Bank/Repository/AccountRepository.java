package com.meritamerica.assignment5.Bank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meritamerica.assignment5.models.AccountHolder;

@Repository
public interface AccountRepository extends JpaRepository<AccountHolder, Long> {

}
