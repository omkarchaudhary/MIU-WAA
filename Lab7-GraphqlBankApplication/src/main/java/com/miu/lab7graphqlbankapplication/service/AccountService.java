package com.miu.lab7graphqlbankapplication.service;

import com.miu.lab7graphqlbankapplication.entity.Account;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
     void createAccount(AccountDTO account);
     Account deposit(int accountNumber, double amount);
     Account withdraw(int accountNumber, double amount);
     Account getAccount(int accountNumber);
     void removeAccount(int accountNumber);
}
