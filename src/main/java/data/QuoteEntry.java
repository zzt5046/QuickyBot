package data;

import java.util.Date;

public class QuoteEntry {

    private Date date;
    private String quote;

    public QuoteEntry(String quote){
        date = new Date();
        this.quote = quote;
    }

    public Date getDate() {
        return date;
    }

    public String getQuote() {
        return quote;
    }
}
