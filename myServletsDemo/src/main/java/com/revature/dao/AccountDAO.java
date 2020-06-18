package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.revature.models.Account;
import com.revature.util.ConnectionUtil;

public class AccountDAO implements IAccountDAO {

	@Override
	public int create(Account acc) {
		// Step 1: Get a Connection using ConnectionUtil
		// The Connection interface represents the physical connection to the database
		System.out.println("ENTERING create Method");
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			// Step 2: Define our SQL Statements
			String columns = "balance, account_status_id, account_type_id, account_nickname, account_owner";
			String sql = "INSERT INTO bank_account (" + columns + ") VALUES (?, ?, ?, ?, ?)";
			System.out.println(sql);
			// The ? marks are placeholders for input values
			// They work for PreparedStatements, and are designed to protect from SQL Injection
			
			// Step 3: Obtain our Statement object
			// PreparedStatements are a sub-interface of Statement that provide extra security to prevent
			// SQL Injection. It accomplishes this by allowing to use ? marks that we can replace
			// with whatever data we want
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			// Step 3b: If we are using a PreparedStatement, then inject values to replace the ? marks
			// We insert values into each of the ? marks above
			// Note: It is HEAVILY Recommended to use PreparedStatements instead of String concatenation
			stmt.setDouble(1, acc.getBalance()); 
			stmt.setInt(2, acc.getStatusId()); 
			stmt.setInt(3, acc.getTypeId()); 
			stmt.setString(4, acc.getNickname());
			stmt.setInt(5, acc.getOwnerId());

			
			// Step 4: Execute the Statement
			int affectedRows = stmt.executeUpdate();
//            System.out.println("AffectedRows = " + affectedRows);
//			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			long generatedAccountId = 0L;
			if (rs.next()) {
				generatedAccountId = rs.getLong(1);
			}
			System.out.println("Generated Account Id = " + generatedAccountId);
			return affectedRows;
			//			System.out.println("About to executeUpdate");
//			return stmt.executeUpdate();
//			if (ret != 0) {
//				System.out.println("executeUpdate returns no error");
//				return acc;
//			} else {
//				System.out.println("executeUpdate returns error");
//				return null;
//			}
			
			
		} catch(SQLException e) {
			// Step 5: Perform any exception handling in an appropriate means
			
			// This particular example might not be what you want to ultimately use
			e.printStackTrace();
		}
		
		return 0;

	}

	@Override
	public int createAccountOwner(int aId, int uId) {
		// Step 1: Get a Connection using ConnectionUtil
		// The Connection interface represents the physical connection to the database
		System.out.println("ENTERING createAccountOwner Method");
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			// Step 2: Define our SQL Statements
			String columns = "account_id, user_id";
			String sql = "INSERT INTO bank_account_owner (" + columns + ") VALUES (?, ?)";
			System.out.println(sql);
			// The ? marks are placeholders for input values
			// They work for PreparedStatements, and are designed to protect from SQL Injection
			
			// Step 3: Obtain our Statement object
			// PreparedStatements are a sub-interface of Statement that provide extra security to prevent
			// SQL Injection. It accomplishes this by allowing to use ? marks that we can replace
			// with whatever data we want
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			// Step 3b: If we are using a PreparedStatement, then inject values to replace the ? marks
			// We insert values into each of the ? marks above
			// Note: It is HEAVILY Recommended to use PreparedStatements instead of String concatenation
			stmt.setInt(1, aId); 
			stmt.setInt(2, uId); 
		//	stmt.
			
			// Step 4: Execute the Statement
			return stmt.executeUpdate();
//			System.out.println("About to executeUpdate");
//			stmt.executeUpdate();
//			if (ret != 0) {
//				System.out.println("executeUpdate returns no error");
//				return acc;
//			} else {
//				System.out.println("executeUpdate returns error");
//				return null;
//			}
			
			
		} catch(SQLException e) {
			// Step 5: Perform any exception handling in an appropriate means
			
			// This particular example might not be what you want to ultimately use
			e.printStackTrace();
		}
		
		return 0;

	}
	
	
	@Override
	public List<Account> findAll() {
//		// In this method, we are planning on returning ALL Account objects
//		// So we prepare the List<Account> at the top
//		List<Account> allAccounts = new ArrayList<Account>();
//		
//		// Below is a try-with-resources block
//		// It allows us to instantiate some variable for use only inside the try block
//		// And then at the end, it will automatically invoke the close() method on the resource
//		// The close() method prevents memory leaks
//		try (Connection conn = ConnectionUtil.getConnection()) {
//			String sql = "SELECT * FROM bank_account INNER JOIN bank_roles ON bank_user.role_id = bank_roles.role_id";
//			
//			Statement stmt = conn.createStatement();
//			
//			
//			// Steps 1 - 3 are the same as listed above, except there is
//			// a ResultSet returned from executing our statement
//			
//			// We must make sure to use this ResultSet to save our data to the List that was prepared at the top
//			// The ResultSet interface represents all of the data obtained from our query.
//			// It has data for every column that we obtained from our query, per record
//			ResultSet rs = stmt.executeQuery(sql);
//			
//			// ResultSets are similar to iterators, so this while-loop will allow us to iterate over every record
//			while(rs.next()) {
//				// We obtain the data from every column that we need
//				
//				int id = rs.getInt("user_id");
//				String username = rs.getString("user_name");
//				String password = rs.getString("user_password");
//				String firstName = rs.getString("first_name");
//				String lastName = rs.getString("last_name");
//				String email = rs.getString("email");
//				int roleId = rs.getInt("role_id");
//				String roleName = rs.getString("role_name");
//				
//				// And use that data to create a User object accordingly
//				Role role = new Role(roleId, roleName);
//				Account A = new Account(id, balance, statusId, typeID, nickname);
//				
//				// Then we make sure to add this User to our list
//				allAccounts.add(u);
//			}
//		} catch(SQLException e) {
//			// If something goes wrong, return an empty list
//			e.printStackTrace();
//			return new ArrayList<>();
//		}
//		
//		// At the end we simply return all of our Users
//		return allUsers;
		return null;
	}
