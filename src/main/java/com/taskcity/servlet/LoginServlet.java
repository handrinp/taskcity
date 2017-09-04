package com.taskcity.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taskcity.data.DataFactory;
import com.taskcity.data.UserDataSource;
import com.taskcity.data.dto.UserDTO;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 8387957782626041062L;
	private UserDataSource userDataSource;

	@Override
	public void init() {
		userDataSource = DataFactory.getInstance()
				.createUserDataSource();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String to = "/";
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		UserDTO userDTO;

		if (username != null && (userDTO = userDataSource.getUser(username)) != null) {
			session.setAttribute("userDTO", userDTO);
			to = "/tasks";
		} else {
			session.removeAttribute("userDTO");
			session.setAttribute("error", "Invalid username");
		}

		response.sendRedirect(to);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}
