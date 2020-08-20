package main;

import enums.FileType;
import java.io.*;

public class FileController {

    private static String rootPath = "savedFiles/";

    //Serializes a given object into a binary file in a desired location.
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
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
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
                return file;
            }
            else{
                return null;
            }
        }catch(Exception e){
            System.out.println("File not found: " + filePath);
            return null;
        }
    }

    //We need to ensure that the directories that will be used are valid and ready to go
    public static void init(){
        try {
            File rootDir = new File(rootPath);
            File quoteDir = new File(rootPath + "quote/");

            if(!rootDir.exists()) {
                quoteDir.mkdirs();
            }

            if(!quoteDir.exists()) {
                quoteDir.mkdirs();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
