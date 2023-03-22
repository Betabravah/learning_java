package OOP.oopLab;

public class excpetion {
    public static double divisor(double x, double y) throws ArithmeticException{
        if(y == 0){
            throw new ArithmeticException("Division by Zero");
        }
        return x / y;
    }
    public static void main (String [] args){
        
        System.out.println("s");
        try{
            System.out.println(divisor(3,0));
        }
        catch (ArithmeticException e){
            System.out.println(e.getMessage());
        }
    }
}
// class ZeroDivisionException extends Exception{
//     public ZeroDivisionException (String message){
//         super(message);
//     }

//}