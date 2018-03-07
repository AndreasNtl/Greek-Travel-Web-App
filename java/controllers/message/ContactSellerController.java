package controllers.message;

import dao.UserDAOImpl;
import entities.Message;
import entities.Room;
import entities.User;
import java.util.Date;
import java.util.Map;

//to do Validate input
public class ContactSellerController extends controllers.Controller {

    private final String target_success = "/WEB-INF/account/message/send_message.jsp";
    private final String target_error = "/WEB-INF/error/endoftheworld.jsp";

    private UserDAOImpl dao = new UserDAOImpl();
    private Message viewMessage = null;

    private int roomId = 0;

    private User recipient = null;
    private Room room = null;

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

            String id1 = map.get("ownerId")[0];
            Integer id = Integer.parseInt(id1);
            recipient = dao.findById(id);

            String roomid = map.get("roomId")[0];
            roomId = Integer.parseInt(roomid);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    protected void on_success() {
        request.setAttribute("recipient", recipient.getNickname());
        request.setAttribute("roomId", roomId);
        request.setAttribute("date", new Date());

        redirect(target_success);
    }

    @Override
    protected void on_error() {
        redirect(target_error);
    }

}
