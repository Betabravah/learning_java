package OOP.oopLab;
import java.io.*;
public class filee{
    public static void main(String [] args){
        File file = new File("OOP\\oopLab\\file.java");
        System.out.println(file.isAbsolute());
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());
        System.out.println(file.exists());
        

        try{
            int size = (int)file.length();
            FileReader fileRead = new FileReader(file);
            FileWriter fileWrite = new FileWriter(file, true);
            char array [] = new char [size];
            fileRead.read(array);
            fileWrite.write(" Betty ");
            fileRead.close();
            fileWrite.close();
            System.out.println(array);
        } catch(Exception e){
            System.err.print(e.getMessage());
        }
    }
}