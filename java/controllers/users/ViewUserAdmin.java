package controllers.users;

import dao.UserDAO;
import dao.UserDAOImpl;
import entities.Role;
import entities.User;
import java.util.List;
import java.util.Map;

public class ViewUserAdmin extends controllers.Controller {

    private final String target_success = "/WEB-INF/account/admin/profile.jsp";
    private final String target_error = "/WEB-INF/error/endoftheworld.jsp";

    private UserDAO ud = new UserDAOImpl();
    private User u = null;
    List<Role> roleList = null;

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
            u = ud.findByNickName(map.get("nickname")[0]);
            return u != null;
        } catch (Exception e) {

            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    protected void on_success() {
        request.setAttribute("user", u);
        
        if (u.getPhotoList() != null && u.getPhotoList().size() > 0) {
            String photo = u.getPhotoList().get(0).getPhotographUrl();
            
            request.setAttribute("userphoto", photo);             
        }

        request.setAttribute("active", u.getActive());             
        
        redirect(target_success);
    }

    @Override
    protected void on_error() {
        redirect(target_error);
    }

}