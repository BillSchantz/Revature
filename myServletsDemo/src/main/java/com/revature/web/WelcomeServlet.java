package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.models.User;

public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection conn;  // Make sure that the driver software is included in lib.

	
	public void init() throws ServletException {

		System.out.println(this.getServletName() + " INSTANTIATED!");
		super.init();
	}
	
	// we will get the message that was set in the LoginServlet Request........
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		System.out.println("Begin WelcomeServlet ");
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
        out.write("<p id='errMsg' style='color: blue; font-size: larger;'>Welcome " + req.getParameterValues("firstName") + " " + req.getAttribute("lastName") + "!</p>");

        System.out.println("Passed user object = " + req.getAttribute("user"));
        System.out.println("Passed first name = " + req.getAttribute("firstName"));
 
//        int passedUserId = (int) session.getAttribute("userId");
//		session.dao.findByUsername(enteredUsername);
//        
//		User user = dao.findByUsername(enteredUsername);

        //		RequestDispatcher rdObj = null;
//		rdObj = req.getRequestDispatcher("welcome.html");
//		rdObj.include(req, res);
		
		if(req.getParameter("addAccountButton") != null) {
			//load login.html
			res.sendRedirect("createAccount.html");
			System.out.println(req.getParameter("addAccountButton") + " pressed");

		} else if (req.getParameter("showAccountsButton") != null) {
			//load registerUser.html
			res.sendRedirect("showAccounts.html");
			System.out.println(req.getParameter("showAccountsButton") + " pressed");
		
		} else if (req.getParameter("updateInfoButton") != null) {
			//load registerUser.html
			res.sendRedirect("updateUser.html");
			System.out.println(req.getParameter("updateInfoButton") + " pressed");
		
		} else if (req.getParameter("deleteUserButton") != null) {
			//load registerUser.html
			res.sendRedirect("deleteUser.html");
			System.out.println(req.getParameter("deleteUserButton") + " pressed");
		} else if (req.getParameter("depositButton") != null) {
			//load registerUser.html
			res.sendRedirect("deposit.html");
			System.out.println(req.getParameter("depositButton") + " pressed");
		} else if (req.getParameter("withdrawButton") != null) {
			//load registerUser.html
			res.sendRedirect("withdraw.html");
			System.out.println(req.getParameter("withdrawButton") + " pressed");
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
