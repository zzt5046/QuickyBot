package main;

import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

    public PropertyLoader(){
    }

    public String getProperty(String key){

        Properties prop = new Properties();
        try {
            //load a properties file from class path, inside static method
            prop.load(getClass().getClassLoader().getResourceAsStream("properties.properties"));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop.getProperty(key);
    }
}
