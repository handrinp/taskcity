package com.taskcity.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taskcity.data.DataFactory;
import com.taskcity.data.dto.UserDTO;

public class RemoveTaskServlet extends HttpServlet {
	private static final long serialVersionUID = -6703269195139774735L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		String taskid;
		UserDTO userDTO;
		String to = "/";

		if ((userDTO = (UserDTO) session.getAttribute("userDTO")) == null) {
			session.setAttribute("error", "You need to be logged in to delete a task");
		} else if ((taskid = request.getParameter("taskid")).isEmpty()) {
			session.setAttribute("error", "Need taskid parameter to delete a task");
		} else {
			// hack to prevent hashtag anger in the URL
			if (taskid.startsWith("HASHTAG")) {
				taskid = taskid.replace("HASHTAG", "#");
			}

			DataFactory.getInstance()
					.createTaskDataSource(userDTO.getUsername())
					.deleteTask(taskid);
			to = "/tasks";
		}

		response.sendRedirect(to);
	}
}
