package OOP.oopLab;

public class exceptionn {
    static String name;
    public static void main(String[]args) throws newException{
        if (name == "Betty"){
            throw new newException("Name already taken");
        }
    }
    public static void divisor(int x, int y){
        try{
            x = x / y;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
 class newException extends Exception{
    public newException(String message){
        super(message);
    }

 }
