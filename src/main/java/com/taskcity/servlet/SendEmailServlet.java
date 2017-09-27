package com.taskcity.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taskcity.TaskCity;

public class SendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = -8520796555328044427L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String messageBody = String.format("Contact info: %s%nIssue type: %s%nDevice: %s%nDescription: %s%n",
				request.getParameter("contact"), request.getParameter("issue"), request.getParameter("device"),
				request.getParameter("description"));
		boolean emailSent = TaskCity.getInstance()
				.sendEmail("TaskCity Bug/Suggestion Report", messageBody);

		String result = emailSent ? "Report filed successfully!" : "Something went wrong when filing a report :(";
		request.setAttribute("error", result);
		response.sendRedirect("/");
	}
}
