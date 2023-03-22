package OOP.oopLab;

public class IndexException {
    
    public static void main (String [] args){
      int array []  = new int [5];
      try{
        System.out.println(3/0);
        System.out.println(array[6]);
      
      }
      catch(ArrayIndexOutOfBoundsException e){
        System.out.println(e.getMessage());
        e.printStackTrace();
      }
      catch(ArithmeticException e){
        e.printStackTrace();
      }
      finally{
        System.out.println("Finally block");
      }
    }

}