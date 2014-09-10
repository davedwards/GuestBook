package cs320.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs320.model.GuestBookEntry;

@WebServlet("/AddCommentWithCookie")
public class AddCommentWithCookie extends HttpServlet {

    private static final long serialVersionUID = 1L;

    int idSeed = 100;

    public AddCommentWithCookie()
    {
        super();
    }

    private String getName( HttpServletRequest request )
    {
        Cookie[] cookies = request.getCookies();
        for( Cookie cookie : cookies )
            if( cookie.getName().equals( "name" ) ) return cookie.getValue();

        return null;
    }

    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        response.setContentType( "text/html" );
        PrintWriter out = response.getWriter();
        out.println( "<html><head><title>Add Comment (with Cookie)</title></head><body>" );

        out.println( "<form action='AddCommentWithCookie' method='post'>" );

        // check if we have a cookie with the user's name. If so, display the
        // user's name; otherwise display an input field.
        String name = getName( request );
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
        // the user's name should either be in a cookie or a request parameter
        String name = getName( request );
        if( name == null )
        {
            name = request.getParameter( "name" );
            // create a cookie to store the user's name
            Cookie cookie = new Cookie( "name", name );
            response.addCookie( cookie );
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
