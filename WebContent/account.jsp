<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String username;

	if ((username = (String) session.getAttribute("username")) == null) {
		session.setAttribute("error", "You must enter a username");
		response.sendRedirect("/");
	} else {
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" http-equiv="encoding">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<meta name="description"
	content="A website for saving and viewing tasks">
<meta name="author" content="Nick Handrick">
<title>taskcity | account</title>
<link rel="stylesheet" href="css/frontpage-styles.css">
<link rel="icon" href="images/favicon.ico">
</head>
<body>
	<div id="mainContent">
		<form action="/createAccount" method="GET">
			<input type="hidden" name="username" value="<%=username%>">
			<div id="scheduleTable">
				<div id="headerRow" class="tableRow">
					<div class="cw">taskcity</div>
				</div>
				<div id="tableCells">
					<div class="tableRow oddRow">
						<p>
							The username "<%=username%>" doesn't exist. If you want to create
							that account, fill out the following info and sign up.
						</p>
					</div>
					<div class="tableRow evenRow">
						<div class="cl">
							<p>Subjects</p>
						</div>
						<div class="cr">
							<input type="text" id="subjects" name="subjects"
								value="Misc;School;Social;Work">
						</div>
					</div>
				</div>
				<div id="lastRow" class="tableRow oddRow">
					<div class="cleft">
						<button class="loginButton" type="submit">Sign up</button>
					</div>
					<div class="cright">
						<button class="deleteButton" onclick="cancel()">Cancel</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
<script>
	function cancel() {
		window.location = "/";
	}
</script>
</html>
<%
	}
%>
