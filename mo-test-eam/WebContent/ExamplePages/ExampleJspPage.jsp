<!-- Yes this code is hella messy since HTML + JSP + Java code is integrated into one file. -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<title>Example JSP page</title>
</head>

<body>
	<%!// Set some variables to be used in this page
	String exampleJspPage = "ExampleJspPage.jsp";
	String name;%>

	<table width="500" border="0" cellpadding="4" cellspacing="0">
		<tr bgcolor="blue">
			<td><font color="white" face="Arial"><b>Example JSP
						page</b> </font></td>
		</tr>
		<tr bgcolor="#EEEEFF">
			<td>
				<table>
					<%
						// Request for the parameter "name" which is entered in the previous page and fed into this one
						name = request.getParameter("name");

						// If the parameter "name" is null or contains nothing, display an error message
						if ((name == null) || (name != null && name.equals(""))) {
					%>
					<tr>
						<td><b>Heya, I haven't seen you before! Tell me your
								name!</b></td>
					</tr>
					<tr>
						<td>
							<form method="post" action=<%=exampleJspPage%>>
								<input type="text" name="name" size="25" /> <br /> <br /> <input
									type="submit" value="Submit" />
							</form>
						</td>
					</tr>

					<%
						// Else if the parameter "name" is not null or contains something, say hi
						} else {
					%>
					<tr>
						<td>
							<table>
								<tr>
									<td><b>Hello, <%=name%>!
									</b> <br /> <br /> <br /> <a href=<%=exampleJspPage%>>Try
											again!</a></td>
								</tr>
							</table>
						</td>
					</tr>
					<%
						}
					%>
				</table>
			</td>
		</tr>
		<tr>
			<p>
				Compared to <a href="ExampleHtmlPage.html">ExampleHtmlPage</a>, a
				JSP lets you input data. Depending on what is inputted, you can
				design the Java code to respond accordingly and display it back onto
				the page using HTML code.
			</p>
		</tr>
	</table>
</body>

</html>