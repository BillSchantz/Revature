package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.AccountDAO;
import com.revature.dao.IUserDAO;
import com.revature.dao.UserDAO;
import com.revature.models.Account;
import com.revature.models.Role;
import com.revature.models.User;

public class CreateAccountServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
//		private static Connection conn;  // Make sure that the driver software is included in lib.
		// we create an init() method, 
		// we create a specific service() -->  doGet() method OR doPost();
		// we will create a destroy()
		
		// since we are CREATING a user and changing the database
		// we must use the POST HTTP method...we do so in a servlet with the doPost().
		protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
			// we'll eventually use a SQL insert statement here
			// the info that we're posting/inserting to the database comes from PARAMETERS

			RequestDispatcher rdObj = null;
			PrintWriter out = res.getWriter();
			res.setContentType("text/html");

			String enteredUsername = req.getParameter("username");
			String enteredPassword = req.getParameter("password");
			int accountType = Integer.parseInt(req.getParameter("accountType"));
			String accountNickname = req.getParameter("accountNickname");
			double beginningBalance = 0.00; 
			int beginningStatus = 2;  //Open

			IUserDAO dao = new UserDAO();
			User user = dao.findByUsername(enteredUsername);
			if (user != null) {
				int userId = user.getUserId();
				System.out.println("userId = "+ userId);
				if (enteredPassword.equals(user.getPassword())) {
					// Success!
					// Create the bank_account

					if (enteredUsername.equals("") || enteredPassword.equals("") || accountNickname.equals("")) {
			            out.write("<p id='errMsg' style='color: red; font-size: larger;'>Please fill out all fields.</p>");
						rdObj = req.getRequestDispatcher("createAccount.html");
						rdObj.include(req, res);
					
					} else {
						AccountDAO acc = new AccountDAO();
						Account account = new Account(0, beginningBalance, beginningStatus, accountType, accountNickname, userId);
						int x = acc.create(account);
;						long accountId = acc.highestAccountId();

						
						
						String accNick = account.getNickname();
						System.out.println("accountId = "+ accountId);
						System.out.println("nickname = "+ accNick);
						System.out.println(x);
//						System.out.println(acc.create(account));
							
//						if (x > 0) {
//							int y = acc.createAccountOwner(accountId, userId);
//							if (y > 0) {
//								out.println("Account Created!.</p>");
//								rdObj = req.getRequestDispatcher("login.html");
//								rdObj.forward(req, res);
//							} else
//								out.println("Account Not Created!.</p>");
//								rdObj = req.getRequestDispatcher("login.html");
//								rdObj.forward(req, res);
//						} else {
//							out.println("Account Not Created!.</p>");
//							rdObj = req.getRequestDispatcher("registerUser.html");
//							rdObj.include(req, res);
//						}
					}
					
					rdObj = req.getRequestDispatcher("welcome.html");
					req.setAttribute("userId", user.getUserId());
					req.setAttribute("username", user.getUsername());
					req.setAttribute("password", user.getPassword());
					req.setAttribute("email", user.getEmail());
					req.setAttribute("firstName", user.getFirstName());
					req.setAttribute("lastName", user.getLastName());
					req.setAttribute("roleId", user.getRole().getRoleId());
					req.setAttribute("roleId", user.getRole().getRole());
					req.setAttribute("user", user);
//					session.setAttribute("user", user);
					rdObj.forward(req, res);
				} else {
		            out.write("<p id='errMsg' style='color: red; font-size: larger;'>Password Incorrect!</p>");
					rdObj = req.getRequestDispatcher("createAccount.html");
					rdObj.include(req, res);
				}
			} else {
	            out.write("<p id='errMsg' style='color: red; font-size: larger;'>Username Not Found!</p>");
				rdObj = req.getRequestDispatcher("createAccount.html");
				rdObj.include(req, res);
			}
			
			
		}

		
}		
