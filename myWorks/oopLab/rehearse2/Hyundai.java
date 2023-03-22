package OOP.oopLab.rehearse2;

public class Hyundai  extends AbstractCar{
    private String name;
    private String Model;
    private int year;

    public Hyundai(String name, String Model){
        super(name);
        this.name = name;
        this.Model = Model;
    }

    public String getModel(){
        return Model;
    }
    public int getYear(){
        return year;
    }
    
}