//
//	@Override
//	public User findById(int id) {
//		try (Connection conn = ConnectionUtil.getConnection()) {
//			String sql = "SELECT * FROM bank_user INNER JOIN bank_roles ON bank_user.role_id = bank_roles.role_id WHERE user_id = ?";
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			stmt.setInt(1, id);
//			
//			ResultSet rs = stmt.executeQuery();
//			
//			while(rs.next()) {
//				String username = rs.getString("user_name");
//				String password = rs.getString("user_password");
//				String firstName = rs.getString("first_name");
//				String lastName = rs.getString("last_name");
//				String email = rs.getString("email");
//				int roleId = rs.getInt("role_id");
//				String roleName = rs.getString("role_name");
//				
//				// And use that data to create a User object accordingly
//				Role role = new Role(roleId, roleName);
//				return new User(id, username, password, firstName, lastName, email, role);
//			}
//		} catch(SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return null;
//	}
//
//
// 	@Override
//	public int update(User u) {
//		// NOT WORKING YET!!!!
//		
//		
//		// Step 1: Get a Connection using ConnectionUtil
//		// The Connection interface represents the physical connection to the database
//		try (Connection conn = ConnectionUtil.getConnection()) {
//
//			// Step 2: Define our SQL Statements
//			
//			int userId = u.getUserId();
//			String username = u.getUsername();
//			String password = u.getPassword();
//			String firstName = u.getFirstName();
//			String lastName = u.getLastName();
//			String email = u.getEmail();
//			Role role = u.getRole();
//			boolean firstFieldToUpdateFound = false;
//
//			String sql = "UPDATE bank_user SET ";
//			
//			if (username != null) {
//				if (firstFieldToUpdateFound) {
//					sql += ", user_name = '" + username + "'";
//				} else {
//					sql += "user_name = '" + username + "'";
//					firstFieldToUpdateFound = true;
//				}
//			}
//
//			if (password != null) {
//				if (firstFieldToUpdateFound) {
//					sql += ", user_password = '" + password + "'";
//				} else {
//					sql += "user_password = '" + password + "'";
//					firstFieldToUpdateFound = true;
//				}
//			}
//			
//			if (firstName != null) {
//				if (firstFieldToUpdateFound) {
//					sql += ", first_name = '" + firstName + "'";
//				} else {
//					sql += "first_name = '" + firstName + "'";
//					firstFieldToUpdateFound = true;
//				}
//			}
//
//			if (lastName != null) {
//				if (firstFieldToUpdateFound) {
//					sql += ", last_name = '" + lastName + "'";
//				} else {
//					sql += "last_name = '" + lastName + "'";
//					firstFieldToUpdateFound = true;
//				}
//			}
//			
//			if (email != null) {
//				if (firstFieldToUpdateFound) {
//					sql += ", email = '" + email + "'";
//				} else {
//					sql += "email = '" + email + "'";
//					firstFieldToUpdateFound = true;
//				}
//			}
//			
//			if (role != null) {
//				if (firstFieldToUpdateFound) {
//					sql += ", role_id = " + role.getRoleId();
//				} else {
//					sql += "role_id = " + role.getRoleId();
//					firstFieldToUpdateFound = true;
//				}
//			}
//			
//			sql += " WHERE user_id = " + userId; 
//			
//			System.out.println(sql);
//			//			String username = u.getUsername();
//			
//			
//			
////			+ columns + " = ? WHERE user_id = ?";
//				
//			
//			
//			// The ? marks are placeholders for input values
//			// They work for PreparedStatements, and are designed to protect from SQL Injection
//			
//			// Step 3: Obtain our Statement object
//			// PreparedStatements are a sub-interface of Statement that provide extra security to prevent
//			// SQL Injection. It accomplishes this by allowing to use ? marks that we can replace
//			// with whatever data we want
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			
//			// Step 3b: If we are using a PreparedStatement, then inject values to replace the ? marks
//			// We insert values into each of the ? marks above
//			// Note: It is HEAVILY Recommended to use PreparedStatements instead of String concatenation
////			stmt.setString(1, u.getUsername()); // replace 1st ? mark with u.getUsername()
////			stmt.setInt(2, u.getUserId()); // replace 2nd ? mark with u.getPassword()
////			stmt.setString(3, u.getFirstName()); // replace 3rd ? mark with u.getFirstName()
////			stmt.setString(4, u.getLastName());
////			stmt.setString(5, u.getEmail());
////			stmt.setInt(6, u.getRole().getRoleId());
//			
//			// Step 4: Execute the Statement
//			return stmt.executeUpdate();
//			
//		} catch(SQLException e) {
//			// Step 5: Perform any exception handling in an appropriate means
//			
//			// This particular example might not be what you want to ultimately use
//			e.printStackTrace();
//		}
//		
//		return 0;
//	}
//
//	@Override
//	public int delete(int id) {
//		// Step 1: Get a Connection using ConnectionUtil
//		// The Connection interface represents the physical connection to the database
//		try (Connection conn = ConnectionUtil.getConnection()) {
//			
//			// Step 2: Define our SQL Statements
//			String columns = "user_id";
//			String sql = "DELETE FROM bank_user WHERE " + columns + " = ?";
//			// Step 3: Obtain our Statement object
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			
//			// Step 3b: If we are using a PreparedStatement, then inject values to replace the ? marks
//			// We insert values into each of the ? marks above
//			// Note: It is HEAVILY Recommended to use PreparedStatements instead of String concatenation
//			stmt.setInt(1, id);
//
//			// Step 4: Execute the Statement
//			return stmt.executeUpdate();
//			
//		} catch(SQLException e) {
//			// Step 5: Perform any exception handling in an appropriate means
//			
//			// This particular example might not be what you want to ultimately use
//			e.printStackTrace();
//		}
//		return 0;
//	}
//


	@Override
	public long highestAccountId() {
		// TODO Auto-generated method stub
		System.out.println("ENTERING highestAccountId Method");
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "SELECT MAX(account_id) FROM bank_account";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			long accountId = 0;
			if (rs.next()) {
				accountId = rs.getLong("Max(accountId)");
			}
			System.out.println("Account ID = " + accountId);
			ResultSet rs1 = st.executeQuery("Select * from bank_account where accountId ="+accountId+"");
				while (rs1.next()) {
//					System.out.println(rs1.getString(1)+" "+rs1.getString(2)+" "+rs1.getString(3)+" "+rs1.getString(4));
				}
			//			PreparedStatement stmt = null;
//			try {
//				ps = conn.prepareStatement(sql);
//			}
//			
//			// Step 2: Define our SQL Statements
//			Statement stmt = conn.prepareStatement().createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT MAX(account_id) FROM bank_account");
//			rs = stmt.executeQuery(sql);
			return accountId;
			
			// Step 3: Obtain our Statement object
			// PreparedStatements are a sub-interface of Statement that provide extra security to prevent
			// SQL Injection. It accomplishes this by allowing to use ? marks that we can replace
			// with whatever data we want
			
			// Step 3b: If we are using a PreparedStatement, then inject values to replace the ? marks
			// We insert values into each of the ? marks above
			// Note: It is HEAVILY Recommended to use PreparedStatements instead of String concatenation
		//	stmt.
			
			// Step 4: Execute the Statement
//			System.out.println("About to executeUpdate");
//			stmt.executeUpdate();
//			if (ret != 0) {
//				System.out.println("executeUpdate returns no error");
//				return acc;
//			} else {
//				System.out.println("executeUpdate returns error");
//				return null;
//			}
			
			
		} catch(SQLException e) {
			// Step 5: Perform any exception handling in an appropriate means
			
			// This particular example might not be what you want to ultimately use
			e.printStackTrace();
		}
		
		return 0;

	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Account u) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int deposit(int a, int u, double amt) {
		// Step 1: Get a Connection using ConnectionUtil
		// The Connection interface represents the physical connection to the database
		System.out.println("ENTERING deposit Method");
		try (Connection conn = ConnectionUtil.getConnection()) {
			
						
			// Step 2: Define our SQL Statements

			//		SELECT * FROM bank_account WHERE ACCOUNT_ID = 1 AND ACCOUNT_OWNER = 1;
//			String column1 = "account_id";
//			String column2 = "account_owner";
//			String column3 = "balance";
//			System.out.println("Original Balance = " + originalBalance);
//			System.out.println("New Balance = " + newBalance);
			String sql = "UPDATE bank_account SET balance = balance + " + amt + " WHERE account_id = " + a + " AND account_owner = " + u;
//			String sql = "SELECT * FROM bank_account WHERE " + column1 + " = ? AND " + column2 + " = ?";
			System.out.println(sql);
			// The ? marks are placeholders for input values
			// They work for PreparedStatements, and are designed to protect from SQL Injection
			
			// Step 3: Obtain our Statement object
			// PreparedStatements are a sub-interface of Statement that provide extra security to prevent
			// SQL Injection. It accomplishes this by allowing to use ? marks that we can replace
			// with whatever data we want
			Statement stmt = conn.createStatement();


			
			// Step 3b: If we are using a PreparedStatement, then inject values to replace the ? marks
			// We insert values into each of the ? marks above
			// Note: It is HEAVILY Recommended to use PreparedStatements instead of String concatenation
			
			System.out.println("Get Account ID = " + a);
			System.out.println("Get Owner ID = " + u);
			
			
			// Step 4: Execute the Statement
			return stmt.executeUpdate(sql);

			
			
//			while(rs.next()) {
//				double originalBalance = rs.getDouble("balance");
//				double newBalance = originalBalance + amt;
//				System.out.println("Original Balance = " + originalBalance);
//				System.out.println("New Balance = " + newBalance);
//
//				String column3 = "balance";
//				sql = "UPDATE bank_account SET " + column3 + " = " + newBalance + " WHERE " + column1 + " = " + a + " AND " + column2 + " = " + u;
//				stmt = conn.prepareStatement(sql);
//				
////				stmt.setInt(1, a); 
////				stmt.setInt(2, u);
// 
//				System.out.println(sql);
//				
//				return stmt.executeUpdate();
//				
//			}

			
//            System.out.println("AffectedRows = " + affectedRows);
//			stmt.execute();
			//			System.out.println("About to executeUpdate");
//			return stmt.executeUpdate();
//			if (ret != 0) {
//				System.out.println("executeUpdate returns no error");
//				return acc;
//			} else {
//				System.out.println("executeUpdate returns error");
//				return null;
//			}
			
			
		} catch(SQLException e) {
			// Step 5: Perform any exception handling in an appropriate means
			
			// This particular example might not be what you want to ultimately use
			e.printStackTrace();
		}
		
		return 0;

	}

	@Override
	public int withdraw(int a, int u, double amt) {
		// TODO Auto-generated method stub
		return 0;
	}
}
