package lab2;

public class ReaserchAssistant extends Grad {
    int salary;
    public ReaserchAssistant(String name, int year, int salary){
        super(name, year);
        this.salary = salary;
    }
    public int getPay(){
        return salary;
    }
}
