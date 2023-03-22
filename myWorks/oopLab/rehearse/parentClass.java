package OOP.oopLab.rehearse;

public class parentClass {
    private double area = 1.0;

    public double area(){
        return this.area;
    }
    
    public static void main(String[] arg) {
        Child myCircle = new Child(5.0);
        Child myCircle2 = new Child(10.0);

        
        
        System.out.println(myCircle.radius);
        System.out.println(myCircle2.radius);
    }
}
