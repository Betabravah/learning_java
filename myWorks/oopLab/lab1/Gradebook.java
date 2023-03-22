import java.util.Scanner;

public class Gradebook {
    public static void main (String []args){
        double [] grade = new double [4];  
    
        Scanner gradee = new Scanner (System.in);
        System.out.println("Please enter four grades");
        for (int i = 0; i<=3; i++){
            grade [i] = gradee.nextDouble();
        } 
        System.out.printf("Please enter the number of the action you want to perform:\n1. Print out all grades\n2.Find the average grade\n3. Print the grade\n4. find highest grade\n");
        int given_action = gradee.nextInt();
        
        if (given_action == 1){
            printGrades(grade);
        }
        else if (given_action == 2){
            System.out.printf("Averade grade is: %.2f\n", calcAverage(grade));
        }
        else if (given_action == 3){
            printGrade(grade);
        }
        else if (given_action == 4){
            highestGrade(grade);
        }
        else{
            System.out.println("You entered the wrong number.");
        }
        
        search (78.0, grade);
        sort_array (grade);
        printGrades(grade);
        
    }
    
    public static void printGrades(double [] grade) {
        System.out.print("{");
        for (int i = 0; i < grade.length; i++ ){
            System.out.print( grade[i]);
            System.out.print(",");
        }
        System.out.println("}");
    }
    public static double calcAverage (double [] grade){
        double sum = 0;
        for ( double i_grade: grade ){
            sum += i_grade;
        }
        return (sum/grade.length);
    }
    public static void highestGrade (double [] grade){
        double max = 0;
        for (int i = 0; i < grade.length; i++){
            if (max < grade[i]){
                max = grade[i];
            }
        
        }
        System.out.printf("Highest grade is: %s\n", max);

    }
    public static void printGrade (double [] grade){
        for (double i_grade : grade ){
            if (i_grade >= 90){
                System.out.printf("%s = A\n", i_grade);
            }
            else if (i_grade >= 80){
                System.out.printf("%s = B\n", i_grade);
            }
            else if (i_grade >= 70){
                System.out.printf("%s = C\n", i_grade);
            }
            else if (i_grade >= 60){
                System.out.printf("%s = D\n", i_grade);
            }
            else {
                System.out.printf("%s = F\n", i_grade);
            }
        }
        
    }
    public static void search (double data, double [] grade){
        int ctr = 0;
        for (int i = 0; i<grade.length; i++){
            if (grade[i] == data){
                ctr += 1;
            }
        }

        System.out.printf("The data %s you entered is found %s times in the given array \n" , data, ctr);

    }
    public static void sort_array (double [] grade ){
        double temp;
        for (int i = 0; i < (grade.length -1); i++){
            for (int j = 0; j < (grade.length -1); j++){
                if (grade [j] > grade [j+1]){ 
                    temp = grade [j];
                    grade[j] = grade [j+1];
                    grade [j+1] = temp;
                    
                }
           

           }
       }
       System.out.print("Sorted form of the array is: ");
       printGrades(grade);
    }
    
}

    




