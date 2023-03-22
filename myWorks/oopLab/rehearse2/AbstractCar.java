package OOP.oopLab.rehearse2;

public abstract class AbstractCar {
    private String name;

    public AbstractCar(String name){
        this.name = name;
    }
    public abstract String getModel();
    public abstract int getYear();
    // public abstract boolean is_new();
    //public abstract void setModel();


    public String getName(){
        return name;
    }
}
