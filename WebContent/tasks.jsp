<%@page import="com.taskcity.data.dto.UserDTO"%>
<%@page import="com.taskcity.data.dto.TaskDTO"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
UserDTO userDTO;
if ((userDTO = (UserDTO)session.getAttribute("userDTO")) == null) {
	session.setAttribute("error", "You must be logged in to view that page");
	response.sendRedirect("/");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" http-equiv="encoding">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<meta name="robots" content="noindex">
<title>My Tasks</title>
<link rel="stylesheet" href="css/tasks-styles.css">
<link rel="icon" href="images/favicon.ico">
</head>
<body>
	<div id="mainContent">
		<div id="scheduleTable">
			<div id="headerRow">
				<div class="c1">Subject</div>
				<div class="c2">Task</div>
				<div class="c3 desktop">Due In</div>
				<div class="c3 mobile">Due</div>
				<div class="c4 desktop">Delete</div>
				<div class="c4 mobile">Del</div>
			</div>
			<div id="tableCells">
				<%
					List<TaskDTO> tasks = userDTO.loadSortedTasks();
					boolean isOdd = true;

					if (tasks.isEmpty()) {
						isOdd = !isOdd;
				%>
				<div id="cFull" class="tableRow oddRow">No more tasks :)</div>
				<%
					} else {

						for (int i = 0; i < tasks.size(); ++i) {
							TaskDTO task = tasks.get(i);
				%>
				<div class="tableRow <%=isOdd ? "odd" : "even"%>Row">
					<div class="c1<%=task.isUrgent() ? " urgent" : ""%>"><%=task.getSubject()%></div>
					<div class="c2<%=task.isUrgent() ? " urgent" : ""%>">
						<span><%=task.getDescription()%></span>
					</div>
					<div class="c3<%=task.isUrgent() ? " urgent" : ""%>">
						<p><%=task.dueString()%></p>
					</div>
					<div class="c4">
						<button class="deleteButton" type="button"
							onClick="removeTask('<%=task.getID()%>')">&#x2717;</button>
					</div>
				</div>
				<%
							isOdd = !isOdd;
						}
					}
				%>
			</div>
			<div id="lastRow" class="tableRow <%=isOdd ? "odd" : "even"%>Row">
				<div class="c1">
					<select id="subject">
						<%
						List<String> subjects = userDTO.getSubjects();
						for (int i = 0; i < subjects.size(); ++i) {
							String subject = subjects.get(i);
						%>
						<option value="<%=subject%>"><%=subject%></option>
						<%
						}						
						%>
					</select>
				</div>
				<div class="c2">
					<input type="text" id="task">
				</div>
				<div class="c3">
					<button type="button" class="popUpButton" onclick="showPopUp()">...</button>
				</div>
				<div class="c4">
					<button type="button" class="addButton" onclick="addTask()">+</button>
				</div>
			</div>
		</div>
	</div>
	<div id="popUnder"></div>
	<div id="popUp">
		<div class="popUpRow">
			<select id="formYear">
				<option value="2017" selected>2017</option>
				<option value="2018">2018</option>
			</select>
		</div>
		<div class="popUpRow">
			<select id="formMonth" onchange="validate('formMonth')"
				onblur="validate('formMonth')">
				<option value="0" selected>Select a Month</option>
				<%
					String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
							"October", "November", "December" };
					for (int i = 0; i < months.length; ++i) {
				%>
				<option value="<%=months[i]%>"><%=months[i]%></option>
				<%
					}
				%>
			</select>
		</div>
		<div class="popUpRow">
			<select id="formDay" onchange="validate('formDay')"
				onblur="validate('formDay')">
				<option value="0" selected>Select a Day</option>
				<%
					for (int i = 1; i <= 31; ++i) {
				%>
				<option value="<%=i%>"><%=i%></option>
				<%
					}
				%>
			</select>
		</div>
		<div class="popUpRow">
			<select id="formTime" onchange="validate('formTime')"
				onblur="validate('formTime')">
				<option value="0" selected>Select a Time</option>
				<%
					for (int i = 0; i < 24; ++i) {
						String ampm = "am";

						if (i >= 12) {
							ampm = "pm";
						}

						int fixedHour = i % 12;

						if (fixedHour == 0) {
							fixedHour = 12;
						}
				%>
				<option value="<%=i%>:00:00"><%=fixedHour%>:00<%=ampm%></option>
				<option value="<%=i%>:30:00"><%=fixedHour%>:30<%=ampm%></option>
				<%
					}
				%>
			</select>
		</div>
		<div class="popUpRow">
			<button type="button" class="deleteButton" onclick="closePopUp()">&#x2717;</button>
			<button type="button" class="submitPopUpButton"
				onclick="submitPopUp()">&#10004;</button>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/script.js"></script>
</body>
</html>
