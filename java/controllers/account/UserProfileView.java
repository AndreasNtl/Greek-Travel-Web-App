package controllers.account;

import dao.UserDAO;
import dao.UserDAOImpl;
import entities.Photo;
import entities.User;
import java.util.Map;
import javax.servlet.http.HttpSession;


public class UserProfileView extends controllers.Controller {

    private final String target_success = "/WEB-INF/account/common/profile.jsp";
    private final String target_error = "/WEB-INF/error/endoftheworld.jsp";    
    private User u = null;
    private User sessionuser = null;
    private UserDAO dao = new UserDAOImpl();

    
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
            u  = (User) session.getAttribute("sessionuser");
            
            return u != null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    protected void on_success() {
        HttpSession session = request.getSession();
        sessionuser = (User) session.getAttribute("sessionuser");
        u = dao.findById(sessionuser.getId());
        request.setAttribute("photo_url", u.getPhotoList().get(0).getPhotographUrl());
        request.setAttribute("user", u);

        redirect(target_success);
    }

    @Override
    protected void on_error() {
        redirect(target_error);
    }

}
