package validation;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidator extends Validator {
    //mikor megalo megalitera apo 2 megethos string
    private final String regexForString = "^[a-zA-Z]{2,250}$";
    private final String errorMessage ="Error max: 250";

    public StringValidator(ErrorMap errorMap, Map<String, String> correctMap) {
        super(errorMap, correctMap);
    }
    
    @Override
    public boolean childValidate(Map<String, String[]> map, String KEY) {        
        String value = map.get(KEY)[0];

        try {
            Pattern p = Pattern.compile(regexForString);
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
