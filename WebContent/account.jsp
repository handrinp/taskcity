<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.taskcity.data.dto.UserDTO"%>
<%
	final int NEW_USER = 0;
	final int HAS_PASSWORD = 1;
	final int NEEDS_PASSWORD = 2;

	UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");

	int status = userDTO == null ? NEW_USER : userDTO.hasPassword() ? HAS_PASSWORD : NEEDS_PASSWORD;
	String username = status == NEW_USER ? (String) session.getAttribute("newUsername") : userDTO.getUsername();

	if (username == null) {
		session.setAttribute("error", "You must enter a username");
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
<meta name="description"
	content="A website for saving and viewing tasks">
<meta name="author" content="Nick Handrick">
<title>taskcity | account</title>
<link rel="stylesheet" href="css/frontpage-styles.css">
<link rel="icon" href="images/favicon.ico">
</head>
<body>
	<div id="mainContent">
		<%
			switch (status) {
				case NEW_USER :
		%>
		<%@ include file="/accountNewUser.jspf"%>
		<%
			break;
				case HAS_PASSWORD :
		%>
		<%@ include file="accountHasPassword.jspf"%>
		<%
			break;
				case NEEDS_PASSWORD :
		%>
		<%@ include file="accountNeedsPassword.jspf"%>
		<%
			break;
			}
		%>
	</div>
</body>
<script>
	function cancel() {
		window.location = "/";
	}

	function validateForm() {
		var valid = true;

		var frm = document.forms["login"];
		var pass = frm["password"];
		var conf = frm["confirm"];
		var errMsg = document.getElementById("errorMsg");

		// pass === conf
		if (pass.value !== conf.value) {
			errMsg.innerHTML = "Your password and confirmation must match";
			valid = false;
		}

		// pass length >= 9
		if (pass.value.length < 9) {
			errMsg.innerHTML = "Your password must be at least 9 characters long";
			valid = false;
		}

		// show/hide forms
		fixLastRow(document.getElementById("lastRow"), valid);
		document.getElementById("errorDiv").style.display = valid ? "none"
				: "block";

		return valid;
	}
</script>
</html>
