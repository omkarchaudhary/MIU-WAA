package com.miu.lab3restapi.service;

import com.miu.lab3restapi.entity.Account;

public interface AccountService {
     void createAccount(Account account);
     Account deposit(int accountNumber, double amount);
     Account withdraw(int accountNumber, double amount);
     Account getAccount(int accountNumber);
     void removeAccount(int accountNumber);
}
