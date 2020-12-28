package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

    private static Logger logger = LoggerFactory.getLogger(PropertyLoader.class);
    private static PropertyLoader instance = new PropertyLoader();

    private PropertyLoader(){}

    public static PropertyLoader getInstance(){
        return instance;
    }

    public String getProperty(String key){

        Properties prop = new Properties();
        try {
            //load properties file from class path
            prop.load(getClass().getClassLoader().getResourceAsStream("properties.properties"));
        }
        catch (IOException ex) {
            logger.error("Error when loading properties file...\n");
            ex.printStackTrace();
        }
        logger.info("Looking up property for key: \"" + key + "\"");
        return prop.getProperty(key);
    }
}
