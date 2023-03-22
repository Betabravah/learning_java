package OOP.oopLab;
import java.io.*;
public class file {
    public static void main(String [] args){
        File file = new File("OOP\\oopLab\\fileIO.java");
        int size = (int)file.length();
        try{
        FileReader fileread= new FileReader(file);
        char array [] = new char [size];
        fileread.read(array);
        fileread.close();
        System.out.println(array);
        } catch (FileNotFoundException e){
             e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
}
    
}

  Betty 