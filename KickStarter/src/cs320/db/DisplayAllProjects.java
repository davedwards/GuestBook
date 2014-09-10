package cs320.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs320.model.Pledge;
import cs320.model.Project;
import cs320.model.Reward;
import cs320.model.User;

@WebServlet(urlPatterns = "/db/DisplayAllProjects", loadOnStartup = 4)
public class DisplayAllProjects extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public DisplayAllProjects()
	{
		super();
	}

	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e)
		{
			throw new ServletException(e);
		}
	}

	// @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{


		List<User> users = new ArrayList<User>();

		try
		{
			String url = "jdbc:mysql://localhost/cs320stu08";
			String user = "dave";
			String passwd = "jester32";

			Connection c = DriverManager.getConnection(url, user, passwd);
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from users");

			while (rs.next())
			{
				Integer id = rs.getInt(1);
				String username = rs.getString("username");
				String password = rs.getString("password");
				users.add(new User(id, username, password));
			}

			c.close();
		} catch (SQLException e)
		{
			throw new ServletException(e);
		}
		
		getServletContext().setAttribute("csusers", users);

		List<Project> projects = new ArrayList<Project>();

		try
		{
			String url = "jdbc:mysql://localhost/cs320stu08";
			String user = "cs320stu08";
			String passwd = "bB.K0eGC";

			Connection c = DriverManager.getConnection(url, user, passwd);
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery("select * from projects");

			while (rs.next())
			{
				Integer id = rs.getInt(1);
				String title = rs.getString("title");
				String description = rs.getString("description");
				String creator = rs.getString("creator");
				Integer fundTarget = rs.getInt("funding_target");
				Date startDate = rs.getDate("start_date");
				Integer duration = rs.getInt("funding_duration");
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				String startDateString = df.format(startDate);
				projects.add(new Project(id, creator, title, description,
						fundTarget, startDateString, duration));
			}

			c.close();
		} catch (SQLException e)
		{
			throw new ServletException(e);
		}
		Collections.sort(projects, Project.EndDateComparator);
		getServletContext().setAttribute("csprojects", projects);

		List<Reward> rewards = new ArrayList<Reward>();

		try
		{
			String url = "jdbc:mysql://localhost/cs320stu08";
			String user = "cs320stu08";
			String passwd = "bB.K0eGC";

			Connection c = DriverManager.getConnection(url, user, passwd);
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery("select * from rewards");

			while (rs.next())
			{
				Integer id = rs.getInt(1);
				Integer projectID = rs.getInt("project_id");
				Integer amount = rs.getInt("amount");
				String description = rs.getString("description");
				rewards.add(new Reward(id, projectID, amount, description));
			}

			c.close();
		} catch (SQLException e)
		{
			throw new ServletException(e);
		}
		getServletContext().setAttribute("csrewards", rewards);

		List<Pledge> pledges = new ArrayList<Pledge>();
		
		try
		{
			String url = "jdbc:mysql://localhost/cs320stu08";
			String user = "cs320stu08";
			String passwd = "bB.K0eGC";

			Connection c = DriverManager.getConnection(url, user, passwd);
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery("select * from pledges");

			while (rs.next())
			{
				// Integer id = rs.getInt(1);
				Integer projectID = rs.getInt("project_id");
				String sponsorID = rs.getString("sponsor_id");
				Integer amount = rs.getInt("amount");
				Integer rewardID = rs.getInt("reward_id");
				Pledge pledge = new Pledge(projectID, sponsorID, amount, rewardID);
				for (Project project : projects)
				{
					if(project.getId().equals(projectID))
					{
						project.getPledges().add(pledge);
						project.setTotalPledgeAmount();
					}
				}
				pledges.add(  new Pledge(projectID, sponsorID, amount, rewardID) );
				
			}
			
			c.close();
		} catch (SQLException e)
		{
			throw new ServletException(e);
		}
		getServletContext().setAttribute("cspledges", pledges);	
	
	request.getRequestDispatcher("/WEB-INF/db/DisplayAllProjects.jsp")
			.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
	
}
