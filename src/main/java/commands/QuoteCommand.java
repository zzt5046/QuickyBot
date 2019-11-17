package commands;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class QuoteCommand {

    GuildMessageReceivedEvent event;

    public QuoteCommand(GuildMessageReceivedEvent event, String command, String[] inMessage){
        this.event = event;
        if (command.equals("quote")) {

        }
        else if (command.equals("get_quote")) {

        }
        else if (command.equals("get_quotes")) {

        }
    }

}
