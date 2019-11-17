package main;

import commands.BasicCommand;
import events.BasicEvent;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;

public class Bot {

    public static void main(String[] args) throws LoginException {
        PropertyLoader propertyLoader = new PropertyLoader();
        JDA jda = new JDABuilder(propertyLoader.getProperty("token")).build();
        jda.addEventListener(new BasicEvent());
        jda.addEventListener(new BasicCommand());
    }
}
