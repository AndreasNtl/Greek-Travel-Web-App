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
public class CountryNameValidator extends Validator{
    private final String regexForCountryName ="^[A-Z][a-z]+( [A-Z][a-z]+)*$";
    private final String errorMessage = "error county name input";

    public CountryNameValidator(ErrorMap errorMap, Map<String, String> correctMap) {
        super(errorMap, correctMap);
    }

    @Override
    public boolean childValidate(Map<String, String[]> map, String KEY) {
       String value = map.get(KEY)[0];

        try {
            Pattern p = Pattern.compile(regexForCountryName);
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
