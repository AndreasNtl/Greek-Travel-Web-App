package controllers.message;

import dao.MessageDAO;
import dao.MessageDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import entities.Message;
import entities.User;
import java.util.Map;
import javax.servlet.http.HttpSession;
import validation.PositiveIntegerValidator;

public class DeleteMessageController extends controllers.Controller {
    
    private UserDAOImpl udao = new UserDAOImpl();

    private final String target_success = "/WEB-INF/error/action_result.jsp";
    private final String target_error = "/WEB-INF/error/endoftheworld.jsp";

    private MessageDAO md = new MessageDAOImpl();
    private UserDAO ud = new UserDAOImpl();
    private Message message = null;
    
    private String deleteId = null;

    @Override
    protected boolean validate_input(Map<String, String[]> map) {
        
        PositiveIntegerValidator idValidator = new PositiveIntegerValidator(errorMap, correctMap);
        idValidator.validate(map, "deleteId");
        
        return errorMap.isEmpty();
    }

    @Override
    protected boolean validate_logic() {
        return true;
    }

    @Override
    protected boolean try_execute() {
        try {

            deleteId = correctMap.get("deleteId");
            int messageId = Integer.parseInt(deleteId);

            HttpSession session = request.getSession();
            User sessionuser = (User) session.getAttribute("sessionuser");

            md.DeleteMessage(messageId, sessionuser);

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    protected void on_success() {

        request.setAttribute("message", "message");

        redirect(target_success);
    }

    @Override
    protected void on_error() {
        redirect(target_error);
    }
}

