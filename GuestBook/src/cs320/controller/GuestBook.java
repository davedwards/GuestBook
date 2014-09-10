package cs320.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs320.model.GuestBookEntry;


@WebServlet("/GuestBook")
public class GuestBook extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public GuestBook()
    {
        super();
    }

    public void init( ServletConfig config ) throws ServletException
    {
        super.init( config );

        // create some test data for display
        List<GuestBookEntry> entries = new ArrayList<GuestBookEntry>();
        entries.add( new GuestBookEntry( 1, "john", "hello" ) );
        entries.add( new GuestBookEntry( 2, "joe", "hi" ) );

        // stored the data somewhere that can be accessed by this servlet and other servlets.
        // In Servlets, scope is ServletContext
        getServletContext().setAttribute( "gbentries", entries );
    }

    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
    	// forward data to view to display "path_to_jsp"
        request.getRequestDispatcher( "/WEB-INF/GuestBook.jsp" ).forward(
            request, response );
    }

    protected void doPost( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        doGet( request, response );
    }	
}
