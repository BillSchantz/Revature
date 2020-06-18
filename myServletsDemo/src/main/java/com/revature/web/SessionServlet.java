package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionServlet extends HttpServlet {	 
		private static final long serialVersionUID = 1L;

		public SessionServlet() {
			super();
			// TODO Auto-generated constructor stub
		}

		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	        HttpSession session = req.getSession();
	         
	        PrintWriter writer = res.getWriter();
	        writer.println("Session ID: " + session.getId());
	        writer.println("Creation Time: " + new Date(session.getCreationTime()));
	        writer. println("Last Accessed Time: " + new Date(session.getLastAccessedTime()));
			
		}
}
