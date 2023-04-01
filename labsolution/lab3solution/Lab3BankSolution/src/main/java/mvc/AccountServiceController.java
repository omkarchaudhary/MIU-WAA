package mvc;


import java.util.*;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountServiceController {

	private Map<Long, Account> accounts= new HashMap<Long, Account>();
	
    public AccountServiceController() {
        accounts.put(123L, new Account(123,"James Brown"));
        accounts.put(456L, new Account(456,"Mary Jones"));
	}

	@GetMapping("/accounts/{accountnumber}")
    public ResponseEntity<?> getAccount(@PathVariable Long accountnumber) {
        Account account = accounts.get(accountnumber);
        if (account == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Account with number= " + accountnumber + " is not available"),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Account> (account, HttpStatus.OK);
    }

	@DeleteMapping("/accounts/{accountnumber}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountnumber) {
        Account account = accounts.get(accountnumber);
        if (account == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Account with number= " + accountnumber + " is not available"),HttpStatus.NOT_FOUND);
        }
        accounts.remove(accountnumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	

    @PostMapping("/accounts")
    public ResponseEntity<?> handlePost(@RequestParam(value="operation", required = false, defaultValue = "") String operation,
                                        @RequestParam(value="accountNumber", required = false) Long accountNumber,
                                        @RequestParam(value="amount", required = false) Double amount,
                                        @RequestParam(value="accountHolder", required = false) String accountHolder) {

        Account account = new Account();
        if (operation.equals("")){
            //create account
            if (accountNumber != 0 && accountHolder != null){
                String accountHolderName = accountHolder.substring(1, accountHolder.length()-1); //strip quotes
                account = new Account(accountNumber, accountHolderName);
                accounts.put(account.getAccountnumber(), account);
            }
        }
        else{
            String operationName = operation.substring(1, operation.length()-1); //strip quotes
            if (operationName.equals("deposit")){
                System.out.println("yes");
                account = accounts.get(accountNumber);
                account.deposit(amount);
            }
            else if (operationName.equals("withdraw")){
                account = accounts.get(accountNumber);
                account.withdraw(amount);
            }
        }
        return new ResponseEntity<Account> (account, HttpStatus.OK);
    }

}


