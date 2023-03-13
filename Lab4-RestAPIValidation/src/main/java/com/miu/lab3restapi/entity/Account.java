package com.miu.lab3restapi.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class Account {
    @NotNull
    @Size(min = 3, max = 10)
    private int accountNumber;
    @NotNull
    @Size(min = 5,max = 15)
    private String accountHolder;
    private double balance;
    List<Transaction> transactionList;

    public Account() {
    }

    public Account(int accountNumber, String accountHolder, double balance, List<Transaction> transactionList) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.transactionList = transactionList;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", accountHolder='" + accountHolder + '\'' +
                ", balance=" + balance +
                ", transactionList=" + transactionList +
                '}';
    }
}
