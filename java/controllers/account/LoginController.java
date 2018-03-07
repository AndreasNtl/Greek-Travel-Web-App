package controllers.account;

import dao.UserDAOImpl;
import entities.Role;
import entities.User;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import validation.SkipValidator;
import validation.UserNameValidator;

public class LoginController extends controllers.Controller {

    private UserDAOImpl dao = new UserDAOImpl();

    private final String target = "/";
    private final String target_error = "/WEB-INF/account/login.jsp";

    @Override
    protected boolean validate_input(Map<String, String[]> map) {
        return true;
    }

    @Override
    protected boolean validate_logic() {
        UserNameValidator userNameValidator = new UserNameValidator(errorMap, correctMap);
        if (userNameValidator.validate(map, "nickname")) {
            System.out.println("Correct validesion of nickname : " + correctMap.get("nickname"));
        }
        UserNameValidator password = new UserNameValidator(errorMap, correctMap);
        if (password.validate(map, "password")) {
            System.out.println("Correct possword : " + correctMap.get("password"));
        }
//        SkipValidator userNameValidator = new SkipValidator(errorMap, correctMap);
//        if (userNameValidator.validate(map, "nickname")) {
//            System.out.println("Correct validesion of nickname");
//        }
//        SkipValidator passwordValidator = new SkipValidator(errorMap, correctMap);
//        if (passwordValidator.validate(map, "password")) {
//            System.out.println("Correct possword");
//        }

        return errorMap.isEmpty();
    }

    @Override
    protected boolean try_execute() {
        try {
            User findByNickName = dao.findByNickName(correctMap.get("nickname"));

            if (findByNickName == null) {
                errorMap.addError("account", "Invaled account");
                return false;
            } else {
                String username1 = correctMap.get("nickname");
                String password1 = correctMap.get("password");

                //   GenerateHashing  passwordHash = new GenerateHashing() ;
                //    String newpass = passwordHash.GeneratePassword(username1, "Legend");
                String username2 = findByNickName.getNickname();
                String password2 = findByNickName.getPassword();

                if (username1.equals(username2) && password1.equals(password2)) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("sessionuser", findByNickName);

                    List<Role> roleList = findByNickName.getRoleList();

                    for (Role r : roleList) {
                        if (r.getId() == 1) { // admin
                            session.setAttribute("isadmin", true);
                        }
                        if (r.getId() == 2) { // ownder
                            session.setAttribute("isowner", true);
                            session.setAttribute("isvisitor", true);
                        }
                        if (r.getId() == 3) { // visitor
                            session.setAttribute("isvisitor", true);
                        }
                    }

                    return true;
                } else {
                    errorMap.addError("account", "Invalid credentials");
                    return false;
                }
            }
        } catch (Exception e) {
            errorMap.addError("exception", e.getMessage());
            return false;
        }
    }

    @Override
    protected void on_success() {
        redirect(target);
    }

    @Override
    protected void on_error() {
        request.setAttribute("errorMap", errorMap);
        redirect(target_error);
    }

}
