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
 * @author andreas
 */
public class AddressValidator extends Validator{
    private final String regexForAddress ="[a-zA-Z\\d\\s\\-\\,\\#\\.\\+]+";
    private final String errorMessage = "Error address : ";

    public AddressValidator(ErrorMap errorMap, Map<String, String> correctMap) {
        super(errorMap, correctMap);
    }

    @Override
    public boolean childValidate(Map<String, String[]> map, String KEY) {
       String value = map.get(KEY)[0];

        try {
            Pattern p = Pattern.compile(regexForAddress);
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
