package enums;

import java.util.ArrayList;
import java.util.List;

public enum QuoteType {

    quote, get_quote, get_quotes, delete_quote;

    public static List<String> getValues(){
        List<String> values = new ArrayList<>();
        for(QuoteType type : QuoteType.values()){
            values.add(type.toString());
        }
        return values;
    }
}
