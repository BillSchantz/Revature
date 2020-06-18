package com.revature.web;

//import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.IUserDAO;
import com.revature.dao.UserDAO;
import com.revature.models.User;

public class LoginServlet extends HttpServlet {
	
	/**
	 * 
	 */
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
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		System.out.println("Begin LoginServlet DoGet");
		String enteredEmail = req.getParameter("email");
		String enteredUsername = req.getParameter("username");
		String enteredPassword = req.getParameter("password");
		
		System.out.println("Entered Email: " + enteredEmail);
		System.out.println("Entered Username: " + enteredUsername);
		System.out.println("Entered Password: " + enteredPassword);

		HttpSession session = req.getSession();
		RequestDispatcher rdObj = null;
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		
		if (enteredPassword.equals("")) {
            out.write("<p id='errMsg' style='color: red; font-size: larger;'>Please enter either a username or email and a password.</p>");
			rdObj = req.getRequestDispatcher("login.html");
			rdObj.include(req, res);
		} else {
			if (enteredUsername.equals("") && enteredEmail.equals("")) {
				// return to login
	            out.write("<p id='errMsg' style='color: red; font-size: larger;'>Both email and username are blank. Please enter one of them.</p>");
				rdObj = req.getRequestDispatcher("login.html");
				rdObj.include(req, res);
			} else if (enteredUsername.equals("") && !enteredEmail.equals("")) {
				// search by email
				System.out.println("Email has been entered");
				IUserDAO dao = new UserDAO();
				User user = dao.findByEmail(enteredEmail);
				if (user != null) {
					System.out.println("Retrieved Id: " + user.getUserId());
					System.out.println("Retrieved Username: " + user.getUsername());
					System.out.println("Retrieved Password: " + user.getPassword());
					System.out.println("Retrieved Email: " + user.getEmail());
					System.out.println("Retrieved First Name: " + user.getFirstName());
					System.out.println("Retrieved Last Name: " + user.getLastName());
					System.out.println("Retrieved Role ID: " + user.getRole().getRoleId());
					System.out.println("Retrieved Role Name: " + user.getRole().getRole());

					if (enteredPassword.equals(user.getPassword())) {
						// Success!
						out.write("<p id='errMsg' style='color: blue; font-size: larger;'>Password for " + enteredEmail + " Verified!</p>");
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
						session.setAttribute("user", user);
						rdObj.forward(req, res);
					} else {
			            out.write("<p id='errMsg' style='color: red; font-size: larger;'>Password Incorrect for " + enteredEmail + "!</p>");
						rdObj = req.getRequestDispatcher("login.html");
						rdObj.include(req, res);
					}
				} else {
		            out.write("<p id='errMsg' style='color: red; font-size: larger;'>Email Not Found!</p>");
					rdObj = req.getRequestDispatcher("login.html");
					rdObj.include(req, res);
				}
			} else if ((!enteredUsername.equals("") && enteredEmail.equals("")) || 
					  (!enteredUsername.equals("") && !enteredEmail.equals(""))) {
				// search by username
				System.out.println("Username has been entered");
				IUserDAO dao = new UserDAO();
				User user = dao.findByUsername(enteredUsername);
				System.out.println(user);
				if (user != null) {
					System.out.println("Retrieved Id: " + user.getUserId());
					System.out.println("Retrieved Username: " + user.getUsername());
					System.out.println("Retrieved Password: " + user.getPassword());
					System.out.println("Retrieved Email: " + user.getEmail());
					System.out.println("Retrieved First Name: " + user.getFirstName());
					System.out.println("Retrieved Last Name: " + user.getLastName());
					System.out.println("Retrieved Role ID: " + user.getRole().getRoleId());
					System.out.println("Retrieved Role Name: " + user.getRole().getRole());
					if (enteredPassword.equals(user.getPassword())) {
						// Success!
			            out.write("<p id='errMsg' style='color: blue; font-size: larger;'>Password for " + enteredUsername + " Verified!</p>");
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
						session.setAttribute("userId", user.getUserId());
						rdObj.forward(req, res);
					} else {
			            out.write("<p id='errMsg' style='color: red; font-size: larger;'>Password Incorrect for " + enteredUsername + "!</p>");
						rdObj = req.getRequestDispatcher("login.html");
						rdObj.include(req, res);
					}
				} else {
		            out.write("<p id='errMsg' style='color: red; font-size: larger;'>Username Not Found!</p>");
					rdObj = req.getRequestDispatcher("login.html");
					rdObj.include(req, res);
				}
			}
		}
		
		
//		ResultSet resultSet = statement.executeQuery("SELECT * FROM bank_user");
//		
//		// we still use Print Writer to build a dynamic HTML 
//		PrintWriter out = res.getWriter();
//		res.setContentType("text/html");
//		
//		out.println("<table>");  // First Name       Last Name         Email
//		out.println("<tr>");
//		out.println("<th>");
//		out.println("First Name"); // we will return a table in HTML of FN, LN, EMAIL.
//		out.println("</th>");
//		out.println("<th>");
//		out.println("Last Name");
//		out.println("</th>");
//		out.println("<th>");
//		out.println("Email");
//		out.println("</th>");
//		out.println("</tr>");
//		
//		while(resultSet.next()) {
//			// do something here....realted to getString() <td>
//			out.println("<tr>");
//			
//			out.println("<td>");
//			out.println(resultSet.getString("firstname"));
//			out.println("</td>");
//			
//			out.println("<td>");
//			out.println(resultSet.getString("lastname"));
//			out.println("</td>");
//			
//			out.println("<td>");
//			out.println(resultSet.getString("email"));
//			out.println("</td>");
//			
//			out.println("</tr>");
//		}
//		
//		out.println("</table>");
//
		
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




