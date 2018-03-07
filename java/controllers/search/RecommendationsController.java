
package controllers.search;

import dao.BookingDAO;
import dao.BookingDAOImpl;
import dao.RoomDAOImpl;
import entities.Room;

import entities.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;


public class RecommendationsController extends controllers.Controller {

    private final String target = "/WEB-INF/index.jsp";
    private final String target_error = "/WEB-INF/index.jsp";

    private RoomDAOImpl dao = new RoomDAOImpl();
    private BookingDAO bd = new BookingDAOImpl();
    private Map< Room, Integer[]> SearchListFeatures = new HashMap<>();

    List<Room> list = null;

    @Override
    protected boolean validate_input(Map<String, String[]> map) {
        return true;
    }

    @Override
    protected boolean validate_logic() {
        return true;
    }

    @Override
    protected boolean try_execute() {
    
        try {
       
            HttpSession session = request.getSession();

            User sessionuser = null;
            if (session != null) {
                sessionuser = (User) session.getAttribute("sessionuser");
            }

            if (sessionuser == null) {
                System.out.println("mpeni sti periptosi random");
                list = dao.getRandomRooms();
            } else {
                   System.out.println("mpeni stis periptosis pou to session den einia 0");
                Integer id = sessionuser.getId();

                list = dao.findRecommendationsByUserId(id);
            }

            for (Room r : list) {
                Integer[] avgAndReviews = new Integer[2];

                avgAndReviews[0] = bd.findAvgRating(r.getId());
                avgAndReviews[1] = bd.findReviews(r.getId());

                SearchListFeatures.put(r, avgAndReviews);
            }

            return true;
        } catch (Exception e) {
            errorMap.addError("exception", e.getMessage());
            return false;
        }
    }

    @Override
    protected void on_success() {
        request.setAttribute("SearchListFeatures", SearchListFeatures);
        redirect(target);
    }

    @Override
    protected void on_error() {
        redirect(target_error);
    }
}
