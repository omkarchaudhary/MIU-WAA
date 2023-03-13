package com.miu.lab5mongodemoparttwo.controller;

import com.miu.lab5mongodemoparttwo.entity.Account;
import com.miu.lab5mongodemoparttwo.exception.CustomException;
import com.miu.lab5mongodemoparttwo.service.AccountDTO;
import com.miu.lab5mongodemoparttwo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestParam() @Valid int accountNumber, @RequestParam() @Valid String accountHolder) {
        AccountDTO account = new AccountDTO(accountNumber,accountHolder,0.0, new ArrayList<>());
        accountService.createAccount(account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestParam() int accountNumber,@RequestParam double amount) {
        Account deposit = accountService.deposit(accountNumber, amount);
        if(deposit == null){
            return new ResponseEntity<CustomException>(new CustomException("Account with number = " + accountNumber + " is not found"),HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(deposit,HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestParam int accountNumber,@RequestParam double amount) {
        Account account = accountService.getAccount(accountNumber);
        if(account.getBalance() < amount){
            return new ResponseEntity<CustomException>(new CustomException("Account with number = " + accountNumber + " does not have sufficient money"),HttpStatus.NOT_FOUND);
        }
        Account withdraw = accountService.withdraw(accountNumber, amount);
        if(withdraw == null){
            return new ResponseEntity<CustomException>(new CustomException("Account with number = " + accountNumber + " is not found"),HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(withdraw,HttpStatus.OK);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<?> getAccount(@PathVariable int accountNumber) {
        Account account = accountService.getAccount(accountNumber);
        if(account == null){
            return new ResponseEntity<CustomException>(new CustomException("Account with number = " + accountNumber + " is not found"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(account,HttpStatus.OK);
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<?> removeAccount(@PathVariable int accountNumber) {
        Account account = accountService.getAccount(accountNumber);
        if(account == null){
            return new ResponseEntity<CustomException>(new CustomException("Account with number = " + accountNumber + " is not found"),HttpStatus.NOT_FOUND);
        }
        accountService.removeAccount(accountNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
