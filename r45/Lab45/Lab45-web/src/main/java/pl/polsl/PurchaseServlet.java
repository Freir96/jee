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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version 1.0
 * @author Adam Gajewski
 */
@WebServlet(name = "PurchaseServlet", urlPatterns = {"/PurchaseServlet"})
public class PurchaseServlet extends HttpServlet {

    @EJB
    private PurchaseBeanLocal purchaseBean;

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PurchaseServlet</title>");
            out.println("<link href=\"css/style.css\" rel=\"stylesheet\">");
            out.println("</head>");
            out.println("<body>");
            String id = request.getParameter("id");
            String purchase = request.getParameter("Purchase");
            String delete = request.getParameter("delete");
            if (!"".equals(delete) && delete != null) {
                purchaseBean.removePurchase(Integer.valueOf(delete));
                CookieAccesser.add("" + new Date() + " DeletePurchase", request, response);
            }
            if (!"".equals(id) && id != null && !"".equals(purchase) && purchase != null) {
                Client c = clientBean.findClient(Integer.valueOf(id));
                Purchase p = new Purchase();
                p.setClient(c);
                p.setPurchaseName(purchase);
                purchaseBean.createOrUpdatePurchase(p);
                CookieAccesser.add("" + new Date() + " CreatePurchase", request, response);
            }
            if ((!"".equals(id) && id != null) && (purchase == null || "".equals(purchase))) {
                out.println(
                        "       <div>\n"
                        + "            <div>Create client</div>\n"
                        + "            <form action=\"PurchaseServlet\">\n"
                        + "                <input type=\"text\" name=\"id\" value=\"" + id + "\" />\n"
                        + "                <input type=\"text\" name=\"Purchase\" value=\"\" />\n"
                        + "                <input type=\"submit\" value=\"Submit\" />\n"
                        + "            </form>\n"
                        + "        </div>");

            } else {
                if ((!"".equals(id) && id != null) && !"".equals(purchase) && purchase != null) {
                    Client c = clientBean.findClient(Integer.valueOf(id));
                    //Purchase p = new Purchase(c, purchase);
                    Purchase p = new Purchase();
                    p.setClient(c);
                    p.setPurchaseName(purchase);
                    purchaseBean.createOrUpdatePurchase(p);
                    CookieAccesser.add("" + new Date() + " CreatePurchase", request, response);
                }
                out.println("<table>"
                        + "<tr>"
                        + "<th>Purchase</th>"
                        + "<th>Purchase id</th>"
                        + "<th>Client</th>"
                        + "<th>Delete purchase</th>"
                        + "</tr>");
                List<Purchase> list = purchaseBean.listPurchases();
                for (Purchase c : list) {
                    out.println("<tr>"
                            + "<th>" + c.getPurchaseName() + "</th>"
                            + "<th>" + c.getId() + "</th>"
                            + "<th>" + c.getCilent().getName() + "</th>"
                            + "<th>"
                            + "<a href=\"/Lab23-war/PurchaseServlet?delete=" + c.getId() + "\">"
                                + "Delete</a>"
                            + "</th>"
                            + "</tr>");
                }
                out.println("</table>");
            }
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
