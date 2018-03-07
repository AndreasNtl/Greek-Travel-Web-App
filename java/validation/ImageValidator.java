package validation;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageValidator extends Validator {

    public ImageValidator(ErrorMap errorMap, Map<String, String> correctMap) {
        super(errorMap, correctMap);
    }

 
    private final String regexImg = "(@)?(href=')?(HREF=')?(HREF=\")?(href=\")?(http://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?";
    private final String errorMessage = "error Img format: ex a.png";

    @Override
    public boolean childValidate(Map<String, String[]> map, String KEY) {
        String value = map.get(KEY)[0];

        try {
            Pattern p = Pattern.compile(regexImg);
            Matcher m = p.matcher(value);

            if (m.matches()) {
                return true;
            } else {
                errorMap.addError(KEY, errorMessage);
                return false;
            }
        } catch (Exception e) {
            errorMap.addError(KEY, e.getMessage());
            return false;
        }
    }
}

