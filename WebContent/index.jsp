<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	session.removeAttribute("userDTO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<meta name="description"
	content="A website for saving and viewing tasks">
<meta name="author" content="Nick Handrick">
<meta property="og:title" content="Task City">
<meta property="og:url" content="http://www.taskcity.tk/">
<meta property="og:description"
	content="A website for saving and viewing tasks">
<meta property="og:image" content="http://taskcity.tk/screenie.png">
<title>taskcity</title>
<link rel="stylesheet" href="css/styles.css">
<link rel="icon" href="images/favicon.ico">
</head>
<body>
	<div id="mainContent">
		<div id="scheduleTable">
			<div id="headerRow" class="tableRow">
				<%
					String err;
					if ((err = (String) session.getAttribute("error")) != null) {
						session.removeAttribute("error");
				%>
				<div class="cw errorRow"><%=err%></div>
				<%
					} else {
				%>
				<div class="cw">taskcity</div>
				<%
					}
				%>
			</div>
			<div id="tableCells">
				<div class="cp tableRow oddRow">
					<p>
						<span>a website for saving tasks and reminders that is...</span>
					</p>
				</div>
				<div class="cp tableRow evenRow">
					<p>
						<span>&#9679; beautiful looking on all devices</span>
					</p>
				</div>
				<div class="cp tableRow oddRow">
					<p>
						<span>&#9679; lightweight and very fast loading</span>
					</p>
				</div>
				<div class="cp tableRow evenRow">
					<p>
						<span> &#9679; <a
							href="https://github.com/handrinp/taskcity" target="new">open
								source</a> and completely free
						</span>
					</p>
				</div>
				<div class="cp tableRow oddRow">
					<p>
						<span>you can sign up for an account <a href="signup.html">here</a></span>
					</p>
				</div>
				<div class="cp tableRow evenRow">
					<p>
						<span>question? check <a href="faqs.html">here</a> first
						</span>
					</p>
				</div>
				<div class="cp tableRow oddRow">
					<p>
						<span>report any suggestions/glitches <a href="report.html">here</a></span>
					</p>
				</div>
			</div>
			<div id="lastRow" class="tableRow evenRow">
				<form action="/taskcity/login" method="GET">
					<div class="c1 desktop">
						<p>Login with your taskcity id</p>
					</div>
					<div class="c1 mobile">
						<p>Login</p>
					</div>
					<div class="c2">
						<input type="text" id="accountId" name="username">
					</div>
					<div class="c3">
						<input type="submit" value="Go" />
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
