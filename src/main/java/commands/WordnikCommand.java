package commands;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class WordnikCommand extends BasicCommand {

    GuildMessageReceivedEvent event;

    public WordnikCommand(GuildMessageReceivedEvent event, String command, String[] inMessage){
        this.event = event;
        if (command.equals("define")) {

        } else if (command.equals("subtract")) {

        }
    }

    private String activateWordnikAPI(){
        return "";
    }
}
