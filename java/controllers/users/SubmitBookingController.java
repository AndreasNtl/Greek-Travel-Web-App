package controllers.users;

import dao.BookingDAOImpl;
import dao.RoomDAOImpl;
import dao.UserDAOImpl;
import entities.User;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpSession;
import validation.DateValidator;


public class SubmitBookingController extends controllers.Controller {

    private UserDAOImpl dao = new UserDAOImpl();
    private RoomDAOImpl rdao = new RoomDAOImpl();
    private BookingDAOImpl bdao = new BookingDAOImpl();
 
    private final String target_succses = "/WEB-INF/fragments/Booking.jsp";
    private final String target_error = "/WEB-INF/fragments/Booking.jsp";

    private Date From = null;
    private Date To = null;
    private User sessionuser = null;
    private String ownerId = null;
    private String roomId = null;
    private int ownerid = 0;
    private int roomid = 0;

    @Override
    protected boolean validate_input(Map<String, String[]> map) {

        DateValidator dv = new DateValidator(errorMap, correctMap);

        dv.validate(map, "dateFrom");
        dv.validate(map, "dateTo");
        
        return errorMap.isEmpty();
    }

    @Override
    protected boolean validate_logic() {
  
        try {
            roomId = map.get("roomId")[0];
            
            DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            From = (Date) formatter.parse(correctMap.get("dateFrom"));
            To = (Date) formatter.parse(correctMap.get("dateTo"));
            
            roomid = Integer.parseInt(roomId);
            boolean checkAvailability = rdao.checkRoomsAvailability(roomid, From, To);
            if( checkAvailability == false){
                errorMap.addError("availability", "Not Available");
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        
        
        return errorMap.isEmpty();
    }

    @Override
    protected boolean try_execute() {
        try {
            HttpSession session = request.getSession();
            sessionuser = (User) session.getAttribute("sessionuser");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        // ------------------------------------------------------
        try {
      
            bdao.BookRoom(From, To, sessionuser.getId() ,roomid);
       
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    protected void on_success() {
        request.setAttribute("success", true);
        redirect(target_succses);
    }

    @Override
    protected void on_error() {
        redirect(target_error);
    }

}
