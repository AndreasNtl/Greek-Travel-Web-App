package validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ErrorMap extends HashMap<String, List<String>> {
     public void addError(final String KEY, String error) {
        if (!containsKey(KEY)) {
            List<String> errors = new ArrayList<>();
            errors.add(error);
            put(KEY, errors);
        } else {
            List<String> errors = get(KEY);
            errors.add(error);
        }
    }

}
