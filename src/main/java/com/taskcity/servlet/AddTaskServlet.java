package com.taskcity.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taskcity.data.DataFactory;
import com.taskcity.data.dto.TaskDTO;
import com.taskcity.data.dto.UserDTO;

public class AddTaskServlet extends HttpServlet {
	private static final long serialVersionUID = -6703269195139774735L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		UserDTO userDTO;
		String to = "/";

		if ((userDTO = (UserDTO) session.getAttribute("userDTO")) == null) {
			session.setAttribute("error", "You need to be logged in to add a task");
		} else if (hasInValidParams(request)) {
			session.setAttribute("error", "Need taskid parameter to delete a task");
		} else {
			String id = request.getParameter("taskID");
			String subject = hexToAscii(request.getParameter("taskSubject"));
			String desc = hexToAscii(request.getParameter("taskDescription"));
			long due = Long.parseLong(request.getParameter("taskDue"));
			TaskDTO newTask = new TaskDTO(id, subject, desc, due);
			DataFactory.getInstance()
					.createTaskDataSource(userDTO.getUsername())
					.addTask(newTask);
			to = "/tasks";
		}

		response.sendRedirect(to);
	}

	private boolean hasInValidParams(HttpServletRequest request) {
		return request.getParameter("taskID")
				.isEmpty()
				|| request.getParameter("taskSubject")
						.isEmpty()
				|| request.getParameter("taskDescription")
						.isEmpty();
	}

	private static String hexToAscii(String hexStr) {
		StringBuilder output = new StringBuilder();

		for (int i = 0; i < hexStr.length(); i += 2) {
			String str = hexStr.substring(i, i + 2);
			output.append((char) Integer.parseInt(str, 16));
		}

		return output.toString();
	}
}
