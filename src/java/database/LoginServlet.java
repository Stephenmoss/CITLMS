/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DBConn;
import src.People;

/**
 *
 * @author khuckins
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	// Set up the connection.
	//make sure to update this
    	DBConn connection = new DBConn("jdbc:jtds:sqlserver://PC20167:1433;instance=MSSQLSERVER",
				"sa", "Pentium4!", "DB_MossLMS");
    	
    	connection.SetUpConnection();
    	
    	People person = null;
    	
    	if (connection.IsConnected())
    	{
	    	// For now, constructs the database if it doesn't already exist.
	    	DBHelper.createDatabase(connection.getDBName(), connection);
	    	
	    	// Check with the database to see if we have a matching account.
	        person = DBHelper.getPersonFromDatabaseWithAccount(request.getParameter("userID"), request.getParameter("password"),
	        															connection);
	        
	        connection.Close();
    	}
    	
    	// If we got a person back, we've successfully logged in.
        if (person != null)
        {
            HttpSession session = request.getSession(true); 
            session.setAttribute("FirstName", person.getFirstname());
            session.setAttribute("Role", person.getRole());
            session.setAttribute("LastName", person.getLastname());
            session.setAttribute("CurrentUser", person);
            session.setMaxInactiveInterval(30*60);
            System.out.println(person.getFullName() + " logged in.");
            // redirect to success page
            response.sendRedirect("Dash/index.jsp"); 
        }
        // Otherwise, go to the login failure page.
        else
        {
            request.setAttribute("message", "<div style=" + "margin-top:2%;" + "><header role=" + "banner" + ">\n" +
"          <div class=" + "danger" + ">\n" +
"            <strong>Error</strong> - Your credentials were deemed invalid by the server.\n" +
"          </div></header></div>"); // Will be available as ${message}
        request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
