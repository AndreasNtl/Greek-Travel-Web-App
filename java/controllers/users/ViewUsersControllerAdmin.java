package controllers.users;

import dao.UserDAO;
import dao.UserDAOImpl;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import validation.PositiveIntegerValidator;

public class ViewUsersControllerAdmin extends controllers.Controller {

    private final String target_error = "/WEB-INF/error/endoftheworld.jsp";
    private final String target_success = "/WEB-INF/account/admin/admin_list.jsp";

    private List<User> list = null;
    private List<String> listPhotosUrl = null;

    int currentPage;

    int recordsPerPage = 10;

    int noOfRecords;

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
        return errorMap.isEmpty();
    }

    @Override
    protected boolean validate_logic() {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("sessionuser");
        if (u != null) {
            return u.getNickname().equals("admin");
        } else {
            return false;
        }
    }

    @Override
    protected boolean try_execute() {
        UserDAO dao = new UserDAOImpl();
        listPhotosUrl = new ArrayList<>();

        try {
            noOfRecords =(int) dao.findNumberOfUsers();
//            System.out.println("Number of Users" + noOfRecords);
            list = dao.patinagtion((currentPage - 1) * recordsPerPage, recordsPerPage);

            numberOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

            for (User u : list) {//edw an einai null i empty na stelw mia defaul
                String photourl = u.getPhotoList().get(0).getPhotographUrl();
                listPhotosUrl.add(photourl);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            list = null;
            return false;
        }

    }

    @Override
    protected void on_success() {

        request.setAttribute("list", list);
        request.setAttribute("listPhotosUrl", listPhotosUrl);
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
