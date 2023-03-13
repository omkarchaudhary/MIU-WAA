package com.miu.lab3restapi.service.serviceImpl;

import com.miu.lab3restapi.entity.Account;
import com.miu.lab3restapi.entity.Transaction;
import com.miu.lab3restapi.repository.AccountRepository;
import com.miu.lab3restapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public void createAccount(Account account) {
        accountRepository.createAccount(account);
    }

    @Override
    public Account deposit(int accountNumber, double amount) {
        return accountRepository.deposit(accountNumber,amount);
    }
    @Override
    public Account withdraw(int accountNumber, double amount) {
        return accountRepository.withdraw(accountNumber,amount);
    }
    @Override
    public Account getAccount(int accountNumber) {
        return accountRepository.getAccount(accountNumber);
    }

    @Override
    public void removeAccount(int accountNumber) {
        accountRepository.removeAccount(accountNumber);
    }


}
