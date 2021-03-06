package com.taskcity.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taskcity.data.DataFactory;
import com.taskcity.data.UserDataSource;

public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 3512941895153923514L;
	private UserDataSource userDataSource;

	@Override
	public void init() {
		userDataSource = DataFactory.getInstance()
				.createUserDataSource();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String to = "/";
		String username = request.getParameter("username");
		String subjects = request.getParameter("subjects");
		String password = request.getParameter("password");

		if (isValid(username)) {
			if (isValid(subjects)) {
				userDataSource.createNewUser(username, subjects, password);
				to = "/login?username=" + username;
			} else {
				request.setAttribute("error", "You must enter valid subjects");
			}
		} else {
			request.setAttribute("error", "Invalid URL");
		}

		response.sendRedirect(to);
	}

	private boolean isValid(String var) {
		return !(var == null || var.isEmpty());
	}
}
