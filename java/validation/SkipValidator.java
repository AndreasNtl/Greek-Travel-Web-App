package validation;

import java.util.Map;


public class SkipValidator {

    private final ErrorMap errorMap;
    private final Map<String, String> correctMap;

    public SkipValidator(ErrorMap errorMap, Map<String, String> correctMap) {
        this.errorMap = errorMap;
        this.correctMap = correctMap;
    }

    public boolean validate(Map<String, String[]> map, final String KEY) {
        if (!map.containsKey(KEY) || map.get(KEY).length == 0 || map.get(KEY)[0].isEmpty()) {            
            // .. do nothing
        } else {
            correctMap.put(KEY, map.get(KEY)[0]);
        }
        return true;
    }
}
