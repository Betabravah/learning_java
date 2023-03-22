public class add_matrices{
    public static void main(String [] args){
         int [][] array_1 = {{1,2,3,4}, {5,6,7,8}, {9,0,11,12}};
        int [][] array_2 = {{3,56,3,55}, {12,3,45,6}, {1,8,9,32}};
        add_matrices (array_1, array_2);
    }
    public static void add_matrices(int array_1 [][], int array_2 [][]){
        int [][] sum_array = new int [array_1.length][array_1[0].length];
        if (array_1.length == array_2.length){
            //System.out.print("{");
            for (int i = 0; i < array_1.length; i++ ){
                System.out.print("{");
                if (array_1[i].length == array_2[i].length){
                    for (int j = 0; j < array_1[i].length; j++){
                        sum_array[i][j] = array_1[i][j] + array_2[i][j];
                        System.out.printf("%d, ", sum_array[i][j]);
                    }
                    System.out.println("}");
                }
            }
        }
    }
}