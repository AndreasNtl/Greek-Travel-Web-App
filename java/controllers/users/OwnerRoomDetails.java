package controllers.users;

import dao.BookingDAO;
import dao.BookingDAOImpl;
import dao.MessageDAO;
import dao.MessageDAOImpl;
import dao.RoomDAO;
import dao.RoomDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import entities.Message;
import entities.Room;
import entities.User;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

public class OwnerRoomDetails extends controllers.Controller {

    private final String target_success = "/WEB-INF/account/owner/room_details.jsp";
    private final String target_error = "/WEB-INF/error/endoftheworld.jsp";

    private String id = null;
    private RoomDAO rd = new RoomDAOImpl();
    private UserDAO ud = new UserDAOImpl();
    private MessageDAO md = new MessageDAOImpl();
    private BookingDAO bd = new BookingDAOImpl();

    private Integer avgOfTheRoom;
    private Integer reviewsOfTheRoom;
    private User ownerOfTheRoom = null;
    private List<Integer> ownerOfTheRoomAvg = null;

    private User sessionuser;

    List<Room> list = null;
    List<Message> messages = null;
    List<Room> listBookedRooms = null;

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

            id = map.get("Room_id")[0];
            int roomId = Integer.decode(id);
            HttpSession session = request.getSession();
            sessionuser = (User) session.getAttribute("sessionuser");
            
            ownerOfTheRoomAvg = bd.findAvgRatingOfOwner(sessionuser.getId());
            ownerOfTheRoom = ud.findUserByRoomId(roomId);
                        
            list = rd.findOwnerRooms(roomId);
            if (!list.isEmpty() && list != null) {
                request.setAttribute("list", list);
                request.setAttribute("available", true);

            } else {
                list = rd.findBookedOwnerRooms(roomId);
                request.setAttribute("list", list);
                request.setAttribute("booked", true);

            }

            messages = md.findMessageByroomid(roomId, sessionuser.getId());

            avgOfTheRoom = bd.findAvgRating(roomId);

            reviewsOfTheRoom = bd.findReviews(roomId);

            return true;
        } catch (Exception e) {

            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    protected void on_success() {
        request.setAttribute("roomsOwner", true);
        request.setAttribute("reviewsOfTheRoom", reviewsOfTheRoom);
        request.setAttribute("avgOfTheRoom", avgOfTheRoom);
        request.setAttribute("ownerOfTheRoomAvg", ownerOfTheRoomAvg);
        request.setAttribute("User", ownerOfTheRoom);
        request.setAttribute("messages", messages);

        redirect(target_success);
    }

    @Override
    protected void on_error() {
        redirect(target_error);
    }

}
