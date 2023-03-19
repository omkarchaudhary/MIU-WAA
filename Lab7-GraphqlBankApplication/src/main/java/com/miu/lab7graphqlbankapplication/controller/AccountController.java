package com.miu.lab7graphqlbankapplication.controller;

import com.miu.lab7graphqlbankapplication.entity.Account;
import com.miu.lab7graphqlbankapplication.service.AccountDTO;
import com.miu.lab7graphqlbankapplication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @MutationMapping
    public Account createAccount(@Argument @Valid int accountNumber, @Argument() @Valid String accountHolder) {
        AccountDTO account = new AccountDTO(accountNumber,accountHolder,0.0, new ArrayList<>());
        accountService.createAccount(account);
        return new Account(account.getAccountNumber(),account.getAccountHolder(),account.getBalance(),account.getTransactionList());
    }

    @MutationMapping
    public Account deposit(@Argument() int accountNumber,@Argument double amount) {
        return accountService.deposit(accountNumber, amount);
    }

    @MutationMapping
    public Account withdraw(@Argument int accountNumber,@Argument double amount) {
        return accountService.withdraw(accountNumber,amount);

    }

    @QueryMapping
    public Account getAccount(@Argument int accountNumber) {
        System.out.println(accountService.getAccount(accountNumber));
        return accountService.getAccount(accountNumber);
    }

    @MutationMapping
    public Account removeAccount(@Argument int accountNumber) {
        return accountService.getAccount(accountNumber);
    }
}
