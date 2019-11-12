package main;

import events.BasicCommand;
import events.BasicEvent;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;

public class Bot {

    private static final String token = "NjQzMzA1NDA1MzYzMjU3MzU1.Xcji5w.seIctO62PZM7gizi6wh3NlkxSjs";
    private final String botURL = "https://discordapp.com/api/oauth2/authorize?client_id=643305405363257355&permissions=129024&scope=bot";

    public static void main(String[] args) throws LoginException {
        JDA jda = new JDABuilder(token).build();
        jda.addEventListener(new BasicEvent());
        jda.addEventListener(new BasicCommand());
    }
}
