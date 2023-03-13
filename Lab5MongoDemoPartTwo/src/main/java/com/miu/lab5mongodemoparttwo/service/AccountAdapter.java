package com.miu.lab5mongodemoparttwo.service;

import com.miu.lab5mongodemoparttwo.entity.Account;

public class AccountAdapter {
    public static Account getAccount(AccountDTO accountDTO){
        Account account = new Account();
        if(accountDTO != null){
            account = new Account(accountDTO.getAccountNumber(),
                    accountDTO.getAccountHolder(),
                    accountDTO.getBalance(),
                    accountDTO.getTransactionList());
        }
        return account;

    }

    public static AccountDTO getAccountDTO(Account account){
        AccountDTO accountDTO = new AccountDTO();
        if (account != null) {
            accountDTO = new AccountDTO(account.getAccountNumber(),
                    account.getAccountHolder(),
                    account.getBalance(),
                    account.getTransactionList());
        }
        return accountDTO;
    }
}
