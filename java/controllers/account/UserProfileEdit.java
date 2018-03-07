package controllers.account;

import dao.UserDAO;
import dao.UserDAOImpl;
import entities.Photo;
import entities.User;
import java.util.Map;
import javax.servlet.http.HttpSession;

public class UserProfileEdit extends controllers.Controller {

    private final String target_success = "/WEB-INF/account/common/edit.jsp";
    private final String target_error = "/WEB-INF/error/endofworld.jsp";
    private UserDAO ud = new UserDAOImpl();
    private User sessionuser = null;

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
            sessionuser = (User) session.getAttribute("sessionuser");
            return sessionuser != null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    protected void on_success() {

        UserDAOImpl dao = new UserDAOImpl();

        try {
            Photo p = sessionuser.getPhotoList().get(0);
            User u = dao.findById(sessionuser.getId());
            request.setAttribute("user", u);
            request.setAttribute("photo_url", p.getPhotographUrl());
        } catch (Exception ex) {
		
        }	

        redirect(target_success);
    }

    @Override
    protected void on_error() {
        redirect(target_error);
    }

}