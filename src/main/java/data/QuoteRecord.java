package data;

import java.util.HashMap;

public class QuoteRecord{

    private String author;
    private HashMap<Integer,QuoteEntry> quoteLog;

    private int id;

    public QuoteRecord(String author){
        this.author = author;
        quoteLog = new HashMap<>();
        id = 0;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){
        return author;
    }

    public HashMap<Integer,QuoteEntry> getQuoteLog(){
        return quoteLog;
    }

    public void addQuote(String quote){
        quoteLog.put(id, new QuoteEntry(quote));
        id++;
    }

    public void deleteQuote(int id){
        quoteLog.remove(id);
    }

    public void clearAllQuotes(){
        quoteLog.clear();
        id = 0;
    }
}
