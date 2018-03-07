package controllers.users;

import dao.UserDAO;
import dao.UserDAOImpl;
import entities.User;
import java.util.Map;
import validation.PositiveIntegerValidator;

public class ViewUserAdminActivate extends controllers.Controller {

    private final String target_error = "/WEB-INF/error/endoftheworld.jsp";
    private final String target_success = "/WEB-INF/account/admin/profile.jsp";

    private UserDAO ud = new UserDAOImpl();
    private User u = null;
    Integer id;

    @Override
    protected boolean validate_input(Map<String, String[]> map) {

        PositiveIntegerValidator validator = new PositiveIntegerValidator(errorMap, correctMap);
        if (validator.validate(map, "id")) {
            System.out.println("Correct id");
            id = Integer.parseInt(correctMap.get("id"));
        }
        return errorMap.isEmpty();
    }

    @Override
    protected boolean validate_logic() {
        return true;
    }

    @Override
    protected boolean try_execute() {
        try {
          if(ud.activate(Integer.parseInt(correctMap.get("id")))){
              System.out.println("Update succesfull");
          }else{
              System.out.println("ton ipies");
              return false;
          }

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    protected void on_success() {
        User u = ud.findById(id);

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
