<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>login</title>
	<link rel="stylesheet" href="resources/css/login_style.css" type="text/css">
</head>

<body>
	<form id="login" method="post" action="/login">
		<h1>Log in</h1>
		<fieldset id="inputs">
			<input id="username" type="login" name="login" placeholder="Your_Login" autofocus required>   
			<input id="password" type="password" name="password" placeholder="Your_Password" required>
		</fieldset>
		<fieldset id="actions">
			<input type="submit" id="submit" value="EnTeR">
			<a href="/saveDeveloper">Registration</a>
		</fieldset>
	</form>
</body>
</html>
