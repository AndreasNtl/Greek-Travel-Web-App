package controllers.message;

import dao.MessageDAO;
import dao.MessageDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import entities.Message;
import entities.User;
import java.util.Map;
import javax.servlet.http.HttpSession;

public class ReplyController extends controllers.Controller {
    
    private UserDAOImpl udao = new UserDAOImpl();

    private final String target_success = "/WEB-INF/account/message/send_message.jsp";
    private final String target_error = "/WEB-INF/error/endoftheworld.jsp";

    private MessageDAO md = new MessageDAOImpl();
    private Message viewMessage = null;
    
    private String messageId = null;
    private String recipient = null;

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

            messageId = map.get("messageId")[0];
            int id = Integer.parseInt(messageId);

            HttpSession session = request.getSession();
            User sessionuser = (User) session.getAttribute("sessionuser");
           
            viewMessage = md.findById(id);
           
            int sessionuserId = sessionuser.getId();
            int messageSenderId = viewMessage.getSenderId().getId() ;
            int messageReceiverId = viewMessage.getReceiverId().getId() ;
           
            if( sessionuserId == messageSenderId ){
               recipient = viewMessage.getReceiverId().getNickname();
            } else if( sessionuserId == messageReceiverId ) {
               recipient = viewMessage.getSenderId().getNickname();    
            }

           
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    protected void on_success() {

        request.setAttribute("recipient", recipient);
        request.setAttribute("topic", viewMessage.getTopic());
        request.setAttribute("roomId", viewMessage.getRoomId().getId());

        redirect(target_success);
    }

    @Override
    protected void on_error() {
        redirect(target_error);
    }
}

