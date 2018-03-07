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
public class DoublePositiveUperToZero extends Validator {

    private final String regexFroPositiveDouble = "^[+]?(([1-9]\\d*)|0)(\\.\\d+)?";
    private final String errorMessage = "Error format ex: 50.20";
    private final String errorMessage1 = "Error: upper to 0";

    public DoublePositiveUperToZero(ErrorMap errorMap, Map<String, String> correctMap) {
        super(errorMap, correctMap);
    }

    @Override
    public boolean childValidate(Map<String, String[]> map, String KEY) {
        String value = map.get(KEY)[0];

        try {
            if (Double.parseDouble(value) == 0) {
                errorMap.addError(KEY, errorMessage1);
                return false;
            } else {
                Pattern p = Pattern.compile(regexFroPositiveDouble);
                Matcher m = p.matcher(value);

                if (m.matches()) {
                    return true;
                } else {
                    errorMap.addError(KEY, errorMessage);
                    return false;
                }
            }
        } catch (Exception e) {
            errorMap.addError(KEY, errorMessage);
            return false;
        }
    }

}

