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
import com.revature.models.User;


public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private static Connection conn;  // Make sure that the driver software is included in lib.
//
//	
//	public void init() throws ServletException {
//
//		System.out.println(this.getServletName() + " INSTANTIATED!");
//		super.init();
//		
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		}
//		
//		try {
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "bill", "123");
//			System.out.println("Connected!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	
	// we will ALSO use a doPost method here....but we will take in different parameters
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		
		String firstname = req.getParameter("firstName"); // make sure this matched the "name" value in your HTML!
		String lastname = req.getParameter("lastName");
		String username = req.getParameter("userName");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String userId = req.getParameter("userId");
		
		RequestDispatcher rdObj = null;
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		int userNum = Integer.parseInt(userId);
		
		
		
		IUserDAO dao = new UserDAO();
		User user = new User(userNum, username, password, firstname, lastname, email, null);
		int x = dao.update(user);

		System.out.println(x);
			
		if (x > 0) {
			//What do we display if successful?
			
			out.write("<p id='errMsg' style='color: red; font-size: larger;'>User Updated!.</p>");
			rdObj = req.getRequestDispatcher("welcome.html");
			rdObj.include(req, res);
		} else {
			//What do we display if successful?
            out.write("<p id='errMsg' style='color: red; font-size: larger;'>Error udpating user.</p>");
			rdObj = req.getRequestDispatcher("updateProfile.html");
			rdObj.include(req, res);
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