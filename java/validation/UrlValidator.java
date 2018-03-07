/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author psilos
 */
public class UrlValidator extends Validator{
    private final String regexForUserName = "\\s*(?i)\\s*=\\s*(\\\"([^\"]*\\\")|'[^']*'|([^'\">\\s]+)){50-4000}";
    private final String errorMessage = "Error format: url";

    public UrlValidator(ErrorMap errorMap, Map<String, String> correctMap) {
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
