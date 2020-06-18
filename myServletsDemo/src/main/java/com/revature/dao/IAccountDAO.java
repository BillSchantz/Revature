package com.revature.dao;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;


public interface IAccountDAO {
	
//
//	Account create(Account acc); // CREATE
//	
//	List<Account> findAll(); // READ
//	Account findById(int id);
//	
//	Account update(Account acc); // UPDATE
//	
//	Account delete(Account acc); // DELETE
	

	public int create(Account u); // CREATE

	public int createAccountOwner(int a, int u); // CREATE
	
	public List<Account> findAll(); // READ
	
	public long highestAccountId(); // READ
	
	public int update(Account u); // UPDATE

	public int deposit(int acct, int user, double amount); // UPDATE
	
	public int withdraw(int acct, int user, double amount); // UPDATE
	
	public int delete(int id); // DELETE

}


