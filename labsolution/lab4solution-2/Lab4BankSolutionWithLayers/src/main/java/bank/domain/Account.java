package bank.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


public class Account {
	private long accountnumber;
	private Collection<AccountEntry> entryList = new ArrayList<AccountEntry>();
	private String accountHolder;
	private double balance;

	public Account(long accountnumber, String accountHolder) {
		this.accountnumber = accountnumber;
		this.accountHolder = accountHolder;
	}

	public Account() {
	}

	public void computeBalance() {
		for (AccountEntry entry : entryList) {
			balance+=entry.getAmount();
		}
	}
	public void deposit(double amount){
		AccountEntry entry = new AccountEntry(new Date(), amount, "deposit");
		entryList.add(entry);
		computeBalance();
	}
	public void withdraw(double amount){
		AccountEntry entry = new AccountEntry(new Date(), -amount, "withdraw");
		entryList.add(entry);
		computeBalance();
	}

	public long getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(long accountnumber) {
		this.accountnumber = accountnumber;
	}

	public Collection<AccountEntry> getEntryList() {
		return entryList;
	}

	public void setEntryList(Collection<AccountEntry> entryList) {
		this.entryList = entryList;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}
}
