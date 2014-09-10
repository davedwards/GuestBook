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

<%-- if the guest book is not empty, display the entries in a table. --%>

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

<%-- Integer pledged = (Integer)request.getSession().getAttribute("pledged"); --%>
<c:if test="${sessionScope.pledged != csproject.id}">    <%--     if  (pledged != projectID) --%>
	<p><a href='AddPledge?projectID=${csproject.id}'>Sponsor This Project</a></p>
</c:if>
       
    <b>${csproject.title}</b><br />
    by ${csproject.creator}<br />
    ${csproject.description}<br />
    <b>Funding Target:</b> $${csproject.fundTarget}<br />
	<b>Start Date: ${csproject.startDateString}</b><br />
	<b>Funding Duration: ${csproject.duration}</b><br />
	<b>Days to Go: ${csproject.daysToGo}</b><br />
    <%-- Pledge: $${rymewards.rid }</br> --%>
    
    
    <c:forEach items="${csrewards}" var="entry" varStatus="status">
    <%-- ${csrewards.rid} --%>
	<c:if test="${entry.pid == csproject.id }">
		<%--     ${csrewards.id.amount}</br> --%>
		<%--	${entry.pid}</br> --%>
		<%--	rid ${entry.rid}</br> --%> 
		<p><b>Pledge $${entry.amount} or more</b></p>
		${entry.description}<br />
	</c:if>
	</c:forEach>
    
    <p><a href="DisplayAllProjects">All Projects</a></p>
    
    <%-- <c:if test="${fn:length(csresults)== 0}">
<p>Found no car that meets your search criteria.</p>

    
    <%-- for (int rewardID = 0; rewardID < rewards.size(); rewardID++) {
			if (rewards.get(rewardID).getRID() == projectID) {
				out.println( "<p><b>Pledge");
				out.println( "$" + rewards.get(rewardID).getRewardAmount() + " or more</b></p>");
				out.println( "<p>" + rewards.get(rewardID).getRewardDescription() + "</b></p>");
			}	
		} --%>
    
<%-- <p><a href="CreateProject">Create Project</a></p>  --%>
</body>
</html>
<%--
		// get the project to be displayed
/*        Integer projectID = Integer.valueOf( request.getParameter( "projectID" ) );
        Project project = getID(projectID);
        
		List<Project> projects = (ArrayList<Project>) getServletContext().getAttribute( "projects");
		List<Reward> rewards = (ArrayList<Reward>) getServletContext().getAttribute( "rewards");
		List<Pledge> pledges = (List<Pledge>)getServletContext().getAttribute("pledges" );
		
		response.setContentType( "text/html");
		PrintWriter out = response.getWriter();

		out.println( "<!DOCTYPE html>");
		out.println( "<head>");
		out.println( "<title>Project Details</title>");
		out.println( "</head>");
		out.println( "<body>");
		out.println( "<p><span>");
		// check if a user has logged in or not
        if( request.getSession().getAttribute( "user" ) == null )
        {
        	out.println( "<a href='Login'>Login</a>" );

        }
        else out.println( "<a href='Logout'>Logout</a>" );
               
        Integer pledged = (Integer)request.getSession().getAttribute("pledged");
        if  (pledged != projectID)
    	out.println( "<p><span><a href='AddPledge?projectID=" + project.getID() + "''>Sponsor This Project</a></span></p>" );
        
    	out.println( "<h3>" + project.getTitle() + "</h3" );
		out.println( "<p>by " + project.getUsername() + "<br />" );
		out.println( "<p>" + project.getDescription() + "<br />" );
		out.println( "<p><b>Funding Target: $ </b>" + project.getFundTarget() + "<br />" );
		out.println( "<p><b>Start Date:</b> "	+ project.getStartDateString() + "<br />" );
		out.println( "<b>Funding Duration:</b> " + project.getDuration() + "<br />");
		out.println( "<b>Days to Go</span>:</b> " + project.getDaysToGo() + "<br></p>");		

		for (int rewardID = 0; rewardID < rewards.size(); rewardID++) {
			if (rewards.get(rewardID).getRID() == projectID) {
				out.println( "<p><b>Pledge");
				out.println( "$" + rewards.get(rewardID).getRewardAmount() + " or more</b></p>");
				out.println( "<p>" + rewards.get(rewardID).getRewardDescription() + "</b></p>");
			}	
		}
		out.println( "</td></tr><tr>" );
			out.println( "<td><a href=\"DisplayAllProjects\">All Projects</a></td>" );
			out.println( "</tr>" );
			out.println( "</table>" );
			out.println( "</body>" );
			out.println( "</html>" );*/  --%>