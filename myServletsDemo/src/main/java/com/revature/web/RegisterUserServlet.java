package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.IUserDAO;
import com.revature.dao.UserDAO;
import com.revature.models.Role;
import com.revature.models.User;

public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private static Connection conn;  // Make sure that the driver software is included in lib.
	// we create an init() method, 
	// we create a specific service() -->  doGet() method OR doPost();
	// we will create a destroy()
	
//	public void init() throws ServletException {
//		// we will establish JDBC connection here
//		// ...because it's called once
//		System.out.println(this.getServletName() + " INSTANTIATED!");
//		super.init();
//		
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "bill", "123");
//			System.out.println("Connected!");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	// since we are CREATING a user and changing the database
	// we must use the POST HTTP method...we do so in a servlet with the doPost().
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		// we'll eventually use a SQL insert statement here
		// the info that we're posting/inserting to the database comes from PARAMETERS
		
		String firstname = req.getParameter("firstName"); // make sure this matched the "name" value in your HTML!
		String lastname = req.getParameter("lastName");
		String username = req.getParameter("userName");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		RequestDispatcher rdObj = null;
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");

		if (firstname.equals("") || lastname.equals("") || username.equals("") || email.equals("") || password.equals("")) {
            out.write("<p id='errMsg' style='color: red; font-size: larger;'>Please fill out all fields.</p>");
			rdObj = req.getRequestDispatcher("registerUser.html");
			rdObj.include(req, res);
		
		} else {
			IUserDAO dao = new UserDAO();
			User user = new User(0, username, password, firstname, lastname, email, new Role(1, "Standard"));
			int x = dao.insert(user);
			System.out.println(x);
				
			if (x > 0) {
	            out.write("<p id='errMsg' style='color: red; font-size: larger;'>User Created!.</p>");
				rdObj = req.getRequestDispatcher("login.html");
				rdObj.forward(req, res);
			} else {
	            out.write("<p id='errMsg' style='color: red; font-size: larger;'>Error creating user. Consider a different User Name.</p>");
				rdObj = req.getRequestDispatcher("registerUser.html");
				rdObj.include(req, res);
			}
		}
	}
	
	
//	public void destroy() {
//		System.out.println(this.getServletName() + " DESTROYED!");
//		super.destroy();
//		// we will also close our connection
//		try {
//			conn.close();
//			System.out.println("Connection closed.");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

}