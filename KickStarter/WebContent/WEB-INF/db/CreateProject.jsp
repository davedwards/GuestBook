<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Project Details</title>
</head>
<body>
<%-- check if a user has logged in or not --%>
<p>
<c:choose>
<c:when test="${empty sessionScope.user}">
    <%-- <c:redirect url="Login.jsp" /> --%>
    <a href="Login">Login</a>
</c:when>
<c:otherwise>
    <a href="Logout">Logout</a>
</c:otherwise>
</c:choose>
 | <a href="NewUser">New User</a>
</p>
	   <form action='CreateProject' method='post'>
	   <table border = '1'>
	<%-- // check if the user's name is stored in session. If so, display the
       // user's name; otherwise display an input field. --%>
       
	   <tr><td>Your Name:</td><td> ${username}</td></tr>
	   <tr><td>Project Title:</td>
       <td><input size=40 name=title /></td></tr>
       <tr><td>Project Description:</td>
       <td><textarea cols=40 rows=5 name=projectDescription></textarea></td></tr>
       <tr><td>Funding Target:</td><td>$ <input size=40 name=fundTarget /></td></tr>
       <tr><td>Start Date:</td><td><input size=10 name=startDate /> (MM/dd/yyyy)</td>
       </tr><tr><td>Funding Duration:</td>
       <td> <input size=40 name=duration />  Days</td></tr>
       </table>
       <input type="hidden" name="projectID" value="${idSeed}" />
       <input name=submit value=Next type=submit />
       </form>

</body>
</html>