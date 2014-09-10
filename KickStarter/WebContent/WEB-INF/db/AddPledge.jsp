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
<title>Pledge</title>
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
		
       <form action='AddPledge' method='post'>
       <p>Pledge Amount: $ <input size=10 name=pledgeAmount /></p>

       <%-- ${param.projectID} --%>
       <table border=0>
       
       <c:forEach items="${csrewards}" var="entry" varStatus="status">
       <%-- <td><c:if test="${project.id}"></c:if></td> --%>
			<c:if test="${entry.pid == param.projectID}">
			<%-- ${entry.pid} == ${param.projectID} --%>
			<%-- <tr><td>${entry.pid}</td><td>${entry.rid}</td></tr> --%>
			<%-- ${rewards.rid}--%>
				<tr><td><input type=radio name=rewardAmount value=${ entry.amount} />
				<b>Pledge $${entry.amount} or more</b></td>
				<td>${entry.description} </td></tr>
				<input type=hidden name=projectID value=${ param.projectID} />
			</c:if>
		</c:forEach>
       </table>
       	
   		<input name=submit value=Pledge type=submit />
   		</form>
		<a href=DisplayAllProjects>All Projects</a>

</body>
</html>