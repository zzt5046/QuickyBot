package main;

import enums.FileType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FileController {

    private static String rootPath = "savedFiles/";
    private static Logger logger = LoggerFactory.getLogger(FileController.class);
    private static FileController instance = new FileController();

    private FileController(){}

    public static FileController getInstance(){
        return instance;
    }

    //TODO - Get rid of this file shit and use an embedded DB instead

    //All files are saved with the extension .qbf (aka quicky-bot file)
    public static void saveFile(FileType fileType, String user, Object data){
        String filePath = rootPath + fileType.toString() + "/" + user + ".qbf";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            // Write objects to file
            objectOutputStream.writeObject(data);

            objectOutputStream.close();
            fileOutputStream.close();
            logger.info(fileType.name() + "file saved successfully for user: " + user);
        } catch (FileNotFoundException e) {
            logger.error("Error finding file at path: " + filePath);
        } catch (IOException e) {
            logger.error("Error initializing stream");
            e.printStackTrace();
        }
    }

    //Deletes file of a user under a certain category.
    //Not presently used but its here because I like having options
    public static Boolean deleteFile(FileType fileType, String user){
        String filePath = rootPath + fileType.toString() + "/" + user + ".qbf";
        try{
            return new File(filePath).delete();
        }catch(Exception e){
            System.out.println("File not found.");
            return null;
        }
    }

    //Gets a file in the file system directory.
    //FileType determines the type of directory, and user selects the applicable file under the directory
    //Features that use this should know that when retrieving the data, casting is needed.
    public static File getFile(FileType fileType, String user){
        String filePath = rootPath + fileType.toString() + "/" + user + ".qbf";
        try{
            File file = new File(filePath);
            if(file.exists()){
                logger.info("Getting " + fileType.name() + " file for user: " + user + "...");
                return file;
            }
            else{
                return null;
            }
        }catch(Exception e){
            logger.error("Error finding file at path: " + filePath);
            return null;
        }
    }

    //We need to ensure that the directories that will be used are valid and ready to go
    public void init(){
        logger.info("Initializing required directories...");
        try {
            File rootDir = new File(rootPath);
            File quoteDir = new File(rootPath + "quote/");

            if(!rootDir.exists()) {
                rootDir.mkdirs();
                logger.info("Created root dir.");
            }

            if(!quoteDir.exists()) {
                quoteDir.mkdirs();
                logger.info("Created quote dir.");
            }

        }catch(Exception e){
            logger.error("Error initializing file structure;");
            e.printStackTrace();
        }
    }
}
