package commands;

import enums.CalculationType;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BasicCommand extends ListenerAdapter {

    GuildMessageReceivedEvent event;

    //lol
    private ArrayList<String> insults = new ArrayList<>();

    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        try {
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.event = event;
        String message = event.getMessage().getContentRaw();
        String[] inMessageArray = message.split(" ");
        String prefix = inMessageArray[0];
        String command = prefix.replace("!", "");

        boolean isNotBot = !event.getMessage().getAuthor().isBot();
        boolean isCalcCommand = CalculationType.getValues().contains(command);

        if(isNotBot) {
            if(isCalcCommand){
                new CalcCommand(event, command, inMessageArray);
            }
            else if(prefix.startsWith("!")){
                printInvalid(prefix);
            }

            if(message.toLowerCase().contains("mayes")){
                event.getChannel().sendMessage("Oh didn't you know? Mayes diddles boys!").queue();
            }
            else if(message.toLowerCase().contains("yit") || message.toLowerCase().contains("wyatt")){
                event.getChannel().sendMessage("Are you taking about Wyatt - the saltiest boi?").queue();
            }
        }

    }

    private void printInvalid(String prefix){
        event.getChannel().sendMessage("Invalid command: " + prefix).queue();
    }

    public String getRandomInsult(){

        File insultFile = new File(getClass().getClassLoader().getResource("insults.txt").getFile());
        try {
            Scanner s = new Scanner(insultFile);
            while (s.hasNext()) {
                insults.add(s.next());
            }
            s.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

        Random r = new Random();
        int index = r.nextInt((insults.size() - 0) + 1) + 0;
        return insults.get(index);
    }
}
