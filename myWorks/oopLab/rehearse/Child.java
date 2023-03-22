package OOP.oopLab.rehearse;

public class Child extends parentClass {
    public double radius;
    
    public Child(double radius){
        this.radius = radius;
    }

    public double area(){
        return (Math.PI * radius * radius);
    }
    
}

