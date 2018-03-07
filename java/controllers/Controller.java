package controllers;

import java.util.HashMap;

import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import validation.ErrorMap;


public abstract class Controller {

    // --------------------------------------------
    protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected Map<String, String[]> map;

    protected ErrorMap errorMap = new ErrorMap();

    protected Map<String, String> correctMap = new HashMap<>();

    protected ServletContext context;

    protected abstract boolean validate_input(Map<String, String[]> map);

    protected abstract boolean validate_logic();

    protected abstract boolean try_execute();

    protected abstract void on_success();

    protected abstract void on_error();

    protected final void redirect(String address) {
        try {
            RequestDispatcher disp = context.getRequestDispatcher(address);
            disp.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // --------------------------------------------
    public void execute(ServletContext context, Map<String, String[]> map, HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.map = map;
        this.context = context;

        if (map != null) {

            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                String key = entry.getKey();
                String[] value = entry.getValue();

                if (value.length != 1) {
                    request.setAttribute(key, value);
                } else {
                    request.setAttribute(key, value[0]);
                }
            }

        }
        boolean validation_success = validate_input(map);

        if (validation_success) {
            try {
                validation_success = validate_logic();

                if (validation_success) {
                    boolean execution_successfull = try_execute();

                    if (execution_successfull) {
                        on_success();
                    } else {
                        request.setAttribute("errorMap", errorMap);
                        on_error();
                    }
                } else {
                    request.setAttribute("errorMap", errorMap);
                    on_error();
                }
            } catch (Exception e) {
                throw e;
            }
        } else {
            request.setAttribute("errorMap", errorMap);
            on_error();
        }
    }

    public void execute(ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        execute(context, null, request, response);
    }
}
