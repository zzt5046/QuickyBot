package main;

import commands.EventListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import utils.QuickyBotUtils;

import javax.security.auth.login.LoginException;

public class Bot {
    public static void main(String[] args) throws LoginException {
        FileController.init();
        PropertyLoader propertyLoader = new PropertyLoader();
        JDA jda = JDABuilder.createDefault(propertyLoader.getProperty("token")).build();
        jda.getPresence().setActivity(Activity.playing(QuickyBotUtils.getRandomStatus()));
        jda.addEventListener(new EventListener());
    }
}
