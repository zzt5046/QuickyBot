package commands;

import enums.WordnikType;
import main.PropertyLoader;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WordnikCommand extends BasicCommand {

    GuildMessageReceivedEvent event;
    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    boolean failed = false;

    public WordnikCommand(GuildMessageReceivedEvent event, String command, String[] inMessage) throws IOException {
        this.event = event;
        String word = inMessage[1];
        if (command.equals("define")) {
            String url = WordnikType.define.getURL().replace("@", word);
            String definition = doGet(url, word);
            if(!failed) {
                event.getChannel().sendMessage("**" + word + "**: " + definition).queue();
            }
        }
    }

    private String doGet(String urlString, String word) throws IOException {
        String definition = "";

        PropertyLoader properties = new PropertyLoader();
        StringBuilder sb = new StringBuilder(urlString);
        sb.append("limit=1&includeRelated=false&sourceDictionaries=wiktionary%2Cwebster%2Cwordnet&useCanonical=false&includeTags=false&api_key=" + properties.getProperty("wordnik"));

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
            definition = replaceFormatting((String) jsonObject.get("text"));
        }catch (Exception e){
            e.printStackTrace();
            failed = true;
            event.getChannel().sendMessage("Something went wrong. Potential causes:\n- It probably isn't a word, you fuckin idiot.\n- The dictionaries didn't have a definition.\n- The request limit for a certain time period has been exceeded.").queue();
        }

        return definition;
    }

    private String replaceFormatting(String in){
        return in.replace("<spn>", "***")
                .replace("</spn>", "***")
                .replace("<xref>", "")
                .replace("</xref>", "")
                .replace("<ex>", "***")
                .replace("</ex>", "***");
    }

}
