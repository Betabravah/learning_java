public class split_array {


    public static int [] split (int array []){
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
        return right;
    }
    
}
