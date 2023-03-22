package lab2;

public class Intern extends Undergrad{
    int wage;
    int hours;
    public Intern(String name, int year, int wage, int hours){
        super(name, year);
    }
    public int getPay(){
        return wage * hours;
    }
    public String description(){
        return String.format("%s%s", super.description(), getPay());
    }
}
