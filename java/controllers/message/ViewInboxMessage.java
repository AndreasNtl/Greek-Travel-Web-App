package controllers.message;

import dao.MessageDAO;
import dao.MessageDAOImpl;
import dao.UserDAOImpl;
import entities.Message;
import entities.User;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import validation.PositiveIntegerValidator;

public class ViewInboxMessage extends controllers.Controller {

    private UserDAOImpl udao = new UserDAOImpl();

    private final String target_success = "/WEB-INF/account/message/inbox.jsp";
    private final String target_error = "/WEB-INF/error/endoftheworld.jsp";

    private MessageDAO md = new MessageDAOImpl();
    List<Message> messages = null;
    List<User> listOfUserThatHaveSendMessage = null;

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
        try {

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("sessionuser");
            if (user == null) {//to eyala egw den kserw an xriazete billis
                return false;
            }
            // int id = Integer.parseInt(user.getId());

            noOfRecords = md.receivedMessageCounter(user.getId());
            messages = md.receivedMessagePagination((currentPage - 1) * recordsPerPage, recordsPerPage, user.getId());

            numberOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
//            listOfUserThatHaveSendMessage = new ArrayList<>();
//            User u;
//            for (Message m : messages) {
//                u = (User) m.getSenderId();
//                listOfUserThatHaveSendMessage.add(u);
//                System.out.println(u.getNickname());
//            }

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    protected void on_success() {

        //   request.setAttribute("listOfUserThatHaveSendMessage", listOfUserThatHaveSendMessage);
        request.setAttribute("messages", messages);
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
