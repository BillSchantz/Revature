package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;


public class UserDAO implements IUserDAO {

	@Override
	public int insert(User u) {
		
		// Step 1: Get a Connection using ConnectionUtil
		// The Connection interface represents the physical connection to the database
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			// Step 2: Define our SQL Statements
			String columns = "user_name, user_password, first_name, last_name, email, role_id";
			String sql = "INSERT INTO bank_user (" + columns + ") VALUES (?, ?, ?, ?, ?, ?)";
			System.out.println(sql);
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, u.getUsername()); // replace 1st ? mark with u.getUsername()
			stmt.setString(2, u.getPassword()); // replace 2nd ? mark with u.getPassword()
			stmt.setString(3, u.getFirstName()); // replace 3rd ? mark with u.getFirstName()
			stmt.setString(4, u.getLastName());
			stmt.setString(5, u.getEmail());
			stmt.setInt(6, u.getRole().getRoleId());
			
			// Step 4: Execute the Statement
			return stmt.executeUpdate();
			
		} catch(SQLException e) {
			// Step 5: Perform any exception handling in an appropriate means
			
			// This particular example might not be what you want to ultimately use
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public List<User> findAll() {
		// In this method, we are planning on returning ALL User objects
		// So we prepare the List<User> at the top
		List<User> allUsers = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM bank_user INNER JOIN bank_roles ON bank_user.role_id = bank_roles.role_id";
			
			Statement stmt = conn.createStatement();
			
			
			ResultSet rs = stmt.executeQuery(sql);
			
			// ResultSets are similar to iterators, so this while-loop will allow us to iterate over every record
			while(rs.next()) {
				// We obtain the data from every column that we need
				
				int id = rs.getInt("user_id");
				String username = rs.getString("user_name");
				String password = rs.getString("user_password");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				int roleId = rs.getInt("role_id");
				String roleName = rs.getString("role_name");
				
				// And use that data to create a User object accordingly
				Role role = new Role(roleId, roleName);
				User u = new User(id, username, password, firstName, lastName, email, role);
				
				// Then we make sure to add this User to our list
				allUsers.add(u);
			}
		} catch(SQLException e) {
			// If something goes wrong, return an empty list
			e.printStackTrace();
			return new ArrayList<>();
		}
		
		// At the end we simply return all of our Users
		return allUsers;
	}

	@Override
	public User findById(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM bank_user INNER JOIN bank_roles ON bank_user.role_id = bank_roles.role_id WHERE user_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String username = rs.getString("user_name");
				String password = rs.getString("user_password");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				int roleId = rs.getInt("role_id");
				String roleName = rs.getString("role_name");
				
				// And use that data to create a User object accordingly
				Role role = new Role(roleId, roleName);
				return new User(id, username, password, firstName, lastName, email, role);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public User findByUsername(String username) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT * FROM bank_user INNER JOIN bank_roles ON bank_user.role_id = bank_roles.role_id WHERE user_name = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("user_id");
				String password = rs.getString("user_password");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				int roleId = rs.getInt("role_id");
				String roleName = rs.getString("role_name");
				
				// And use that data to create a User object accordingly
				Role role = new Role(roleId, roleName);
				return new User(id, username, password, firstName, lastName, email, role);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public User findByEmail(String email) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			System.out.println("Begin Find By Email method");

			String sql = "SELECT * FROM bank_user INNER JOIN bank_roles ON bank_user.role_id = bank_roles.role_id WHERE email = ?";
			System.out.println(sql);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			System.out.println(sql);
			
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("user_id");
				String password = rs.getString("user_password");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String username = rs.getString("user_name");
				int roleId = rs.getInt("role_id");
				String roleName = rs.getString("role_name");
				
				// And use that data to create a User object accordingly
				Role role = new Role(roleId, roleName);
				System.out.println("End Find By Email method");
				return new User(id, username, password, firstName, lastName, email, role);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

 	@SuppressWarnings("null")
	@Override
	public int update(User u) {
		// NOT WORKING YET!!!!
		
		
		// Step 1: Get a Connection using ConnectionUtil
		// The Connection interface represents the physical connection to the database
		try (Connection conn = ConnectionUtil.getConnection()) {

			// Step 2: Define our SQL Statements
			
			int userId = u.getUserId();
			String username = u.getUsername();
			String password = u.getPassword();
			String firstName = u.getFirstName();
			String lastName = u.getLastName();
			String email = u.getEmail();
			Role role = u.getRole();
			boolean firstFieldToUpdateFound = false;

			System.out.println("userId = " + userId);
			System.out.println("username = " + username);
			System.out.println("password = " + password);
			System.out.println("firstName = " + firstName);
			System.out.println("lastName = " + lastName);
			System.out.println("email = " + email);
			System.out.println("role = " + role);

			
			
			String sql = "UPDATE bank_user SET ";
			
			if (username !=null && !username.equals("")) {
				if (firstFieldToUpdateFound) {
					sql += ", user_name = '" + username + "'";
				} else {
					sql += "user_name = '" + username + "'";
					firstFieldToUpdateFound = true;
				}
			}

			if (password != null && !password.equals("")) {
				if (firstFieldToUpdateFound) {
					sql += ", user_password = '" + password + "'";
				} else {
					sql += "user_password = '" + password + "'";
					firstFieldToUpdateFound = true;
				}
			}
			
			if (firstName != null && !firstName.equals("")) {
				if (firstFieldToUpdateFound) {
					sql += ", first_name = '" + firstName + "'";
				} else {
					sql += "first_name = '" + firstName + "'";
					firstFieldToUpdateFound = true;
				}
			}

			System.out.println((lastName != null && !lastName.equals("")));
			
			if (lastName != null && !lastName.equals("")) {
				if (firstFieldToUpdateFound) {
					sql += ", last_name = '" + lastName + "'";
				} else {
					sql += "last_name = '" + lastName + "'";
					firstFieldToUpdateFound = true;
				}
			}
			
			if (email != null && !email.equals("")) {
				if (firstFieldToUpdateFound) {
					sql += ", email = '" + email + "'";
				} else {
					sql += "email = '" + email + "'";
					firstFieldToUpdateFound = true;
				}
			}
			
			if (role != null) {
				if (firstFieldToUpdateFound) {
					sql += ", role_id = " + role.getRoleId();
				} else {
					sql += "role_id = " + role.getRoleId();
					firstFieldToUpdateFound = true;
				}
			}
			
			sql += " WHERE user_id = " + userId; 
			
			System.out.println(sql);
			//			String username = u.getUsername();
			
			
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			
			// Step 4: Execute the Statement
			return stmt.executeUpdate();
			
		} catch(SQLException e) {
			// Step 5: Perform any exception handling in an appropriate means
			
			// This particular example might not be what you want to ultimately use
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int delete(int id) {
		// Step 1: Get a Connection using ConnectionUtil
		// The Connection interface represents the physical connection to the database
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			// Step 2: Define our SQL Statements
			String columns = "user_id";
			String sql = "DELETE FROM bank_user WHERE " + columns + " = ?";
			// Step 3: Obtain our Statement object
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			// Step 3b: If we are using a PreparedStatement, then inject values to replace the ? marks
			// We insert values into each of the ? marks above
			// Note: It is HEAVILY Recommended to use PreparedStatements instead of String concatenation
			stmt.setInt(1, id);

			// Step 4: Execute the Statement
			return stmt.executeUpdate();
			
		} catch(SQLException e) {
			// Step 5: Perform any exception handling in an appropriate means
			
			// This particular example might not be what you want to ultimately use
			e.printStackTrace();
		}
		return 0;
	}

}
