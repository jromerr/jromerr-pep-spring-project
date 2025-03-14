package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account addAccount(Account account){
        if(account.getUsername() != "" && account.getPassword().length() >= 4 && accountRepository.findByUsername(account.getUsername())){
            return accountRepository.save(account);
        }
        return null;
    }

    public Account loginAccount(Account account){
        if(accountRepository.findByUsername(account.getUsername()) != null){
            Account newAcc = accountRepository.findByUsername(account.getUsername())
            if(account.getPassword().equals(newAcc.getPassword())) return newAcc;
        }
        return null;
    }
}
