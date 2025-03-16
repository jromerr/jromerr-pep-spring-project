package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

//@Repository("accountRepo")
public interface AccountRepository extends JpaRepository<Account, Integer>{
    //@Query("FROM account WHERE username = :user")
    //Account findByUsername(@Param("user") String username);
    Optional<Account> findByUsername(String username);

    //@Query("FROM account WHERE accountId = :id")
    //Account findByAccountId(@Param("id") Integer accId);
}
