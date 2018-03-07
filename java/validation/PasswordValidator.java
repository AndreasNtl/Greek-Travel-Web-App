package validation;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PasswordValidator extends Validator{
    private final String regForPassWord = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    private final String errorMessage = "Error format ex: V#89vnsd";

    public PasswordValidator(ErrorMap errorMap, Map<String, String> correctMap) {
        super(errorMap, correctMap);
    }

    @Override
    public boolean childValidate(Map<String, String[]> map, String KEY) {
      String value = map.get(KEY)[0];

        try {
            Pattern p = Pattern.compile(regForPassWord);
            Matcher m = p.matcher(value);
            
//            if(value.trim().length() >=1){
//                 errorMap.addError(KEY,"triming");
//                 return false;
//            }

            if (m.matches()) {
                return true;
            } else {
                errorMap.addError(KEY,errorMessage);
                return false;
            }
        } catch (Exception e) {
            errorMap.addError(KEY, e.getMessage());
            return false;
        }
    }
    
}
