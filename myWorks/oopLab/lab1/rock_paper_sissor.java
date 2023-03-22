import java.util.Random;
import java.util.Scanner;

public class rock_paper_sissor {
    public static void main(String [] args){
        Random rand = new Random();
        int random_int = rand.nextInt(3);

        String computer_choice = "";
        if (random_int == 0){
            computer_choice = "rock";
        }
        else if (random_int == 1){
            computer_choice = "paper";
        }
        else{
            computer_choice = "sissor";
        }


        Scanner user = new Scanner(System.in);
        System.out.println("choose from: rock, paper, sissor");
        String user_choice = user.next();
        System.out.println(computer_choice);
        if (computer_choice.equals(user_choice)){
            System.out.println("draw");
        }
        else if ((computer_choice.equals("rock")) && (user_choice.equals("paper"))){
            System.out.println("you won -_-");
        }
        else if ((computer_choice.equals("rock")) && (user_choice.equals("sissor"))){
            System.out.println("i won ^_^");
        }
        else if ((computer_choice.equals("paper")) && (user_choice .equals("sissor"))){
            System.out.println("you won -_-");
        }
        else if ((computer_choice.equals("paper")) && (user_choice.equals("rock"))){
            System.out.println("i won ^_^");
           
        }
        else if ((computer_choice.equals("sissor")) && (user_choice.equals("rock"))){
            System.out.println("you won -_-");
        }
        else if((computer_choice.equals("sissor")) && (user_choice.equals("paper"))) {
            System.out.println("i won ^_^");

    }
}
}