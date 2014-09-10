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

@WebServlet("/db/NewUser")
public class NewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
   public NewUser() {
        super();    
    }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   	   	
	   request.getRequestDispatcher( "/WEB-INF/db/NewUser.jsp" ).forward(
            request, response );
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
