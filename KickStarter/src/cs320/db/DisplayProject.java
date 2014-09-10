package cs320.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs320.model.Project;
import cs320.model.Reward;
import cs320.model.Pledge;

@WebServlet( "/db/DisplayProject")
@SuppressWarnings( "unchecked")
public class DisplayProject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DisplayProject() {
		super();
	}
	
	private Project getEntry( Integer id )
    {
        List<Project> projects = (List<Project>) 
        		getServletContext().getAttribute( "csprojects" );
        
        for( Project project : projects )
            if( project.getId().equals( id ) ) return project;
        return null;
    }

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		 // get the entry to be edited
        Integer id = Integer.valueOf( request.getParameter( "id" ) );
        Project project = getEntry( id );

        // pass the entry to jsp in request scope
        request.setAttribute( "csproject", project );
        getServletContext().setAttribute( "csrewards", getEntriesFromDB() );
        request.getRequestDispatcher( "/WEB-INF/db/DisplayProject.jsp" ).forward(
            request, response );
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	List<Reward> getEntriesFromDB() throws ServletException
    {
        List<Reward> rewards = new ArrayList<Reward>();

        try
        {
            String url = "jdbc:mysql://localhost/cs320stu08";
            String username = "cs320stu08";
            String password = "bB.K0eGC";

            Connection c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "select * from rewards" );

            while( rs.next() )
            {
                Integer id = rs.getInt( 1 );
                Integer projectID = rs.getInt( "project_id" );
                Integer amount = rs.getInt( "amount" );
                String description = rs.getString( "description" );
                
                rewards.add( new Reward( id, projectID, amount, description ) );
            }

            c.close();
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }

        return rewards;
    }
}