package enums;

import java.util.ArrayList;
import java.util.List;

public enum WordnikType {

    define, get_quote, get_quotes;

    public static List<String> getValues(){
        List<String> values = new ArrayList<>();
        for(WordnikType type : WordnikType.values()){
            values.add(type.toString());
        }
        return values;
    }
}
