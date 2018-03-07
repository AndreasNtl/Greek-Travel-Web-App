/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.room;

import controllers.message.ContactSellerController;
import controllers.rooms.BookingController;
import controllers.roomChange.ViewOwnerRoomEdit;
import controllers.rooms.ViewMorePhotoController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "room_details_options", urlPatterns = {"/user/room_details/options"})
public class RoomDetailsOptions extends HttpServlet {

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
        ServletContext servletContext = getServletContext();

        try (PrintWriter out = response.getWriter()) {

            Map<String, String[]> parameterMap = request.getParameterMap();

            String view = request.getParameter("edit");
            String book = request.getParameter("book");
            String contact = request.getParameter("contact");
            String morePhoto = request.getParameter("morePhoto");

            if ("edit".equals(view)) {
                ViewOwnerRoomEdit controller = new ViewOwnerRoomEdit();
                controller.execute(servletContext, parameterMap, request, response);
            } else if ("book".equals(book)) {
                BookingController controller = new BookingController();
                controller.execute(servletContext, parameterMap, request, response);
            } else if ("contact".equals(contact)) {
                ContactSellerController controller = new ContactSellerController();
                controller.execute(servletContext, parameterMap, request, response);
            } else if ("morePhoto".equals(morePhoto)) {
                ViewMorePhotoController controller = new ViewMorePhotoController();
                controller.execute(servletContext, parameterMap, request, response);
            }
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
