import java.lang.Math;

public class merge_sort {
    public static void main (String [] args){
        int [] array = {51,1,0,4,7};
        print_array(sort(array));
    }
    public static int [] merge (int array_1 [], int array_2 []){
        int total_length = array_1.length + array_2.length;
        int merged_array [] = new int [total_length];
        int i = 0;
        int j = 0;
        while ((array_1.length != 0) && (array_2.length != 0) && (j < total_length)){
            
            if (array_1 [i] <= array_2 [i]){
                merged_array [j] = array_1[i];
                array_1 = item_remove(array_1, i);
            }
            else if (array_1[i] > array_2[i]){
                merged_array [j] = array_2[i];
                array_2 = item_remove(array_2, i);
            }
            j++;
        }
        if (array_1.length == 0){
            for (int k = 0; k < array_2.length; k++){
                merged_array [j + k] = array_2[k];
            }
        }
        else if (array_2.length == 0){
           for (int k = 0; k < array_1.length; k++){
            merged_array [j + k] = array_1[k];
           }
        }
        
        return merged_array;
    }

    public static void print_array(int array[]){
        System.out.print("{");
        for (int i = 0; i < array.length; i++){
            System.out.printf("%d ,", array[i]);
        }
        System.out.println("}");
    }


    public static int [] item_remove(int array [], int index){
        int new_array [] = new int [array.length - 1];
        for (int i = 0; i < array.length; i++){
            if (i < index){
                new_array [i] = array [i];
            }
            else if (i > index){
                new_array [i - 1] = array[i];
            }
        }
        return (new_array);

    }
    public static int[] sort (int array []){
        if (array.length == 1){
            return array;
        }
        else{
            int left_length = Math.floorDiv(array.length, 2);
            int right_length = array.length - left_length;
            int left [] = new int [left_length];
            int right [] = new int [right_length];
            for (int i = 0; i < left_length; i++){
                left[i] = array [i];
            }
            for (int j = 0; j < right_length; j++){
                right[j] = array[j + left_length];
            }
            return merge (sort(left), sort(right)); 
        }
    }
    
}
