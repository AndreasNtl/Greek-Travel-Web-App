package validation;

import java.util.Map;

public abstract class Validator {
    protected ErrorMap errorMap;    
    protected Map<String, String> correctMap;

    public Validator(ErrorMap errorMap, Map<String, String> correctMap) {
        this.errorMap = errorMap;
        this.correctMap = correctMap;
    }
    
    public boolean validate(Map<String, String[]> map, final String KEY) {
        if (!map.containsKey(KEY) || map.get(KEY).length == 0 || map.get(KEY)[0].isEmpty()) {
            errorMap.addError(KEY, "missing");
            return false;
        } else {
            boolean ok = childValidate(map, KEY);
            
            if (ok) {
                correctMap.put(KEY, map.get(KEY)[0]);
            }
            
            return ok;
        }
    }
    
    public abstract boolean childValidate(Map<String, String[]> map, final String KEY);
}
