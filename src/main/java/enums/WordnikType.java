package enums;

import java.util.ArrayList;
import java.util.List;

public enum WordnikType {

    define("define", "https://api.wordnik.com/v4/word.json/@/definitions?"), thesaurus("thesaurus","");

    private final String command;
    private final String baseUrl;

    WordnikType(String command, String url){
        this.command = command;
        this.baseUrl = url;
    }

    public String getCommand() {
        return command;
    }
    public String getURL() {
        return baseUrl;
    }

    public static List<String> getValues(){
        List<String> values = new ArrayList<>();
        for(WordnikType type : WordnikType.values()){
            values.add(type.getCommand());
        }
        return values;
    }
}
