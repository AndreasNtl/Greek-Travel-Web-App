package controllers.message;

import dao.UserDAOImpl;
import entities.Role;
import entities.User;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import validation.TextValidator;
import validation.UserNameValidator;

public class ViewSendMessage extends controllers.Controller {

    private UserDAOImpl udao = new UserDAOImpl();
    private User sessionuser = null;
            
    private final String target_success = "/WEB-INF/report/message_send.jsp";
    private final String targe_error = "/WEB-INF/account/message/send_message.jsp";

    private User recipientUser = null;
    private String recipient = null;
    private String title = null;
    private String message = null;

    @Override
    protected boolean validate_input(Map<String, String[]> map) {

        UserNameValidator userNameValidator = new UserNameValidator(errorMap, correctMap);
        if (userNameValidator.validate(map, "recipient")) {
            System.out.println("Correct validesion of recipient");
        }

        TextValidator titleValidetor = new TextValidator(errorMap, correctMap);
        if (titleValidetor.validate(map, "title")) {
            System.out.println("Correct validesion of title");
        }

        TextValidator messageValidetor = new TextValidator(errorMap, correctMap);
        if (messageValidetor.validate(map, "message")) {
            System.out.println("Correct validesion of message");
        }
        return errorMap.isEmpty();
    }

    //TO do: elenxos an eiparxei stin basi o recipient 
    @Override
    protected boolean validate_logic() {
        recipientUser = udao.findByNickName(map.get("recipient")[0]);
        
        
        if( recipientUser == null ){
            errorMap.addError("recipient" , "The recipient is invalid");
        } else {
            recipient = recipientUser.getNickname();
        }
        
        return errorMap.isEmpty();
    }

    @Override
    protected boolean try_execute() {
        
        String roomId = map.get("roomId")[0];
        int room_id = Integer.parseInt(roomId);
                
        HttpSession session = request.getSession();
        sessionuser = (User) session.getAttribute("sessionuser");        
        
        boolean sms = udao.addMessage(sessionuser, recipientUser, correctMap.get("title"), correctMap.get("message"), room_id ) ;
        
        return sms;
    }

    @Override
    protected void on_success() {
        request.setAttribute("recipient", correctMap.get(recipient));
        redirect(target_success);
    }

    @Override
    protected void on_error() {
        request.setAttribute("errorMap", errorMap);
        redirect(targe_error);
    }

}