package enums;

import java.util.HashMap;

public enum CommandType {
    alive("alive"),
    add("add"),
    subtract("subtract"),
    multiply("multiply"),
    divide("divide");

    private String command;

    CommandType(String command){
        this.command = command;
    };

    public String getValue(){
        return command;
    }

    public HashMap<String,CommandType> valueMap(){
        HashMap<String,CommandType> valueMap = new HashMap<>();
        for(CommandType type : CommandType.values()){
            valueMap.put(type.getValue(), type);
        }
        return valueMap;
    }
}
