package commands.wordnik;

import main.PropertyLoader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WordnikAPI {

    private static final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private static boolean failed = false;

    static String call(String urlString, WordnikType type) throws IOException {
        String returnInfo = "";

        PropertyLoader properties = new PropertyLoader();
        StringBuilder sb = new StringBuilder(urlString);
        if(type == WordnikType.define) {
            sb.append("limit=1&includeRelated=false&sourceDictionaries=wiktionary%2Cwebster%2Cwordnet&useCanonical=false&includeTags=false&api_key=" + properties.getProperty("wordnik"));
        }

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(sb.toString()))
                .setHeader("User-Agent", "Java 11 HttpClient")
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(response.body());
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);
            if(type == WordnikType.define) {
                returnInfo = replaceFormatting((String) jsonObject.get("text"));
            }
        }catch (Exception e){
            e.printStackTrace();
            failed = true;
            return getCommandErrorMessage(type);
        }

        return returnInfo;
    }

    private static String getCommandErrorMessage(WordnikType type){
        if(type == WordnikType.define){
            return "Something went wrong. Potential causes:\n- It probably isn't a word, you fuckin idiot.\n- The dictionaries didn't have a definition.\n- The request limit for a certain time period has been exceeded.";
        }

        return null;
    }

    private static String replaceFormatting(String in){
        return in.replaceAll("\\<.*?\\>", "");
    }
}
