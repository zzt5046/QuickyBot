package main;

import commands.EventListener;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import javax.security.auth.login.LoginException;

public class Bot {
    public static void main(String[] args) throws LoginException {
        FileController.init();
        PropertyLoader propertyLoader = new PropertyLoader();
        JDA jda = new JDABuilder(propertyLoader.getProperty("token")).build();
        jda.getPresence().setGame(Game.playing("with your mom"));
        jda.addEventListener(new EventListener());
    }
}
