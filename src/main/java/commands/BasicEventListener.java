package commands;

import enums.CalculationType;
import enums.CommandType;
import enums.QuoteType;
import enums.WordnikType;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.io.IOException;

public class BasicEventListener extends ListenerAdapter {

    GuildMessageReceivedEvent event;

    public void onGuildMessageReceived(GuildMessageReceivedEvent event){

        //to avoid a seemingly spammy feeling of the the bot instantly messaging again, I wanted to try
        //adding a small sleep just before the bot will formulate its response, if any.
        try {
            Thread.sleep(200);
        } catch (Exception e) {
            //pretty sure that if this gets executed we're fucked application-wise
            e.printStackTrace();
        }

        //Set class wide working event and parse message string into useful pieces
        this.event = event;
        String message = event.getMessage().getContentRaw();
        String[] inMessageArray = message.split(" ");
        String prefix = inMessageArray[0];
        String command = prefix.replace("!", "");

        //booleans to determine factors in responding or not. We don't want the bot responding
        //to itself or something that isn't actually a command (ex. regular text chats).
        boolean isNotBot = !event.getMessage().getAuthor().isBot();
        boolean isCommand = prefix.startsWith("!");

        //booleans to determine the type of command being used
        boolean isAliveCommand = command.equalsIgnoreCase("alive");
        boolean isCalcCommand = CalculationType.getValues().contains(command);
        boolean isQuoteCommand = QuoteType.getValues().contains(command);
        boolean isWordnikCommand = WordnikType.getValues().contains(command);

        //here we are sending off the message to the applicable command class if necessary.
        //If it is not sent off, the user is using an incorrect command
        if(isNotBot && isCommand) {
            if (isCalcCommand) {
                new CalcCommand(event, command, inMessageArray);
            } else if (isQuoteCommand) {
                new QuoteCommand(event, command, message);
            } else if(isWordnikCommand) {
                try {
                    new WordnikCommand(event, command, inMessageArray);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (isAliveCommand){
                new AliveCheck(event);
            } else if (prefix.startsWith("!")) {
                printInvalid(prefix);
            }
        } else if(isNotBot){
            //for expl, see below
            checkForEasterEggs(message);
        }
    }

    //if the latest message is not a command, do some easter egg stuff.
    //TODO - Make this stuff harder to trigger in the future. It would be kind of annoying
    // to actually be trying to use a "trigger word" and have the bot be all annoying n shit.
    private void checkForEasterEggs(String message){
        if(message.toLowerCase().contains("mayes")){
            event.getChannel().sendMessage("Oh didn't you know? Mayes diddles boys!").queue();
        }
        else if(message.toLowerCase().contains("yit") || message.toLowerCase().contains("wyatt")){
            event.getChannel().sendMessage("Are you taking about Mr. Lutz - the saltiest boi?").queue();
        }
    }

    private void printInvalid(String prefix){
        event.getChannel().sendMessage("Invalid command: " + prefix).queue();
    }
}
