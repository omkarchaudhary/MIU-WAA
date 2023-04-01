package bank.web;


import java.util.*;

import bank.domain.Account;
import bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountServiceController {
    @Autowired
    AccountService accountService;

	@GetMapping("/accounts/{accountnumber}")
    public ResponseEntity<?> getAccount(@PathVariable Long accountnumber) {
        Account account = accountService.findByAccountNumber(accountnumber);
        if (account == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Account with number= " + accountnumber + " is not available"),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Account> (account, HttpStatus.OK);
    }

	@DeleteMapping("/accounts/{accountnumber}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountnumber) {
        Account account = accountService.findByAccountNumber(accountnumber);
        if (account == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Account with number= " + accountnumber + " is not available"),HttpStatus.NOT_FOUND);
        }
        accountService.delete(accountnumber);
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
                accountService.add(account);
            }
        }
        else{
            String operationName = operation.substring(1, operation.length()-1); //strip quotes
            if (operationName.equals("deposit")){
                accountService.deposit(accountNumber,amount );
            }
            else if (operationName.equals("withdraw")){
                accountService.withdraw(accountNumber,amount );
            }
        }
        return new ResponseEntity<Account> (account, HttpStatus.OK);
    }

}


