<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	session.removeAttribute("userDTO");
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
<title>taskcity</title>
<link rel="stylesheet" href="css/frontpage-styles.css">
<link rel="icon" href="images/favicon.ico">
</head>
<body>
	<div id="mainContent">
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
		<div id="scheduleTable">
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
						<span>question? check <a href="faqs">here</a> first
						</span>
					</p>
				</div>
				<div class="cp tableRow evenRow">
					<p>
						<span>report any suggestions/glitches <a href="report">here</a></span>
					</p>
				</div>
				<div id="lastRow" class="tableRow oddRow">
					<form action="/login" method="GET">
						<div class="c1 desktop">
							<p>Log in or sign up</p>
						</div>
						<div class="c1 mobile">
							<p>Log in</p>
						</div>
						<div class="c2">
							<input type="text" id="accountId" name="username">
						</div>
						<div class="c3">
							<button class="loginButton" type="submit">Go</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
