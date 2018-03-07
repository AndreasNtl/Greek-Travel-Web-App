package controllers.dispatch;

import java.util.Map;

public class StaticDispatchController extends controllers.Controller {

    private String target = null;

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
        if (map.containsKey("page")) {
            String[] values = map.get("page");

            String value = values[0];

            if (value.equalsIgnoreCase("home")) {
                target = "/";
            }

            if (value.equalsIgnoreCase("contact")) {
                target = "/WEB-INF/static/contact.jsp";
            }

            if (value.equalsIgnoreCase("about")) {
                target = "/WEB-INF/static/about.jsp";
            }

            if (value.equalsIgnoreCase("login")) {
                target ="/WEB-INF/account/login.jsp";
//                target = "/WEB-INF/session/login.jsp";
            }

            if (value.equalsIgnoreCase("register")) {
                target = "/WEB-INF/account/register.jsp";
            }

            if (value.equalsIgnoreCase("singleItem")) {
                target = "/WEB-INF/singleItem.jsp";
            }

            if (value.equalsIgnoreCase("searchresult")) {
                target = "/WEB-INF/searchresult.jsp";
            }
            
            if (value.equalsIgnoreCase("advanced")) {
                target = "/WEB-INF/fragments/search.jsp";
            }

            if (value.equalsIgnoreCase("edit")) {
                target = "/WEB-INF/account/admin/edit.jsp";
            }

            if (value.equalsIgnoreCase("sendmessage")) {
                target = "/WEB-INF/account/message/send_message.jsp";
            }

            // ----------------------------------------------
            if (value.equalsIgnoreCase("admin")) {
                target = "/WEB-INF/account/admin/admin_list.jsp";
            }

            if (value.equalsIgnoreCase("t1")) {
                target = "/WEB-INF/examples/index_1.jsp";
            }
            if (value.equalsIgnoreCase("t2")) {
                target = "/WEB-INF/examples/index_2.jsp";
            }
            if (value.equalsIgnoreCase("t3")) {
                target = "/WEB-INF/examples/index_3.jsp";
            }
            if (value.equalsIgnoreCase("t4")) {
                target = "/WEB-INF/examples/index_4.jsp";
            }

            if (value.equalsIgnoreCase("t5")) {
                target = "/WEB-INF/examples/index_5.jsp";
            }
        }

        return true;
    }

    @Override
    protected void on_success() {
        if (target != null) {
            redirect(target);
        } else {
            on_error();
        }
    }

    @Override
    protected void on_error() {
        redirect("/WEB-INF/error/endoftheworld.jsp");
    }

}
