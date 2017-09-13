package com.taskcity.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taskcity.Logger;
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
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Logger.setLogger(getServletContext());
		String username = request.getParameter("username");
		HttpSession session = request.getSession();
		session.removeAttribute("userDTO");
		String to = "/";

		if (username != null) {
			UserDTO userDTO = userDataSource.getUser(username);
			session.setAttribute("userDTO", userDTO);

			if (username.equals("test")) {
				to = "/tasks";
			} else {
				to = "/account";

				if (userDTO == null) {
					session.setAttribute("newUsername", username);
				}
			}
		} else {
			session.setAttribute("error", "You must enter a username");
		}

		response.sendRedirect(to);
	}
}
