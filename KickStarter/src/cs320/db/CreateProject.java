package cs320.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs320.model.Project;
import cs320.model.User;

@WebServlet("/db/CreateProject")
public class CreateProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
   public CreateProject() {
        super();    
    }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   	   	
	// check if a user has logged in or not
       if( request.getSession().getAttribute( "user" ) == null )
       {
           response.sendRedirect( "Login" );
           return;
       }
       
    // pass the entry to jsp in request scope
       String username = (String) request.getSession().getAttribute( "user" ) ;
       request.setAttribute( "username", username);
       List<Project> projects = (ArrayList<Project>) getServletContext().getAttribute( "csprojects" );
//       request.getSession().setAttribute( "projectID", projects.size() );
       getServletContext().setAttribute( "projectID", projects.size() );
      
	   request.getRequestDispatcher( "/WEB-INF/db/CreateProject.jsp" ).forward(
            request, response );

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String creator = (String) request.getSession().getAttribute( "user" );
        String title = request.getParameter( "title" );
	    String description = request.getParameter( "projectDescription" );
	    Integer fundTarget = Integer.parseInt( request.getParameter( "fundTarget" ) );
	    String startDateString = request.getParameter( "startDate" );
	    Integer duration = Integer.parseInt( request.getParameter( "duration" ) );
	    List<Project> projects = (List<Project>)getServletContext().getAttribute("csprojects");
	    Integer projectID = (Integer) getServletContext().getAttribute( "projectID" );
	    ++projectID;
	    getServletContext().setAttribute( "projectID", projectID );
	    System.out.println(projectID);
//	    request.setAttribute( "projectID", projectID );
//	    request.getSession().setAttribute("projectID", projects.size());
   	    Project project = new Project( projectID, creator, title, description,
   			fundTarget, startDateString, duration );
		
   	    projects.add( project );
   	    
   	 try
     {
         String url = "jdbc:mysql://localhost/cs320stu08";
         String user = "cs320stu08";
         String passwd = "bB.K0eGC";

         Connection c = DriverManager.getConnection( url, user, passwd );
         String sql = "insert into projects (title, description, creator, " +
         		"funding_target, start_date, funding_duration)  " +
         		"values (?, ?, ?, ?, ?, ?)";
         PreparedStatement pstmt = c.prepareStatement( sql );
         pstmt.setString( 1, title );
         pstmt.setString( 2, description );
         pstmt.setString( 3, creator );
         pstmt.setInt( 4, fundTarget );
         DateFormat dF = DateFormat.getDateInstance(DateFormat.SHORT);
         Date ud = dF.parse(startDateString); 
         java.sql.Date startDate = new java.sql.Date(ud.getTime());  
//         System.out.println(startDate);
         pstmt.setDate( 5, startDate );
         pstmt.setInt(6,  duration);
         pstmt.executeUpdate();

         c.close();
     }
     catch( SQLException e )
     {
         throw new ServletException( e );
     } catch (ParseException e) 
     {
		e.printStackTrace();
	}
   	    response.sendRedirect("AddRewards");
	}

}
