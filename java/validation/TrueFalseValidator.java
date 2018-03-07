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
public class TrueFalseValidator extends Validator {

    public TrueFalseValidator(ErrorMap errorMap, Map<String, String> correctMap) {
        super(errorMap, correctMap);
    }

    @Override
    public boolean childValidate(Map<String, String[]> map, String KEY) {

        String value = map.get(KEY)[0];
        try {
            if (value.equals("true") || value.equals("false")) {
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