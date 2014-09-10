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
	   <form action='AddRewards' method='post'>
       <table border=0>
       <tr><th>Add Rewards</th></tr>
       
       <tr><td>Pledge Amount:</td><td> $ <input size=10 name=pledgeAmount /></td>
       </tr><tr><td>Reward Description:</td>
       <td><textarea cols=40 rows=5 name=rewardDescription></textarea></td>
       </tr>
       <tr><td><input name=submit value=Add type=submit /> 
       <input name=submit value=Finish type=submit />
       </td></tr>
       </table>
       </form>

</body>
</html>