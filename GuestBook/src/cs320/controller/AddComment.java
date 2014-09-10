package cs320.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs320.model.GuestBookEntry;

@WebServlet("/AddComment")
public class AddComment extends HttpServlet {

    private static final long serialVersionUID = 1L;

    int idSeed = 100;

    public AddComment()
    {
        super();
    }

    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        request.getRequestDispatcher( "/WEB-INF/AddComment.jsp" ).forward(
            request, response );
    }

    @SuppressWarnings("unchecked")
    protected void doPost( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        // get the user input
        String name = request.getParameter( "name" );
        String message = request.getParameter( "message" );

        // create a new guest book entry
        GuestBookEntry entry = new GuestBookEntry( idSeed++, name, message );

        // add the new entry to the guest book
        List<GuestBookEntry> entries = (List<GuestBookEntry>) getServletContext().getAttribute(
            "gbentries" );
        entries.add( entry );

        // send the user back to the guest book page
        response.sendRedirect( "GuestBook" );
    }

}
