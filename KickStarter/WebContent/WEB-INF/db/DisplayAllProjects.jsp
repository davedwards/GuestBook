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
<title>CS320 Starter</title>
</head>
<body>
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

<%-- if the guest book is empty, display "no comments yet." --%>
<c:if test="${fn:length(csprojects)== 0}">
<p>No projects yet.</p>
</c:if>

<%-- if the guest book is not empty, display the entries in a table. --%>
<c:if test="${fn:length(csprojects)>0}">
    <table border="1">
  <tr><th>Project</th><th>Posted By</th><th>Start Date</th><th>Days To Go</th>
  <th>Percentage Funded</th><th>Amount Pledged</th></tr>
<c:forEach items="${csprojects}" var="entry" varStatus="status">

<c:if test="${entry.daysToGo > 0}">
  <tr><td><a href="DisplayProject?id=${entry.id}" >${entry.title}</a></td>
    <td>${entry.creator}</td>
    <td>${entry.startDateString}</td>
    <td>${entry.daysToGo}</td>
    <td><fmt:formatNumber type="number" maxIntegerDigits="2" value="${entry.percentFunded}" />%</td>
    <td>${entry.totalPledgeAmount}</td></tr>
    </c:if>
</c:forEach>
</table>
</c:if>

<p><a href="CreateProject">Create Project</a></p>
</body>
</html>

