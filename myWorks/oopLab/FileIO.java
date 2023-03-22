package OOP.oopLab;
import java.io.*;
import java.util.Scanner;
public class FileIO {
    public static void main (String [] args){
        File file = new File("OOP\\oopLab\\fileIO.java");
        
        try {
            Scanner input = new Scanner(file);
            while(input.hasNext()){
                System.out.println(input.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
}
