package bank.data;

import bank.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccountRepository {

    private Map<Long, Account> accounts = new HashMap<Long, Account>();

    public void save(Account account){
        accounts.put(account.getAccountnumber(),account);
    }

    public Account findByAccountNumber(long accountNumber){
        return accounts.get(accountNumber);
    }

    public void delete(long accountNumber){
        accounts.remove(accountNumber);
    }

    public Collection<Account> findAll(){
        return accounts.values();
    }

}
