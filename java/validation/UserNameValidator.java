
package validation;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserNameValidator extends Validator{
    private final String regexForUserName = "^[a-z0-9]{3,15}$";
    private final String errorMessage = "Error format: ex bas100";

    public UserNameValidator(ErrorMap errorMap, Map<String, String> correctMap) {
        super(errorMap, correctMap);
    }

    @Override
    public boolean childValidate(Map<String, String[]> map, String KEY) {
       String value = map.get(KEY)[0];

        try {
            Pattern p = Pattern.compile(regexForUserName);
            Matcher m = p.matcher(value);

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
