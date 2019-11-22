package commands;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class CalcCommand extends BasicCommand{

    GuildMessageReceivedEvent event;
    String[] inMessage;
    private String numberFormatErrorMessage = "You entered a parameter that is not a number.";

    //constructor handles task delegation based upon the command passed
    public CalcCommand(GuildMessageReceivedEvent event, String command, String[] inMessage){
        this.event = event;
        this.inMessage = inMessage;
        if (command.equals("add")) {
            calcAddition();
        } else if (command.equals("subtract")) {
            calcSubtraction();
        } else if (command.equals("multiply")) {
            calcMultiply();
        } else if (command.equals("divide")) {
            calcDivide();
        }
    }

    //All calc methods are the same; do the certain calculation and print if possible.
    //If there is a number format exception (some asshole fucking with the system) we just
    //print a statement saying its an invalid argument

    private void calcAddition(){
        try {
            Double ret = Double.parseDouble(inMessage[1]) + Double.parseDouble(inMessage[2]);
            printCalcMessage(ret);
        }catch(NumberFormatException e){
            event.getChannel().sendMessage(numberFormatErrorMessage).queue();
        }catch(Exception ohno){
            ohno.printStackTrace();
        }
    }
    private void calcSubtraction(){
        try {
            Double ret = Double.parseDouble(inMessage[1]) - Double.parseDouble(inMessage[2]);
            printCalcMessage(ret);
        }catch(NumberFormatException e){
            event.getChannel().sendMessage(numberFormatErrorMessage).queue();
        }catch(Exception ohno){
            ohno.printStackTrace();
        }
    }
    private void calcMultiply(){
        try {
            Double ret = Double.parseDouble(inMessage[1]) * Double.parseDouble(inMessage[2]);
            printCalcMessage(ret);
        }catch(NumberFormatException e){
            event.getChannel().sendMessage(numberFormatErrorMessage).queue();
        }catch(Exception ohno){
            ohno.printStackTrace();
        }
    }
    private void calcDivide(){
        try {
            Double ret = Double.parseDouble(inMessage[1]) / Double.parseDouble(inMessage[2]);
            printCalcMessage(ret);
        }catch(NumberFormatException e){
            event.getChannel().sendMessage(numberFormatErrorMessage).queue();
        }catch(Exception ohno){
            ohno.printStackTrace();
        }
    }

    //takes results generated above, pulls a generated insults, and prints the completed statement to server
    private void printCalcMessage(double num){
        if(Math.floor(num) == num){
            Integer intConvert = (int) num;
            event.getChannel().sendMessage("It's **" + Integer.toString(intConvert) + "**, you " + getRandomInsult() + ".").queue();
        }
        else{
            event.getChannel().sendMessage("It's **" + Double.toString(num) + "**, you " + getRandomInsult() + ".").queue();
        }
    }
}
