<html>
<%!String loginPage = "Login.html";%>
<head>
<title>Moeam Login</title>
</head>
<body>

	<br />
	<!-- HTML5 obsolete tag <center> -->

	<table width="500" border="0" cellpadding="4" cellspacing="0">
		<tr>
			<td bgcolor="lightred"><font color="white" face="Arial"><b>Moeam
						Login</b> </font></td>
		</tr>

		<%!String username, password;%>
		<%
			username = request.getParameter("userName");
			password = request.getParameter("passWord");

			// If a field is empty, display an error message
			if ((username == null) || (username != null && username.equals(""))
					|| (password == null)
					|| (password != null && password.equals(""))) {
		%>
		<tr>
			<td>
				<table>
					<tr>
						<td><b>One or more fields were empty - please go back and
								try again</b></td>
					</tr>
					<tr>
						<td><b><a href=<%=loginPage%>>Back to login page</a></b></td>
					</tr>
				</table> <br />
			</td>
		</tr>
		<%
			// otherwise if all fields have values, print them out
			} else {
		%>

		<tr>
			<td><b>You have entered the following:</b></td>
		</tr>
		<tr>
			<td>
				<table>
					<tr>
						<td><b>Username:</b></td>
						<td><%=username%></td>
					</tr>
					<tr>
						<td><b>Password:</b></td>
						<td><%=password%></td>
					</tr>
					<tr>
						<td><b><a href=<%=loginPage%>>Back to login page</a></b></td>
					</tr>
				</table> <br />
			</td>
		</tr>
	</table>

	<%
		}
	%>

	<!-- </center> -->
</body>
</html>
