package cs320.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cs320.model.GuestBookEntry;

@WebServlet("/AddCommentWithSession")
public class AddCommentWithSession extends HttpServlet {

    private static final long serialVersionUID = 1L;

    int idSeed = 100;

    public AddCommentWithSession()
    {
        super();
    }

    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        response.setContentType( "text/html" );
        PrintWriter out = response.getWriter();
        out.println( "<html><head><title>Add Comment (with Session)</title></head><body>" );

        out.println( "<form action='AddCommentWithSession' method='post'>" );

        // check if the user's name is stored in session. If so, display the
        // user's name; otherwise display an input field.
        String name = (String) request.getSession().getAttribute( "name" );
        if( name != null )
            out.println( "Name: " + name + "<br />" );
        else
            out.println( "Name: <input type='text' name='name' /> <br />" );

        out.println( "<textarea name='message'></textarea> <br />" );
        out.println( "<input type='submit' name='add' value='Add' /> <br />" );
        out.println( "</form>" );

        out.println( "</body></html>" );
    }

    @SuppressWarnings("unchecked")
    protected void doPost( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        // the user's name should either be in session or a request parameter
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute( "name" );
        if( name == null )
        {
            name = request.getParameter( "name" );
            // save the name in session
            session.setAttribute( "name", name );
        }

        String message = request.getParameter( "message" );

        // create a new guest book entry
        GuestBookEntry entry = new GuestBookEntry( idSeed++, name, message );

        // add the new entry to the guest book
        List<GuestBookEntry> entries = (List<GuestBookEntry>) getServletContext().getAttribute(
            "entries" );
        entries.add( entry );

        // send the user back to the guest book page
        response.sendRedirect( "GuestBook" );
    }

}
