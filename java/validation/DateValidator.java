
package validation;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DateValidator extends Validator{
    private final String regexForDate ="^(19[5-9][0-9]|20[0-4][0-9]|2050)[-/](0?[1-9]|1[0-2])[-/](0?[1-9]|[12][0-9]|3[01])$";
    private final String errorMessage = "error date input";

    public DateValidator(ErrorMap errorMap, Map<String, String> correctMap) {
        super(errorMap, correctMap);
    }

    @Override
    public boolean childValidate(Map<String, String[]> map, String KEY) {
       String value = map.get(KEY)[0];

        try {
            Pattern p = Pattern.compile(regexForDate);
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
