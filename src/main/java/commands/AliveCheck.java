package commands;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class AliveCheck {

    public AliveCheck(GuildMessageReceivedEvent event){
        event.getChannel().sendMessage("I'm alive and accepting input.").queue();
    }
}
