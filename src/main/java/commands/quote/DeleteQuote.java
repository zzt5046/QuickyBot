package commands.quote;

import commands.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class DeleteQuote extends Command {

    public DeleteQuote(GuildMessageReceivedEvent event, String[] args) {
        super(event, args);
    }

    @Override
    public void execute(String[] args) {
        printResponse("Command not available yet.");
    }
}
