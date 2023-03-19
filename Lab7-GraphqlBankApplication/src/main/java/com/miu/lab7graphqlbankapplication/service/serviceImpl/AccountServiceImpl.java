package com.miu.lab7graphqlbankapplication.service.serviceImpl;
import com.miu.lab7graphqlbankapplication.entity.Account;
import com.miu.lab7graphqlbankapplication.entity.Transaction;
import com.miu.lab7graphqlbankapplication.repository.AccountRepository;
import com.miu.lab7graphqlbankapplication.service.AccountAdapter;
import com.miu.lab7graphqlbankapplication.service.AccountDTO;
import com.miu.lab7graphqlbankapplication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public void createAccount(AccountDTO accountDTO) {
        Account account = AccountAdapter.getAccount(accountDTO);
        accountRepository.save(account);
    }


    @Override
    public Account deposit(int accountNumber, double amount) {
        Account account = getAccountTransaction(accountNumber, amount, "DEPOSIT");
        if (account != null) {
            accountRepository.save(account);
            return account;
        };
        return null;
    }
    @Override
    public Account withdraw(int accountNumber, double amount) {
        Account account = getAccountTransaction(accountNumber, amount, "WITHDRAW");
        if (account != null) {
            accountRepository.save(account);
            return account;
        };
        return null;
    }
    @Override
    public Account getAccount(int accountNumber) {
        return accountRepository.findById(accountNumber).get();
    }

    @Override
    public void removeAccount(int accountNumber) {
        accountRepository.deleteById(accountNumber);
    }

    private Account getAccountTransaction(int accountNumber, double amount, String transactionType) {
        Optional<Account> optionalAccount = accountRepository.findById(accountNumber);
        if(optionalAccount.isPresent()){
            Account account = optionalAccount.get();
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
