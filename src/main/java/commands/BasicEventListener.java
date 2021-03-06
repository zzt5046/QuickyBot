package commands;

import data.CommandStorage;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BasicEventListener extends ListenerAdapter {

    private static Logger logger = LoggerFactory.getLogger(BasicEventListener.class);

    public BasicEventListener(){
        logger.info("Basic event listener initialized.");
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event){

        //small sleep just before the bot will formulate its response
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String message = event.getMessage().getContentRaw();
        String[] messageArgs = message.split(" ");

        String prefix = messageArgs[0];
        String command = prefix.replace("!", "");

        //booleans to determine factors in responding or not. We don't want the bot responding
        //to itself or something that isn't actually a command (ex. regular text chats).
        boolean isNotBot = !event.getMessage().getAuthor().isBot();
        boolean isCommandFormat = prefix.startsWith("!");

        //here we are sending off the message to the applicable command class if necessary.
        //If it is not sent off, the user is using an incorrect command
        if(isNotBot && isCommandFormat) {
            CommandStorage commandStore = new CommandStorage(event, messageArgs);
            commandStore.runCommand(command);
        }
    }
}
