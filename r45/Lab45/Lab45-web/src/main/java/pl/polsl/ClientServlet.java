/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version 1.0
 * @author Adam Gajewski
 */
public class ClientServlet extends HttpServlet {
    @EJB
    private ClientBeanLocal clientBean;
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
            Client client;
            String name = request.getParameter("Client");
            String type = request.getParameter("ClientType");
            String delete = request.getParameter("delete");
            if(!"".equals(name) && name !=null){
                client = new Client(name);
                if("Normal".equals(type)){
                    client.setClientType(ClientType.NORMAL);
                }
                clientBean.createOrUpdateClient(client);
                CookieAccesser.add("" + new Date() + " CreateClient", request, response);
            }
            
            if(!"".equals(delete) && delete !=null){
                clientBean.removeClient(Integer.valueOf(delete));
                CookieAccesser.add("" + new Date() + " DeleteClient", request, response);
            }
            
            List<Client> list = clientBean.listClients();
            out.println("<head>");
            out.println("<link href=\"css/style.css\" rel=\"stylesheet\">");
            out.println("</head>");
            out.println("<table>"
                   + "<tr>"
                   + "<th>Client name</th>"
                   + "<th>Client id</th>"
                   + "<th>Add purchase</th>"
                   + "<th>Delete client</th>"
                   + "</tr>");
            for(Client c: list){
                out.println("<tr>"
                        + "<form action=\"PurchaseServlet\">"
                        + "<th>" + c.getName() + "</th>"
                        + "<th name=\"id\">" + c.getId() + "</th>"
                        //+ "<th><input type=\"submit\" value=\"Add\" /></th>"
                        + "<th>"
                            + "<a href=\"/Lab23-war/PurchaseServlet?id=" + c.getId() + "\">"
                                + "ADD</a>"
                        + "</th>"
                        + "<th>"
                            + "<a href=\"/Lab23-war/ClientServlet?delete=" + c.getId() + "\">"
                                + "Delete</a>"
                        + "</th>"
                        + "</form>"
                        + "</tr>");
            }
            out.println("</table>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
