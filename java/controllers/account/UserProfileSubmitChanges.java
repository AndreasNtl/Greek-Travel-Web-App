package controllers.account;

import HashPassword.GenerateHashing;
import dao.PhotoDAO;
import dao.PhotoDAOImpl;
import dao.UserDAOImpl;
import entities.Photo;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import validation.EmailValidator;
import validation.NameValidator;
import validation.PositiveIntegerValidator;
import validation.SkipValidator;


public class UserProfileSubmitChanges extends controllers.Controller {

    private UserDAOImpl dao = new UserDAOImpl();
    private PhotoDAO pd = new PhotoDAOImpl();

    private final String target_succses = "/WEB-INF/account/common/profile.jsp";
    private final String target_error = "/WEB-INF/account/common/edit.jsp";
    private boolean password_should_change = false;

    private User sessionuser = null;

    @Override
    protected boolean validate_input(Map<String, String[]> map) {

        NameValidator stringValidetor = new NameValidator(errorMap, correctMap);
        if (stringValidetor.validate(map, "firstname")) {
            System.out.println("Correct firstname");
        }
        NameValidator stringValidetorSurname = new NameValidator(errorMap, correctMap);
        if (stringValidetorSurname.validate(map, "surname")) {
            System.out.println("Correct surname");
        }
        EmailValidator emailValidetor = new EmailValidator(errorMap, correctMap);
        if (emailValidetor.validate(map, "email")) {
            System.out.println("Correct Email");
        }
        PositiveIntegerValidator phoneNumberValidesion = new PositiveIntegerValidator(errorMap, correctMap);
        if (phoneNumberValidesion.validate(map, "phonenumber")) {
            System.out.println("Correct phonenumber");
        }

        if(map.get("photo") == null || map.get("photo")[0].isEmpty()){
            errorMap.addError("photo", "missing");
        }else{
            if(map.get("photo")[0].length() > 4000){
                errorMap.addError("photo", "Large size of photo");
            }else{
                correctMap.put("photo", map.get("photo")[0]);
            }
        }

//        SkipValidator stringValidetor = new SkipValidator(errorMap, correctMap);
//        stringValidetor.validate(map, "firstname");
//
//        SkipValidator stringValidetorSurname = new SkipValidator(errorMap, correctMap);
//        stringValidetorSurname.validate(map, "surname");

//        SkipValidator emailValidetor = new SkipValidator(errorMap, correctMap);
//        emailValidetor.validate(map, "email");
//
//        SkipValidator phoneNumberValidesion = new SkipValidator(errorMap, correctMap);
//        phoneNumberValidesion.validate(map, "phonenumber");

//        SkipValidator photoValidetor = new SkipValidator(errorMap, correctMap);
//        photoValidetor.validate(map, "photo");

        // password change
        SkipValidator passwordValidator1 = new SkipValidator(errorMap, correctMap);
        passwordValidator1.validate(map, "oldpassword");

        SkipValidator passwordValidator2 = new SkipValidator(errorMap, correctMap);
        passwordValidator2.validate(map, "newpassword");

        SkipValidator passwordValidator3 = new SkipValidator(errorMap, correctMap);
        passwordValidator3.validate(map, "repeat_password");

        return errorMap.isEmpty();
    }

    @Override
    protected boolean validate_logic() {
        try {
            HttpSession session = request.getSession();
            sessionuser = (User) session.getAttribute("sessionuser");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        String oldpassword = correctMap.get("oldpassword");
        String newpassword = correctMap.get("newpassword");
        String repeat_password = correctMap.get("repeat_password");

//        if(!sessionuser.getPassword().equals(oldpassword)){
//
//            errorMap.addError("oldpassword", "invalid password");
//            password_should_change = false;
//            return errorMap.isEmpty();
//        }


        if (oldpassword != null && oldpassword.trim().length() > 1
                && (newpassword != null && newpassword.trim().length() > 1
                && repeat_password != null && repeat_password.trim().length() > 1)) {
            // password should be changed
            if (!newpassword.equals(repeat_password)) {
                if (!newpassword.equals(repeat_password)) {
                    errorMap.addError("repeat_password", "passwords don't matches");
                }
                password_should_change = false;
            } else {
                password_should_change = true;
            }
        }

        try {
            String email = correctMap.get("email");
            if (email != null) {

                if (!email.equals(sessionuser.getEmail())) {
                    User other = dao.findByEmail(email);

                    if (other != null) {
                        errorMap.addError("email", "email already taken");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return errorMap.isEmpty();
    }

    @Override
    protected boolean try_execute() {
        try {
            HttpSession session = request.getSession();
            sessionuser = (User) session.getAttribute("sessionuser");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        // ------------------------------------------------------
        try {
            //photo
            List<Photo> photos = new ArrayList<>();
            Photo p = new Photo();
            p.setPhotographUrl(correctMap.get("photo"));
            photos.add(p);

            sessionuser.setFirstName(correctMap.get("firstname"));
            sessionuser.setSurname(correctMap.get("surname"));
            if (password_should_change) {
            //    GenerateHashing passwordHash = new GenerateHashing();
             //   String newpass = passwordHash.GeneratePassword(correctMap.get("password"), "Legend");
                sessionuser.setPassword(correctMap.get("newpassword"));
            } else {
                sessionuser.setPassword(correctMap.get("newpassword"));
            }
            sessionuser.setEmail(correctMap.get("email"));
            sessionuser.setPhoneNumber(correctMap.get("phonenumber"));
            sessionuser.setPhotoList(photos);

            dao.submitChanges(sessionuser);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    protected void on_success() {
        HttpSession session = request.getSession();
        sessionuser = (User) session.getAttribute("sessionuser");
        User u = dao.findById(sessionuser.getId());
        request.setAttribute("photo_url", u.getPhotoList().get(0).getPhotographUrl());
        request.setAttribute("user", u);
        redirect(target_succses);
    }

    @Override
    protected void on_error() {
        HttpSession session = request.getSession();
        sessionuser = (User) session.getAttribute("sessionuser");
        User u = dao.findById(sessionuser.getId());
        request.setAttribute("photo_url", u.getPhotoList().get(0).getPhotographUrl());
        request.setAttribute("user", u);
        redirect(target_error);
    }
}
