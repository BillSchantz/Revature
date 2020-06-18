package com.revature.driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.dao.AccountDAO;
import com.revature.dao.IAccountDAO;
import com.revature.dao.IUserDAO;
import com.revature.dao.UserDAO;
import com.revature.models.Account;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

/**
 * This class is just to test some of the methods to confirm that we
 * are actually interacting with our database
 * 
 * We create a User, insert it into the database, and then read all Users from it
 */
public class Driver {

	public static void main(String[] args) {
//		IUserDAO dao = new UserDAO();
		
//		User user = new User(0, "username9", "farkle", "m", "n", "email9@yahoo.com", new Role(1, "Standard"));
//		System.out.println(dao.insert(user));
//		
//		for(User u : dao.findAll()) {
//			System.out.println(u);
//		}
//
//		User v = dao.findByUsername("x");
//		System.out.println(v);
//		if (v != null) {
//			int retrievedId = v.getUserId();
//			System.out.println(retrievedId);
//		} else {
//			System.out.println("User Not Found");
//		}
			
//
//		User w = dao.findById(3);
//		System.out.println(w);
//
//		int d = dao.delete(2);
//		System.out.println(d);
	
//		IAccountDAO<Account, Integer> acc = new AccountDAO();

//		AccountDAO acc = new AccountDAO();
//		Account account = new Account(0, 0.25, 2, 1, "Bill's Checking");
//		System.out.println(acc.create(account));

		
//		AccountDAO acc = new AccountDAO();
//		System.out.println(acc.deposit(1, 1, 250.50));

		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE bank_account SET balance = balance + 50.01 WHERE ACCOUNT_ID = 1 AND ACCOUNT_OWNER = 1";
			System.out.println(sql);
			Statement stmt = conn.createStatement();
     		stmt.executeUpdate(sql);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
//
//		Account account = Acc(0, 0.25, 2, 1, "Bill's Checking");
//		acc.deposit(account, 200.50);
		
		
//		User user = new User(3, "username21", "house", "newname", null, "dan@yahoo.com", null);
//		User user = new User(0, null, null, null, null, null, null);
//		System.out.println(dao.update(user));

	

		
		
//		SELECT MAX(account_id) FROM bank_account;

	}

	
	
//		public User findById(int id) {

//		public User findByUsername(String username) {
//		
//	}

}
