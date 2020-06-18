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
import com.revature.models.User;

public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection conn;  // Make sure that the driver software is included in lib.
	// we create an init() method, 
	// we create a specific service() -->  doGet() method OR doPost();
	// we will create a destroy()
	
	public void init() throws ServletException {
		// we will establish JDBC connection here
		// ...because it's called once
		System.out.println(this.getServletName() + " INSTANTIATED!");
		super.init();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "bill", "123");
			System.out.println("Connected!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		// we'll eventually use a SQL insert statement here
		// the info that we're posting/inserting to the database comes from PARAMETERS

		RequestDispatcher rdObj = null;
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");

		String enteredUsername = req.getParameter("username");
		String enteredPassword = req.getParameter("password");
		String enteredAccountNumber = req.getParameter("accountNumber");
		String enteredWithdrawAmount = req.getParameter("withdrawAmount");
		double withdrawAmount = Double.parseDouble(enteredWithdrawAmount); 
		
		
		System.out.println("Entered Username = " + enteredUsername);
		System.out.println("Entered Password = " + enteredPassword);
		System.out.println("Entered Account Number = " + enteredAccountNumber);
		System.out.println("Entered Withdraw Amount = " + enteredWithdrawAmount);
		
		UserDAO dao = new UserDAO();
		User user = dao.findByUsername(enteredUsername);
		if (user != null) {
			int userId = user.getUserId();
			System.out.println("userId = "+ userId);
			if (enteredPassword.equals(user.getPassword())) {
				// Success!
				
				if (enteredUsername.equals("") || enteredPassword.equals("") || enteredAccountNumber.equals("") || enteredWithdrawAmount.equals("")) {
		            out.write("<p id='errMsg' style='color: red; font-size: larger;'>Please fill out all fields.</p>");
					rdObj = req.getRequestDispatcher("withdraw.html");
					rdObj.include(req, res);
				
				} else {
					// Create the bank_account if the rest of the info is there.
					String sql = "UPDATE bank_account SET balance = balance - " + withdrawAmount + " WHERE ACCOUNT_ID = " + enteredAccountNumber + " AND ACCOUNT_OWNER = " + userId;
					System.out.println(sql);
					try {
						Statement stmt = conn.createStatement();
						int result = stmt.executeUpdate(sql);
						
					
						
						if(result > 0) {
							out.println("<h1>$"+withdrawAmount+" Withdrawn from "+enteredUsername+ "'s account</h1>");
						} else {
							out.println("<h1>Error</h1>");
						}
						
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
//					try {
//						stmt = conn.createStatement();
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//
//					try {
//						System.out.println(stmt.executeUpdate(sql));
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
				}
				
				rdObj = req.getRequestDispatcher("welcome.html");
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

	public void destroy() {
		System.out.println(this.getServletName() + " DESTROYED!");
		super.destroy();
		// we will also close our connection
		try {
			conn.close();
			System.out.println("Connection closed.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
