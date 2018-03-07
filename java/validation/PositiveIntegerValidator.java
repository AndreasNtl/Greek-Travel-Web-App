package validation;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PositiveIntegerValidator extends Validator {
    
    private final  String regexForPositivIntiger = "^[0-9]*[1-9][0-9]*$";
    private final String errorMessage =  "Error format: positive nr ";

    public PositiveIntegerValidator(ErrorMap errorMap, Map<String, String> correctMap) {
        super(errorMap, correctMap);
    }

    @Override
    public boolean childValidate(Map<String, String[]> map, String KEY) {
        String value = map.get(KEY)[0];

        try {
            Pattern p = Pattern.compile(regexForPositivIntiger);
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
