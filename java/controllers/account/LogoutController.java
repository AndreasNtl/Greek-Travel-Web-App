package controllers.account;

import java.util.Map;
import javax.servlet.http.HttpSession;


public class LogoutController extends controllers.Controller{
    private final String target = "/";
    private final String target_error =  "/WEB-INF/index.jsp";
    
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
        HttpSession session = request.getSession();
        
        session.removeAttribute("sessionuser");
        session.removeAttribute("isowner");
        session.removeAttribute("isadmin");
        session.removeAttribute("isvisitor");
        
        session.invalidate();
        
        return true;        
    }

     @Override
    protected void on_success() {
        redirect(target);
    }

    @Override
    protected void on_error() {
        redirect(target_error);
    }
    
}
