package OOP.oopLab;
import java.io.*;
public class writeFile {
    public static void main(String [] args){
        try{
        File file = new File("OOP\\oopLab\\write.java");
        FileWriter fileWrite = new FileWriter(file, true);
        fileWrite.write("Betty");
        fileWrite.append(" Yemane");
        fileWrite.close();
        FileWriter file2Write = new FileWriter(file, true);
        file2Write.append(" Mamo");
        file2Write.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

}
