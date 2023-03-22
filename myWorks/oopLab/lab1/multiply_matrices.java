
public class multiply_matrices {
    public static void main (String []args){

        int [][] array_3 = {{1,2,3,}, {9,0,1}, {1,2,1}, {0,1,2}};
        int [][] array_4 = {{3,0,5,1}, {3,2,6,0}, {8,9,2,1}};
        multiply_matrices (array_3, array_4);

    }

        public static boolean row_uniformity (int array_3 [][], int array_4 [][]){
            for (int i = 0; i < (array_3.length - 1); i++){
                if ((array_3[i].length == array_3[i+1].length ) && (array_4[i].length == array_4[i+1].length)){
                    return true;
                }
            }
            return false;
        }
        
        public static boolean muliplyability (int array_3[][] , int array_4 [][]){
            if (array_3[0].length == array_4.length){
                return true;
            }
            return false;
        }
        
    public static void row_by_column (int array_3[][] , int array_4 [][], int row, int column){
        int x = 0;
        if ((row < array_3.length) && (column < array_4[0].length)){
            for (int i = 0; i < array_3[0].length; i++){   // for each integer in a row of array 1, 
                x +=  (array_3[row ][i] * array_4[i][column]);  // multiply it with respective integer in column of array 2
            }   
        }
        System.out.printf("%d ,", x);
    }
        
    public static void multiply_matrices (int array_3 [][], int array_4[][]){
       if (row_uniformity(array_3, array_4) && muliplyability(array_3, array_4)){
            for (int j = 0; j < array_3.length; j++){       // for a single row in the first array,
                System.out.print("{");
                for (int k = 0; k < array_4[0].length; k++){      // we have multiple columns of the second array
                    row_by_column (array_3, array_4, j , k);      //multiply that single row with all columns of array 2 
                }
                 System.out.println("}");   
            }
    }
            
    
    }
}

