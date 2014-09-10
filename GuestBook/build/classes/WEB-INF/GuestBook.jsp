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
<title>GuestBook</title>
</head>
<body>

<%-- if the guest book is empty, display "no comments yet." --%>
<c:if test="${fn:length(gbentries)== 0}">
<p>No comments yet.</p>
</c:if>

<%-- if the guest book is not empty, display the entries in a table. --%>
<c:if test="${fn:length(gbentries)>0}">
<table border="1">
  <tr><th>ID</th><th>Name</th><th>Message</th><th>Operations</th></tr>
<c:forEach items="${gbentries}" var="entry" varStatus="status">
  <tr>
    <td>${entry.id}</td>
    <td>${entry.name}</td>
    <td><c:out value="${entry.message}" escapeXml="true" /></td>
    <td><a href="EditComment?id=${entry.id}">Edit</a></td>
  </tr>
</c:forEach>
</table>
</c:if>

<p><a href="AddComment">Add Comment</a></p>
</body>
</html>
