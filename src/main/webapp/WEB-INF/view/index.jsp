<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta charset="utf-8">
<title>Welcome</title>
</head>
<body>
	<%-- <c:url value="/Hello" var="messageUrl" />
		<a href="${messageUrl}">Click to enter</a> --%>

	<center>
		<h1>登录页面</h1>
		<form action="dologin" method="post">
			用户名:<input type="text" name="username" /><br>
			<br> 密码:<input type="password" name="password" /> <input
				type="submit" value="登录" />
		</form>
	</center>


</body>
</html>
