package cs320.db;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/db/Login")
public class Login extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        request.getRequestDispatcher( "/WEB-INF/db/Login.jsp" ).forward(
            request, response );
    }

    protected void doPost( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
    	if( request.getParameter( "username" ).equals( "cysun" )
            && request.getParameter( "password" ).equals( "abcd" ) )
        {
            request.getSession().setAttribute( "user", "cysun" );
            response.sendRedirect( "DisplayAllProjects" );
        }
        
        else if( request.getParameter( "username" ).equals( "cs320stu31" )
                && request.getParameter( "password" ).equals( "abcd" ) )
            {
                request.getSession().setAttribute( "user", "cs320stu31" );
                response.sendRedirect( "DisplayAllProjects" );
            }
        
            else
                response.sendRedirect( "Login" );
    }

}
