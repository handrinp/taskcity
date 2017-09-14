<%@page import="com.taskcity.data.DataFactory"%>
<%@page import="com.taskcity.data.UserDataSource"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	UserDataSource ds = DataFactory.getInstance()
			.createUserDataSource();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" http-equiv="encoding">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1">
<meta name="description"
	content="A website for saving and viewing tasks - frequently asked questions">
<meta name="author" content="Nick Handrick">
<title>taskcity | FAQs</title>
<link rel="stylesheet" href="css/frontpage-styles.css">
<link rel="icon" href="images/favicon.ico">
</head>
<body>
	<div id="mainContent">
		<div id="scheduleTable">
			<div id="headerRow" class="tableRow">
				<div class="cw faqRow">taskcity - FAQs</div>
			</div>
			<div id="tableCells">
				<div class="cp tableRow oddRow">
					<p>
						<span>&#9679; how do I get an account?</span>
					</p>
				</div>
				<div class="cp tableRow evenRow">
					<p>
						<span>on the <a href="../">main page</a>, enter a new
							username and press "Go"
						</span>
					</p>
				</div>
				<div class="cp tableRow oddRow">
					<p>
						<span>&#9679; how many users exist currently?</span>
					</p>
				</div>
				<div class="cp tableRow evenRow">
					<p>
						<span><%=ds.numUsers() - 1%> users, including myself :)</span>
					</p>
				</div>
			</div>
			<div id="lastRow" class="tableRow oddRow">
				<div class="cw">
					<p>
						<a href="../">back to the main page</a>
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
