package controllers.account;

import dao.PhotoDAO;
import dao.PhotoDAOImpl;
import dao.UserDAOImpl;
import dao.UserHasRatedDAO;
import dao.UserHasRatedDAOImpl;
import entities.Photo;
import entities.Role;
import entities.User;
import entities.UserHasRated;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import validation.EmailValidator;
import validation.NameValidator;
import validation.PositiveIntegerValidator;
import validation.SkipValidator;
import validation.UserNameValidator;


    public class RegisterController extends controllers.Controller {

    private final String target = "/WEB-INF/report/register_complete.jsp";
    private final String target_error = "/WEB-INF/account/register.jsp";

    private final String defaultPhoto = "http://images6.fanpop.com/image/photos/39500000/Rick-And-Morty-616x480-rick-and-morty-39568274-616-480.png";

    private UserDAOImpl dao = new UserDAOImpl();
    private PhotoDAO pd = new PhotoDAOImpl();
    private UserHasRatedDAO uhrdao = new UserHasRatedDAOImpl();

    @Override
    protected boolean validate_input(Map<String, String[]> map) {

        UserNameValidator userNameValidator = new UserNameValidator(errorMap, correctMap);
        if (userNameValidator.validate(map, "nickname")) {
            System.out.println("Correct validesion of nickname : " + correctMap.get("nickname"));
        }
        UserNameValidator password = new UserNameValidator(errorMap, correctMap);
        if (password.validate(map, "password")) {
            System.out.println("Correct possword : " + correctMap.get("password"));
        }
        UserNameValidator repeat_password = new UserNameValidator(errorMap, correctMap);
        if (repeat_password.validate(map, "repeat_password")) {
            System.out.println("Correct repeat_password");
        }
        NameValidator stringValidetor = new NameValidator(errorMap, correctMap);
        if (stringValidetor.validate(map, "firstname")) {
            System.out.println("Correct firstname : " + correctMap.get("firstname"));
        }
        NameValidator stringValidetorSurname = new NameValidator(errorMap, correctMap);
        if (stringValidetorSurname.validate(map, "surname")) {
            System.out.println("Correct surname : " + correctMap.get("surname"));
        }
        EmailValidator emailValidetor = new EmailValidator(errorMap, correctMap);
        if (emailValidetor.validate(map, "email")) {
            System.out.println("Correct Email : email" + correctMap.get("email"));
        }
        PositiveIntegerValidator phoneNumberValidesion = new PositiveIntegerValidator(errorMap, correctMap);
        if (phoneNumberValidesion.validate(map, "phonenumber")) {
            System.out.println("Correct phonenumber : " + correctMap.get("phonenumber"));
        }
 
        if (map.get("photo")[0] == null || map.get("photo")[0].isEmpty()) {
            correctMap.put("photo", defaultPhoto);
        } else {
            if (map.get("photo")[0].length() < 4000) {
                correctMap.put("photo", map.get("photo")[0]);
            } else {
                errorMap.addError("photo", "Large size of photo");
            }
        }

        SkipValidator isownerValidator = new SkipValidator(errorMap, correctMap);
        isownerValidator.validate(map, "isowner");

        return errorMap.isEmpty();
    }

    @Override
    protected boolean validate_logic() {

        if (!correctMap.get("password").equals(correctMap.get("repeat_password"))) {
            errorMap.addError("repeat_password", "passwords don't matches");
        }
        User user = dao.findByNickName(correctMap.get("nickname"));
        if (user != null) {
            errorMap.addError("nickname", "duplicate username");
        }

        User u = dao.findByEmail(correctMap.get("email"));
        if (u != null) {
            errorMap.addError("email", "duplicate email");
        }
        return errorMap.isEmpty();

    }

    @Override
    protected boolean try_execute() {

        try {
            User u = new User();

            //      configure photo
            List<Photo> photos = new ArrayList<>();
            Photo p = new Photo();
            p.setPhotographUrl(correctMap.get("photo"));
            photos.add(p);

            //       configure roles
            List<Role> roles = new ArrayList<>();
            Role r1 = new Role(3);
            roles.add(r1);

            if (correctMap.get("isowner") != null) {
//                Role r2 = new Role(2);
//            roles.add(r2);
                u.setActive(1);
            } else {
                u.setActive(0);
            }

            //   GenerateHashing  passwordHash = new GenerateHashing() ;
            //   String newpass = passwordHash.GeneratePassword(correctMap.get("password"), "Legend");
            u.setNickname(correctMap.get("nickname"));
            u.setPassword(correctMap.get("password"));
            u.setFirstName(correctMap.get("firstname"));
            u.setSurname(correctMap.get("surname"));
            u.setEmail(correctMap.get("email"));
            u.setPhoneNumber(correctMap.get("phonenumber"));
            u.setPhotoList(photos);
            u.setRoleList(roles);
            //    u.setActive(0);

            Integer newUserId = dao.createWithPhoto(u);
            if (newUserId == null) {
                return false;
            }
//            System.out.println("User object: " + u);

            UserHasRated uhrated = new UserHasRated();
            uhrated.setId(newUserId);
            uhrated.setHasRated(false);
            uhrdao.create(uhrated);

            return true;
        } catch (Exception e) {
            System.out.println("User object: " + e.getMessage());
            // errorMap.addError("exception", e.getMessage());
            return false;
        }

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


       //________________________Skip_validator______________________deletie___
//        SkipValidator userNameValidator = new SkipValidator(errorMap, correctMap);
//        userNameValidator.validate(map,"nickname");
//        
//        SkipValidator passwordValidator = new SkipValidator(errorMap, correctMap);
//        passwordValidator.validate(map, "password");
//        
//        SkipValidator passwordValidatorRepet = new SkipValidator(errorMap, correctMap);
//        passwordValidatorRepet.validate(map,"repeat_password");
//        
//        SkipValidator stringValidetor = new SkipValidator(errorMap, correctMap);
//        stringValidetor.validate(map, "firstname");
//        
//        SkipValidator stringValidetorSurname = new SkipValidator(errorMap, correctMap);
//        stringValidetorSurname.validate(map, "surname");
//        
//        SkipValidator emailValidetor = new SkipValidator(errorMap, correctMap);
//        emailValidetor.validate(map, "email");
//        
//        SkipValidator phoneNumberValidesion = new SkipValidator(errorMap, correctMap);
//        phoneNumberValidesion.validate(map, "phonenumber");
//        
        //______________________________________________________________________