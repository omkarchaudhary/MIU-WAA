package com.miu.lab7graphqlbankapplication.repository;

import com.miu.lab7graphqlbankapplication.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account,Integer> {


    /*
    HashMap<Integer, Account> accountMap = new HashMap<>();

    public void createAccount(Account account) {
        Account newAccount = new Account(account.getAccountNumber(),account.getAccountHolder(),0, new ArrayList<Transaction>());
        accountMap.put(account.getAccountNumber(),newAccount);
    }

    public Account deposit(int accountNumber, double amount) {
        Account account = getAccountTransaction(accountNumber, amount, "DEPOSIT");
        if (account != null) return account;
        return null;
    }

    public Account withdraw(int accountNumber, double amount) {
        Account account = getAccountTransaction(accountNumber, amount, "WITHDRAW");
        if (account != null) return account;
        return null;
    }

    public Account getAccount(int accountNumber) {
        return accountMap.get(accountNumber);
    }

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
    */
}
