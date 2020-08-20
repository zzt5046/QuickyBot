package commands.calc;

import java.util.ArrayList;
import java.util.List;

public enum CalculationType {
    add, subtract, multiply, divide;

    public static List<String> getValues(){
        List<String> values = new ArrayList<>();
        for(CalculationType type : CalculationType.values()){
            values.add(type.toString());
        }
        return values;
    }
}
