package controllers.users;

import dao.UserDAO;
import dao.UserDAOImpl;
import entities.User;
import java.util.List;
import java.util.Map;
public class ViewUsersControllerByActive extends controllers.Controller{
    
    private final String target_error ="/WEB-INF/error/endoftheworld.jsp";
    private final String target_success ="/WEB-INF/account/admin/admin_list.jsp";
    
    private List<User> list;

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
       UserDAO dao = new UserDAOImpl();
    
        try {            
            list = dao.findAllUserByActivity(Integer.parseInt(map.get("active")[0]));   
            list.forEach((i) -> {
                System.out.println(i);
           });
            return true;
        } catch (Exception e) {
            list = null;
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    protected void on_success() {
        request.setAttribute("list", list);
        redirect(target_success);
    }

    @Override
    protected void on_error() {
        redirect(target_error);
    }
}
