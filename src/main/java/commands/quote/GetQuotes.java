package commands.quote;

import commands.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class GetQuotes extends Command {

    public GetQuotes(GuildMessageReceivedEvent event, String[] args) {
        super(event, args);
    }

    @Override
    public void execute(String[] args) {
        printResponse("Command not available yet.");
    }
}
