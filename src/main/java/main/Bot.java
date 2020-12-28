package main;

import commands.BasicEventListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import utils.QuickyBotUtils;

import javax.security.auth.login.LoginException;

public class Bot {

    public static void main(String[] args) throws LoginException {
        FileController.getInstance().init();
        JDA jda = JDABuilder.createDefault(PropertyLoader.getInstance().getProperty("token")).build();
        jda.getPresence().setActivity(Activity.playing(QuickyBotUtils.getRandomStatus()));
        jda.addEventListener(new BasicEventListener());
    }
}
