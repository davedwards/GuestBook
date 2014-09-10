<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%-- set data source --%>
<sql:setDataSource driver="com.mysql.jdbc.Driver"
	url="jdbc:mysql://localhost/cs320stu08" user="cs320stu08"
	password="bB.K0eGC" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>New User</title>
</head>
<body>

<h2 align="center">New User</h2>

<form action='NewUser' method='post'>
<table align="center" border="1" cellpadding="2" cellspacing="2" width="60%">
		<tr><td valign="top" width="50%">Username:<br /></td><td><input name="username" style="width: 100%;" type="text" /></td></tr>
		<tr><td>Password:<br /></td><td><input name="password" style="width: 100%;" type="password" /></td></tr>
		<tr><td>Retype Password:<br /></td><td><input name="repassword" style="width: 100%;" type="password" /></td></tr>
		<tr><td>First Name:<br /></td><td><input name="firstname" style="width: 100%;" type="text" /></td></tr>
		<tr><td>Last Name:</td><td><input name="lastname" style="width: 100%;" type="text" /></td></tr>
		<tr><td colspan="2"><input name="submit" type="submit" value="Submit" /></td></tr>
</table>
</form>
<table border="0" cellpadding="2" cellspacing="2" align="center" width="60%">
<tbody>
  	<tr><td valign="top" width="50%">
  	<c:if test="${not empty param.submit}">
	<c:choose>
		<c:when test="${empty param.username}">No Username Given</c:when>
		<c:when test="${empty param.password}">No Password Given</c:when>
		<c:when test="${param.repassword != param.password}">Passwords do not match</c:when>
		<c:when test="${empty param.firstname}">Missing First Name</c:when>
		<c:when test="${empty param.lastname}">Missing Last Name</c:when>
		
		<c:when test="${fn:length(param.username) < 4 }">Username must be at least 4 characters</c:when>
		<c:when test="${fn:length(param.password) < 4 }">Password must be at least 4 characters</c:when>
		
		
		
		<c:otherwise>

		<%-- query --%>
		<c:if test="${not empty param.submit}">
		<sql:update>
		  insert into users (username, password) values (? , ?)
		  <sql:param value="${param.username}" />
		  <sql:param value="${param.password}" />
		</sql:update>
		</c:if>
		${param.firstname} ${param.lastname}, your registration is complete.
		</c:otherwise>
	</c:choose>
	</c:if>
	</td></tr>
</tbody>
</table>
<a href="DisplayAllProjects">All Projects</a>
</body>
</html>