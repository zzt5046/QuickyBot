package commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public abstract class Command {

    GuildMessageReceivedEvent event;
    String[] args;

    public final String NUMBER_FORMAT_ERROR = "You entered a parameter that is not a number, or incorrectly entered arguments.";
    public final String WORDNIK_ERROR = "There was an error communicating with Wordnik services. Let dev know pls.";
    public final String GENERIC_ERROR = "Something went wrong. If you see this message, that means I don't really know what happened.";


    public Command(GuildMessageReceivedEvent event, String[] args){
        this.event = event;
        this.args = args;
        execute(args);
    }

    public abstract void execute(String[] args);

    public void printResponse(String message){
        event.getChannel().sendMessage(message).queue();
    };

}
