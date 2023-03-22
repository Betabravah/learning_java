
public class concatenate_arrays {
    public static void main (String [] args){
        int [] array1 = {1,2,10,11};
        int [] array2 = {0,5,9};
        print_array(concatenate_arrays(array1, array2));
    }

    public static int [] concatenate_arrays (int first_array[], int second_array []){
        int total_length = first_array.length + second_array.length;
        int new_array [] =  new int [total_length];
        for (int i = 0; i < first_array.length; i++){
            new_array [i] = first_array[i];
        }
        for (int i = 0; i < second_array.length; i++){
            new_array [i + first_array.length] = second_array [i];
        }

        return new_array;
    
}

public static void print_array(int array[]){
    System.out.print("{");
    for (int i = 0; i < array.length; i++){
        System.out.printf("%d ,", array[i]);
    }
    System.out.println("}");
}
}
