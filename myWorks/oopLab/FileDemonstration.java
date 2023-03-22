package OOP.oopLab;
import java.io.File;
import java.util.Scanner;
public class FileDemonstration{
    public static void analyzePath(String path){
        File fileName = new File(path);
        if (fileName.exists() && fileName.isFile()){
            System.out.printf("%s%S\n", fileName.getName(), "exists");
            System.out.printf("%s%S\n", fileName.getPath(), "- path");
            System.out.printf("%s%S\n", fileName.getParent(), "- parent directory");
            System.out.printf("%s%S\n", fileName.getAbsolutePath(), "- Absolute Path");
            System.out.printf("%s%S\n", fileName.isFile(), "- is file");
            System.out.printf("%s%S\n", fileName.isDirectory(), "- is Directory");
            System.out.printf("%s%S\n", fileName.length(), "size");
        }
        if (fileName.isDirectory()){
            String Files [] = fileName.list();
            System.out.print("The files in this directory are: ");
            for (String files: Files){
                System.out.printf("%s ,", files);
            }
        }
    }
    public static void main(String [] args){
        System.out.println("Enter the path of the file you want analysis of");
        Scanner input = new Scanner(System.in);
        String path = input.next();
        File fileName = new File(path);
    }
}