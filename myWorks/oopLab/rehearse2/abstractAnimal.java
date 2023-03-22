package OOP.oopLab.rehearse2;
public abstract class abstractAnimal{
    private String name;

    public abstractAnimal(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public abstract boolean is_mammal();
    public abstract boolean is_domestic();
}