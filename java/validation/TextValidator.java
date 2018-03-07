package validation;

import java.util.Map;

public class TextValidator extends Validator {

    private final String errorMessage = "Too short";

    public TextValidator(ErrorMap errorMap, Map<String, String> correctMap) {
        super(errorMap, correctMap);
    }
    

    @Override
    public boolean childValidate(Map<String, String[]> map, String KEY) {
        String value = map.get(KEY)[0];
        if (value.length() < 2) {
            errorMap.addError(KEY, errorMessage);
            return false;
        }

        return true;
    }

}
