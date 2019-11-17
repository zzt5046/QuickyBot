package commands;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class CalcCommand extends BasicCommand{

    GuildMessageReceivedEvent event;
    private String numberFormatErrorMessage = "You entered a parameter that is not a number.";

    public CalcCommand(GuildMessageReceivedEvent event, String command, String[] inMessage){
        this.event = event;
        if (command.equals("add")) {
            calcAddition(inMessage);
        } else if (command.equals("subtract")) {
            calcSubtraction(inMessage);
        } else if (command.equals("multiply")) {
            calcMultiply(inMessage);
        } else if (command.equals("divide")) {
            calcDivide(inMessage);
        }
    }

    private void calcAddition(String[] message){
        try {
            Double ret = Double.parseDouble(message[1]) + Double.parseDouble(message[2]);
            printCalcMessage(ret);
        }catch(NumberFormatException e){
            event.getChannel().sendMessage(numberFormatErrorMessage).queue();
        }catch(Exception ohno){
            ohno.printStackTrace();
        }
    }
    private void calcSubtraction(String[] message){
        try {
            Double ret = Double.parseDouble(message[1]) - Double.parseDouble(message[2]);
            printCalcMessage(ret);
        }catch(NumberFormatException e){
            event.getChannel().sendMessage(numberFormatErrorMessage).queue();
        }catch(Exception ohno){
            ohno.printStackTrace();
        }
    }
    private void calcMultiply(String[] message){
        try {
            Double ret = Double.parseDouble(message[1]) * Double.parseDouble(message[2]);
            printCalcMessage(ret);
        }catch(NumberFormatException e){
            event.getChannel().sendMessage(numberFormatErrorMessage).queue();
        }catch(Exception ohno){
            ohno.printStackTrace();
        }
    }
    private void calcDivide(String[] message){
        try {
            Double ret = Double.parseDouble(message[1]) / Double.parseDouble(message[2]);
            printCalcMessage(ret);
        }catch(NumberFormatException e){
            event.getChannel().sendMessage(numberFormatErrorMessage).queue();
        }catch(Exception ohno){
            ohno.printStackTrace();
        }
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
}
