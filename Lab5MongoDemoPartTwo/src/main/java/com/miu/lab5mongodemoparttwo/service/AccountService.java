package com.miu.lab5mongodemoparttwo.service;

import com.miu.lab5mongodemoparttwo.entity.Account;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
     void createAccount(AccountDTO account);
     Account deposit(int accountNumber, double amount);
     Account withdraw(int accountNumber, double amount);
     Account getAccount(int accountNumber);
     void removeAccount(int accountNumber);
}
