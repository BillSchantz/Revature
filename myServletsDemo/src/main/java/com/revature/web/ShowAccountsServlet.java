package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.UserDAO;
import com.revature.models.User;

@WebServlet ("/showAccountsServlet")
public class ShowAccountsServlet extends HttpServlet {

		private static final long serialVersionUID = 1L;
		private static Connection conn;  // Make sure that the driver software is included in lib.

		
		public void init() throws ServletException {

			System.out.println(this.getServletName() + " INSTANTIATED!");
			super.init();
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			
			try {
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "bill", "123");
				System.out.println("Connected!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// we use a doGet which is IDEMPOTENT 
		protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

			RequestDispatcher rdObj = null;
			PrintWriter out = res.getWriter();
			res.setContentType("text/html");

			String enteredUsername = req.getParameter("username");
			String enteredPassword = req.getParameter("password");
			System.out.println("Entered Username = " + enteredUsername);
			System.out.println("Entered Password = " + enteredPassword);
			
			UserDAO dao = new UserDAO();
			User user = dao.findByUsername(enteredUsername);
			if (user != null) {
				int userId = user.getUserId();
				System.out.println("userId = "+ userId);
				if (enteredPassword.equals(user.getPassword())) {
					// Success!
					
					if (enteredUsername.equals("") || enteredPassword.equals("")) {
			            out.write("<p id='errMsg' style='color: red; font-size: larger;'>Please fill out all fields.</p>");
						rdObj = req.getRequestDispatcher("showAccounts.html");
						rdObj.include(req, res);
					
					} else {

						try {
						Statement statement = conn.createStatement();
						ResultSet resultSet = statement.executeQuery("SELECT * FROM bank_account WHERE account_owner = '"+userId+"'");
						
						// we still use Print Writer to build a dynamic HTML 
					    res.setContentType("text/html");
						
						out.println("<table>");  // First Name       Last Name         Email
						out.println("<tr>");
						out.println("<th>");
						out.println("Account Nickname"); // we will return a table in HTML of FN, LN, EMAIL.
						out.println("</th>");
						out.println("<th>");
						out.println("Balance");
						out.println("</th>");
						out.println("</tr>");
						
						while(resultSet.next()) {
							// do something here....realted to getString() <td>
							out.println("<tr>");
							
							out.println("<td>");
							out.println(resultSet.getString("account_nickname"));
							out.println("</td>");
							
							out.println("<td>");
							out.println(resultSet.getString("balance"));
							out.println("</td>");
							
							out.println("</tr>");
						}
						
						out.println("</table>");
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
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
