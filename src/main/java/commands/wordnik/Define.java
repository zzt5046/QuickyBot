package commands.wordnik;

import commands.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import utils.QuickyBotUtils;

public class Define extends Command {

    public Define(GuildMessageReceivedEvent event, String[] args) {
        super(event, args);
    }

    @Override
    public void execute(String[] args) {
        String word = args[1];
        String url = getCommandURL(WordnikType.define, word);
        try {
            String definition = WordnikAPI.call(url, WordnikType.define);
            printResponse(QuickyBotUtils.bold(word) + ": " + definition);
        }catch (Exception e){
            printResponse(WORDNIK_ERROR);
        }
    }

    private String getCommandURL(WordnikType commandType, String word){
        return commandType.getURL().replace("@", word);
    }
}
