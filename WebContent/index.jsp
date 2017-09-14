<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	session.removeAttribute("userDTO");
	session.removeAttribute("newUsername");
	session.removeAttribute("loggedIn");
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
<%@include file="/metaTags.html"%>
<title>taskcity</title>
<link rel="stylesheet" href="css/frontpage-styles.css">
<link rel="icon" href="images/favicon.ico">
</head>
<body>
	<div id="mainContent">
		<div id="scheduleTable">
			<div id="headerRow" class="tableRow">
				<div class="cw">taskcity</div>
			</div>
			<div id="tableCells">
				<div class="cp tableRow oddRow">
					<p>
						<span>a website for saving tasks and reminders</span>
					</p>
				</div>
				<div class="cp tableRow evenRow">
					<p>
						<span>&#9679; beautiful on all devices</span>
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
	<script type="text/javascript" src="js/jquery.js"></script>
	<%
		String err = (String) session.getAttribute("error");

		if (err != null) {
			session.removeAttribute("error");
	%>
	<div id="popUnder"></div>
	<div id="errorPopUp">
		<div class="popUpRow">
			<h2>Error</h2>
		</div>
		<div class="popUpRow">
			<p><%=err%></p>
		</div>
		<div class="popUpRow">
			<button type="button" class="deleteButton"
				onclick="closeErrorPopUp()">&#x2717;</button>
		</div>
	</div>
	<script type="text/javascript">
		$('document').ready(function() {
			$('#popUnder').css('display', 'block');
			$('#errorPopUp').css('display', 'block');
		});

		function closeErrorPopUp() {
			$('#popUnder').css('display', 'none');
			$('#errorPopUp').css('display', 'none');
		}
	</script>
	<%
		} else {
			String attemptedUser = (String) session.getAttribute("attemptedUser");

			if (attemptedUser != null) {
	%>
	<div id="popUnder"></div>
	<div id="errorPopUp">
		<div class="popUpRow">
			<h2>User does not exist</h2>
		</div>
		<div class="popUpRow">
			<button type="button" class="loginButton" onclick="createUser()">Create
				User</button>
			<button type="button" class="deleteButton"
				onclick="closeErrorPopUp()">&#x2717;</button>
		</div>
	</div>
	<script type="text/javascript">
		$('document').ready(function() {
			$('#popUnder').css('display', 'block');
			$('#errorPopUp').css('display', 'block');
		});

		function closeErrorPopUp() {
			$('#popUnder').css('display', 'none');
			$('#errorPopUp').css('display', 'none');
		}

		function createUser() {
			window.location = "/account";
		}
	</script>
	<%
		}
		}
	%>
</body>
</html>
