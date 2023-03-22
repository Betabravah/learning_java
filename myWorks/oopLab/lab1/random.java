import java.util.Random;
public class random{
public static void main (String [] args){
    Random rand = new Random();
    for (int i = 0; i < 100; i++){
    int random_int = rand.nextInt(100);
    System.out.println(random_int);
    }
}
}