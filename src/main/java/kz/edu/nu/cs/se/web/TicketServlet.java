package kz.edu.nu.cs.se.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Supposed to be smth like /profile/tickets but waiting for profile
@WebServlet(urlPatterns = {"/tickets"})
public class TicketServlet extends HttpServlet {
    private static final long serialVersionUID = 1;

    public TicketServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null) {
            request.getRequestDispatcher("ticketFront/ticketsTable.jsp").forward(request, response);
        } else {
            request.setAttribute("id", id);
            request.getRequestDispatcher("views/oneTicket.jsp").forward(request, response);
        }
    }
}
