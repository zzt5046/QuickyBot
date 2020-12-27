package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class QuickyBotUtils {

    private QuickyBotUtils(){}

    public static String getRandomInsult(){

        ArrayList<String> insults = new ArrayList<>();

        File insultFile = new File(QuickyBotUtils.class.getClassLoader().getResource("insults.txt").getFile());
        try {
            Scanner s = new Scanner(insultFile);
            while (s.hasNext()) {
                //the asterisks format the text in-app to be bold/italics. for the drama, you know?
                insults.add(italicize(s.next()));
            }
            s.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

        Random r = new Random();
        int index = r.nextInt((insults.size() - 0) + 1) + 0;
        return insults.get(index);
    }

    public static String getRandomStatus(){

        ArrayList<String> statuses = new ArrayList<>();

        File insultFile = new File(QuickyBotUtils.class.getClassLoader().getResource("insults.txt").getFile());
        try {
            Scanner s = new Scanner(insultFile);
            while (s.hasNext()) {
                //the asterisks format the text in-app to be bold/italics. for the drama, you know?
                statuses.add(s.next());
            }
            s.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

        Random r = new Random();
        int index = r.nextInt((statuses.size() - 0) + 1) + 0;
        return statuses.get(index);
    }

    public static String bold(String in){
        return "**"+in+"**";
    }

    public static String italicize(String in){
        return "***"+in+"***";
    }
}
