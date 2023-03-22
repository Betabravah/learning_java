package OOP.oopLab.rehearse2;

public class AnimalSubClass extends abstractAnimal {
    private int age;
    private String name;
    
    public AnimalSubClass(String name, int age){
        super(name);
        this.age = age;
        this.name = name;
    }
    public boolean is_mammal(){
        if (name == "Bird"){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean is_domestic(){
        if(name == "Lion"){
            return false;
        }
        return true;
    }
}
