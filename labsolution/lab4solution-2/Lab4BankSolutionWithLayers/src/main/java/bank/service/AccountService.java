package bank.service;

import bank.data.AccountRepository;
import bank.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;


    public void add(Account account){
        accountRepository.save(account);
    }

    public void update(Account account){
        accountRepository.save(account);
    }

    public Account findByAccountNumber(long acountNumber){
        return accountRepository.findByAccountNumber(acountNumber);
    }

    public void delete(long acountNumber){
        accountRepository.delete(acountNumber);
    }

    public Collection<Account> findAll(){
        return accountRepository.findAll();
    }

    public void deposit(long accountNumber, double amount){
        Account account = accountRepository.findByAccountNumber(accountNumber);
        account.deposit(amount);
    }

    public void withdraw(long accountNumber, double amount){
        Account account = accountRepository.findByAccountNumber(accountNumber);
        account.withdraw(amount);
    }
}
