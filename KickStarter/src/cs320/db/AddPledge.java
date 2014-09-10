package cs320.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs320.model.Project;
import cs320.model.Reward;
import cs320.model.Pledge;

@WebServlet("/db/AddPledge" )
public class AddPledge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	int idSeed = 100;
	
   public AddPledge() {
        super();    
    }

   @SuppressWarnings("unchecked")
private Project getProjectID( Integer projectID )
   {
       List<Project> projects = (List<Project>) getServletContext().getAttribute("csprojects" );

       for( Project project : projects )
           if( project.getId().equals( projectID ) ) 
        	   return project;

       return null;
   }
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   	   	
	// check if a user has logged in or not
       if( request.getSession().getAttribute( "user" ) == null )
       {
           response.sendRedirect( "Login" );
           return;
       }
       
//       int projectID = (Integer) request.getSession().getAttribute("projectID");
       // get the project to be displayed
       Integer projectID = Integer.valueOf( request.getParameter( "projectID" ) );
       Project project = getProjectID(projectID);
       
		List<Project> projects = (ArrayList<Project>) getServletContext().getAttribute( "csprojects" );
		List<Reward> rewards = (ArrayList<Reward>) getServletContext().getAttribute( "csrewards" );
 
	   
	   request.getRequestDispatcher( "/WEB-INF/db/AddPledge.jsp" ).forward(
	           request, response );
}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer pledgeAmount = Integer.parseInt(request.getParameter( "pledgeAmount" ) );
		Integer projectID = Integer.parseInt( request.getParameter( "projectID" ) );
		String sponsorID = request.getParameter( "username" );
		Integer rewardID = 5;//Integer.parseInt( request.getParameter( "rewardID" ) );
//		Integer rewardID = (Integer) getServletContext().getAttribute("rewardID");
		Integer rewardAmount = Integer.parseInt(request.getParameter( "rewardAmount" ) );
		List<Pledge> pledges = (List<Pledge>)getServletContext().getAttribute("cspledges" );
		Pledge pledge = new Pledge( projectID, sponsorID, pledgeAmount , rewardID );
		
		request.getSession().setAttribute( "pledged", projectID );
		pledges.add(pledge);
		
		try
	     {
	         String url = "jdbc:mysql://localhost/cs320stu08";
	         String user = "cs320stu08";
	         String passwd = "bB.K0eGC";

	         Connection c = DriverManager.getConnection( url, user, passwd );
	         String sql = "insert into pledges (project_id, sponsor_id, amount, reward_id)  " +
	         		"values (?, ?, ?, ?)";
	         PreparedStatement pstmt = c.prepareStatement( sql );
	         pstmt.setInt( 1, projectID );
	         pstmt.setString(2,  sponsorID);
	         pstmt.setInt( 3, pledgeAmount );
	         pstmt.setInt( 4, rewardID );
	         pstmt.executeUpdate();

	         c.close();
	     }
	     catch( SQLException e )
	     {
	         throw new ServletException( e );
	     } 
		
		List<Project> projects = (List<Project>)getServletContext().getAttribute("csprojects" );
		for(Project project : projects)	
		{
			if(project.getId().equals(projectID))
			{
				project.getPledges().add(pledge);
				project.setTotalPledgeAmount();
			}
		}
		
		
		
		response.sendRedirect("DisplayAllProjects" );	
	}
}
