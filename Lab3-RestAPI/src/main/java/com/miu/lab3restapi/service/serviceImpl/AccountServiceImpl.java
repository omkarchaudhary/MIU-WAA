package com.miu.lab3restapi.service.serviceImpl;

import com.miu.lab3restapi.entity.Account;
import com.miu.lab3restapi.entity.Transaction;
import com.miu.lab3restapi.service.AccountService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    HashMap<Integer, Account> accountMap = new HashMap<>();

    @Override
    public void createAccount(Account account) {
        Account newAccount = new Account(account.getAccountNumber(),account.getAccountHolder(),0, new ArrayList<Transaction>());
        accountMap.put(account.getAccountNumber(),newAccount);
    }

    @Override
    public Account deposit(int accountNumber, double amount) {
        Account account = getAccountTransaction(accountNumber, amount, "DEPOSIT");
        if (account != null) return account;
        return null;
    }
    @Override
    public Account withdraw(int accountNumber, double amount) {
        Account account = getAccountTransaction(accountNumber, amount, "WITHDRAW");
        if (account != null) return account;
        return null;
    }

    @Override
    public Account getAccount(int accountNumber) {
        return accountMap.get(accountNumber);
    }

    @Override
    public void removeAccount(int accountNumber) {
        accountMap.remove(accountNumber);
    }

    private Account getAccountTransaction(int accountNumber, double amount, String transactionType) {
        if(accountMap.containsKey(accountNumber)){
            Account account = accountMap.get(accountNumber);
            List<Transaction> transactions = account.getTransactionList();
            if(transactionType.equals("DEPOSIT")){
                transactions.add(new Transaction(amount,"DEPOSIT", LocalDate.now()));
                account.setBalance(account.getBalance()+ amount);
            }else if(transactionType.equals("WITHDRAW")){
                transactions.add(new Transaction(amount,"WITHDRAW", LocalDate.now()));
                account.setBalance(account.getBalance() - amount);
            }
            return account;
        }
        return null;
    }

}
