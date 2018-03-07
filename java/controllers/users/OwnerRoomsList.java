/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.users;

import dao.BookingDAO;
import dao.BookingDAOImpl;
import dao.RoomDAO;
import dao.RoomDAOImpl;
import entities.Room;
import entities.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import validation.PositiveIntegerValidator;

public class OwnerRoomsList extends controllers.Controller {

    private final String target_error = "/WEB-INF/error/endoftheworld.jsp";
    private final String target_success = "/WEB-INF/account/owner/owner_list.jsp";
    
    private BookingDAO bd = new BookingDAOImpl();
    private Map< Room, Integer[]> SearchListFeatures = new HashMap<>();

    private List<Room> list;
    
    int currentPage;
    int recordsPerPage = 10;
    long noOfRecords;
    int numberOfPages;
    

    @Override
    protected boolean validate_input(Map<String, String[]> map) {
        if (map.get("currentPage") == null) {
            currentPage = 1;
        } else {
            PositiveIntegerValidator pv = new PositiveIntegerValidator(errorMap, correctMap);
            pv.validate(map, "currentPage");
            String stringCurrentPage = correctMap.get("currentPage");
            currentPage = Integer.parseInt(stringCurrentPage);
        }
        return true;
    }

    @Override
    protected boolean validate_logic() {
        return true;
    }

    @Override
    protected boolean try_execute() {
        RoomDAO dao = new RoomDAOImpl();

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("sessionuser");

        try {
          
            int id = currentUser.getId();
            
            noOfRecords = dao.findNumberOfOwnerRooms(id);
  
            list = dao.padignationRoomList(id, (currentPage -1) *  recordsPerPage, recordsPerPage);

            numberOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            
            for (Room r : list) {
                Integer[] avgAndReviews = new Integer[2];

                avgAndReviews[0] = bd.findAvgRating(r.getId());
                avgAndReviews[1] = bd.findReviews(r.getId());

                SearchListFeatures.put(r, avgAndReviews);
            }

            return true;
        } catch (Exception e) {
            list = null;
            return false;
        }

    }

    @Override
    protected void on_success() {
        request.setAttribute("list", SearchListFeatures);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("numberOfPages", numberOfPages);
        redirect(target_success);
    }

    @Override
    protected void on_error() {
        redirect(target_error);
    }

}
