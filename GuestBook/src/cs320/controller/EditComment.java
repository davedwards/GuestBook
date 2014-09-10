package cs320.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs320.model.GuestBookEntry;

@WebServlet("/EditComment")
public class EditComment extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public EditComment()
    {
        super();
    }

    /**
     * Given an id, retrieve the GuestBookEntry.
     */
    @SuppressWarnings("unchecked")
    private GuestBookEntry getEntry( Integer id )
    {
        List<GuestBookEntry> entries = (List<GuestBookEntry>) getServletContext().getAttribute(
            "gbentries" );

        for( GuestBookEntry entry : entries )
            if( entry.getId().equals( id ) ) return entry;

        return null;
    }

    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        // get the entry to be edited
        Integer id = Integer.valueOf( request.getParameter( "id" ) );
        GuestBookEntry entry = getEntry( id );

        // pass the entry to jsp in request scope
        request.setAttribute( "entry", entry );
        request.getRequestDispatcher( "/WEB-INF/EditComment.jsp" ).forward(
            request, response );
    }

    protected void doPost( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        // get the entry to be edited
        Integer id = Integer.valueOf( request.getParameter( "id" ) );
        GuestBookEntry entry = getEntry( id );

        // change the entry based on user input
        entry.setName( request.getParameter( "name" ) );
        entry.setMessage( request.getParameter( "message" ) );

        // send the user back to the guest book page
        response.sendRedirect( "GuestBook" );
    }

}
