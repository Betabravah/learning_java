package lab2;

public class Grad extends Student{
    public Grad(String name, int year){
        super(name, year = 5);
    }
    public String description(){
        return String.format("%s G", name);
    }
}
