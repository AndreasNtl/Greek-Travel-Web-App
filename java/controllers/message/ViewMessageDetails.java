package controllers.message;

import dao.MessageDAO;
import dao.MessageDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import entities.Message;
import entities.User;
import java.util.Map;

public class ViewMessageDetails extends controllers.Controller {

    private final String target_success = "/WEB-INF/account/message/view_message.jsp";
    private final String target_error = "/WEB-INF/error/endoftheworld.jsp";
    
    private MessageDAO md = new MessageDAOImpl();
    private UserDAO ud = new UserDAOImpl();
    private Message viewMessage = null;
    private User sender = null;
    private User receiver = null;
    
    private String messageId = null;

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
          messageId = map.get("id")[0];
          int id = Integer.parseInt(messageId);
          viewMessage = md.findById(id);
          sender = viewMessage.getSenderId();
          receiver = viewMessage.getReceiverId();
          
        return true;
    }

    @Override
    protected void on_success() {

       request.setAttribute("messageId", messageId);
       request.setAttribute("message", viewMessage.getText());
       request.setAttribute("title", viewMessage.getTopic());
       request.setAttribute("date", viewMessage.getSubmittedDate());
       request.setAttribute("sender", sender.getNickname());
       request.setAttribute("receiver", receiver.getNickname());
       request.setAttribute("roomId", viewMessage.getRoomId());
        
        redirect(target_success);
    }

    @Override
    protected void on_error() {
        redirect(target_error);
    }

}