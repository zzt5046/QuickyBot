package data;

import commands.misc.AliveCheck;
import commands.calc.Add;
import commands.calc.Divide;
import commands.calc.Multiply;
import commands.calc.Subtract;
import commands.quote.DeleteQuote;
import commands.quote.GetQuote;
import commands.quote.GetQuotes;
import commands.quote.Quote;
import commands.wordnik.Define;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.HashMap;

public class CommandStorage {

    private static Logger logger = LoggerFactory.getLogger(CommandStorage.class);

    private GuildMessageReceivedEvent event;
    private String[] args;
    static final HashMap<String,Class> commands = new HashMap<String,Class>();

    static{
        commands.put("alive", AliveCheck.class);
        commands.put("add", Add.class);
        commands.put("subtract", Subtract.class);
        commands.put("multiply", Multiply.class);
        commands.put("divide", Divide.class);
        commands.put("quote", Quote.class);
        commands.put("get-quote", GetQuote.class);
        commands.put("get-quotes", GetQuotes.class);
        commands.put("delete-quote", DeleteQuote.class);
        commands.put("define", Define.class);
    }

    public CommandStorage(GuildMessageReceivedEvent event, String[] args){
        this.event = event;
        this.args = args;
    }

    public void runCommand(String command) {
        try {
            Constructor constructor = commands.get(command).getDeclaredConstructor(GuildMessageReceivedEvent.class, String[].class);
            constructor.newInstance(event, args);
        }catch (NullPointerException e){
            String user = event.getAuthor().getName();
            logger.error(user + " has entered invalid command: \"" + command + "\"");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
