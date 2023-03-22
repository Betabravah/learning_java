package OOP.oopLab.rehearse;

public class circle extends shape{
    private double radius;

    public circle(Double radius){
        this.radius = radius;
    }
    
    public double area(){
        return(Math.PI * radius * radius);
    }
}
