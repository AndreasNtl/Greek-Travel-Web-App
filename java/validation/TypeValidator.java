package validation;

import java.util.Map;

public class TypeValidator extends Validator {

    public TypeValidator(ErrorMap errorMap, Map<String, String> correctMap) {
        super(errorMap, correctMap);
    }

    @Override
    public boolean childValidate(Map<String, String[]> map, String KEY) {
        String value = map.get(KEY)[0];
        try {
            if (value.equals("private room") || value.equals("shared room") || value.equals("house") || value.equals("villa") || value.equals("Private room") || value.equals("House") || value.equals("Shared room") || value.equals("Villa")) {
                return true;
            } else {
                errorMap.addError(KEY, "Hackers");
                return false;
            }
        } catch (Exception e) {
            errorMap.addError(KEY, e.getMessage());
            return false;
        }
    }

}
