package enums;

import java.util.ArrayList;
import java.util.List;

public enum CalculationType {
    ADD, SUBTRACT, MULTIPLY, DIVIDE;

    public static List<String> getValues(){
        List<String> values = new ArrayList<>();
        for(CalculationType type : CalculationType.values()){
            values.add(type.toString().toLowerCase());
        }
        return values;
    }
}
