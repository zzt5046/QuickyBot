package events;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BasicCommand extends ListenerAdapter {

    private GuildMessageReceivedEvent event;

    //lol
    private ArrayList<String> insults = new ArrayList<>();

    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        this.event = event;
        String[] inMessage = event.getMessage().getContentRaw().split(" ");
        String prefix = inMessage[0];

        boolean isNotBot = !event.getMessage().getAuthor().isBot();
// for the lols
        if(isNotBot) {
            if (prefix.equalsIgnoreCase("!add")) {
                calcAddition(inMessage);
            } else if (prefix.equalsIgnoreCase("!subtract")) {
                calcSubtraction(inMessage);
            } else if (prefix.equalsIgnoreCase("!multiply")) {
                calcMultiply(inMessage);
            } else if (prefix.equalsIgnoreCase("!divide")) {
                calcDivide(inMessage);
            } else if(prefix.startsWith("!")){
                printInvalid();
            }
        }

    }

    private void calcAddition(String[] message){
        Double ret = Double.parseDouble(message[1]) + Double.parseDouble(message[2]);
        printCalcMessage(ret);
    }
    private void calcSubtraction(String[] message){
        Double ret = Double.parseDouble(message[1]) - Double.parseDouble(message[2]);
        printCalcMessage(ret);
    }
    private void calcMultiply(String[] message){
        Double ret = Double.parseDouble(message[1]) * Double.parseDouble(message[2]);
        printCalcMessage(ret);
    }
    private void calcDivide(String[] message){
        Double ret = Double.parseDouble(message[1]) / Double.parseDouble(message[2]);
        printCalcMessage(ret);
    }

    private void printCalcMessage(double num){
        if(Math.floor(num) == num){
            Integer intConvert = (int) num;
            event.getChannel().sendMessage("It's " + Integer.toString(intConvert) + ", you " + getRandomInsult() + ".").queue();
        }
        else{
            event.getChannel().sendMessage("It's " + Double.toString(num) + ", you " + getRandomInsult() + ".").queue();
        }
    }
    private void printInvalid(){
        event.getChannel().sendMessage("Invalid command! Format is: !{operation} {num1} {num2}").queue();
    }

    private String getRandomInsult(){

        File insultFile = new File(getClass().getClassLoader().getResource("insults.txt").getFile());
        try {
            Scanner s = new Scanner(insultFile);
            ArrayList<String> list = new ArrayList<>();
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
