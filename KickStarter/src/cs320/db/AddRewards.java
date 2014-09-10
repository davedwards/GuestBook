package cs320.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs320.model.Reward;

@WebServlet("/db/AddRewards")
public class AddRewards extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
   public AddRewards() {
        super();    
    }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      
	   request.getRequestDispatcher( "/WEB-INF/db/AddRewards.jsp" ).forward(
           request, response );
   }
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if (request.getParameter("submit").equals("Finish"))
        {
			response.sendRedirect("DisplayAllProjects" );
            return;
        }
		
		Integer amount = Integer.parseInt(request.getParameter( "pledgeAmount" ) );
		String description = request.getParameter( "rewardDescription" );

		List<Reward> rewards = (List<Reward>)getServletContext().getAttribute("csrewards" );
		
		Integer projectID = (Integer) getServletContext().getAttribute("projectID");
		System.out.println(projectID);
		int rewardID = rewards.size();
		request.getSession().setAttribute("rewardID", rewardID);
		Reward reward = new Reward(rewardID, projectID, amount, description );
//		System.out.println(rewardID + " " + projectID  + " " + amount + " " + description);
		rewards.add(reward);
		
		try
	     {
	         String url = "jdbc:mysql://localhost/cs320stu08";
	         String user = "cs320stu08";
	         String passwd = "bB.K0eGC";

	         Connection c = DriverManager.getConnection( url, user, passwd );
	         String sql = "insert into rewards (project_id, amount, description)  " +
	         		"values (?, ?, ?)";
	         PreparedStatement pstmt = c.prepareStatement( sql );
	         pstmt.setInt( 1, projectID );
	         pstmt.setInt(2,  amount);
	         pstmt.setString( 3, description );
	         pstmt.executeUpdate();

	         c.close();
	     }
	     catch( SQLException e )
	     {
	         throw new ServletException( e );
	     } 
		
		String button = request.getParameter("submit" );
		if(button.equals("Add"))
		{
			response.sendRedirect("AddRewards" );
		}
	}
}
