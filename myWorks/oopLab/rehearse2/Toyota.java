package OOP.oopLab.rehearse2;

public class Toyota extends AbstractCar {
    private int year;
    private String Model;
    private int currentYear;

    public Toyota(String name, int year){
        super(name);
        this.year = year;
    }
    public void setModel(String Model){
        this.Model = Model;
    }
    public String getModel(){
        return Model;
    }
    public int getYear(){
        return year;
    }
    
    public boolean is_new(){
        if(currentYear - year <= 5){
            return true;
        }
        return false;
    }

    
}
