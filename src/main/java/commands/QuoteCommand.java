package commands;

import data.QuoteRecord;
import enums.FileType;
import main.FileController;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import javax.imageio.IIOException;
import java.io.*;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuoteCommand extends BasicCommand{

    GuildMessageReceivedEvent event;
    String inMessage;
    String author;

    File userFile = null;

    public QuoteCommand(GuildMessageReceivedEvent event, String command, String inMessage){
        this.event = event;
        this.inMessage = inMessage;

        String[] splitMessage = inMessage.split(" ");
        author = splitMessage[splitMessage.length-1].toLowerCase();
        author = author.substring(0, 1).toUpperCase() + author.substring(1);
        getQuoteFile(author);

        if (command.equals("quote")) {
            storeQuote(inMessage);
        }
        else if (command.equals("get_quote")) {
            getQuote();
        }
        else if (command.equals("get_quotes")) {
            getQuotes();
        }
        else if (command.equals("delete_quote")) {
            deleteQuotes();
        }
    }

    private void storeQuote(String inMessage){
        String quote = "";
        Pattern p = Pattern.compile("\"([^\"]*)\"");
        Matcher m = p.matcher(inMessage);
        while (m.find()) {
            quote = m.group(1);
        }

        String[] splitMessage = inMessage.split(" ");
        String author = splitMessage[splitMessage.length-1].toLowerCase();

        QuoteRecord record = readQuoteFile();

        if(record != null){
            record.addQuote(quote);
        }
        else{
            record = new QuoteRecord();
        }

        FileController.saveFile(FileType.quote, author, record);
        event.getChannel().sendMessage("Quote saved for: **" + author + "**").queue();
    }

    private void getQuote(){
        QuoteRecord record = readQuoteFile();

        Random r = new Random();
        int max = record.getQuoteBody().size();
        int index = r.nextInt(((max) - 1) + 1) + 1;

        HashMap<String,String> quoteEntry = record.getQuoteBody().get(index);
        String quote = (String) quoteEntry.keySet().toArray()[0];
        String date = quoteEntry.get(quote);

        event.getChannel().sendMessage("On **" + date + "**, **" + author + "** said \"**" + quote + "**\"").queue();
    }

    private void getQuotes(){
        event.getChannel().sendMessage("Command coming soon.").queue();
    }

    private void deleteQuotes(){
        event.getChannel().sendMessage("Command coming soon.").queue();
    }

    private QuoteRecord readQuoteFile(){
        QuoteRecord record = null;
        try {
            FileInputStream fi = new FileInputStream(userFile);
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            record = (QuoteRecord) oi.readObject();

            oi.close();
            fi.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + userFile.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return record;
    }

    private void getQuoteFile(String author){
        userFile = FileController.getFile(FileType.quote, author);
        if(userFile == null){
            FileController.saveFile(FileType.quote, author, new QuoteRecord());
            userFile = FileController.getFile(FileType.quote, author);
        }
    }
}
