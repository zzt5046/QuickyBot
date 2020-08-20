package commands;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class AliveCheck extends Command{


    public AliveCheck(GuildMessageReceivedEvent event, String[] args) {
        super(event, args);
    }

    @Override
    public void execute(String[] args) {
        printResponse("I'm alive and accepting input.");
    }
}
