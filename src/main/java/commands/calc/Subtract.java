package commands.calc;

import commands.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Subtract extends Command {

    public Subtract(GuildMessageReceivedEvent event, String[] args) {
        super(event, args);
    }

    @Override
    public void execute(String[] args) {
        try {
            Double ret = Double.parseDouble(args[1]) - Double.parseDouble(args[2]);
            printResponse("The answer is **" + ret + "**");
        }catch(NumberFormatException e){
            printResponse(NUMBER_FORMAT_ERROR);
        }catch(Exception ohno){
            ohno.printStackTrace();
        }
    }
}
