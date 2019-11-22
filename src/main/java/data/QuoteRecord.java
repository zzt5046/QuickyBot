package data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

public class QuoteRecord implements Serializable {

    private String author;
    private int id;
    private HashMap<Date, String> quoteEntry;
    private HashMap<Integer, HashMap<String,String>> quoteBody;

    public QuoteRecord(){
        id = 1;
        quoteBody = new HashMap<>();
        quoteEntry = new HashMap<>();
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){
        return author;
    }

    public void addQuote(String quote){
        HashMap<String,String> entry = new HashMap<>();

        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();

        String dateString = month + "/" + day + "/" + year;

        entry.put(quote, dateString);
        quoteBody.put(id, entry);
        id++;
    }

    public HashMap<Integer, HashMap<String,String>> getQuoteBody(){
        return quoteBody;
    }
}
