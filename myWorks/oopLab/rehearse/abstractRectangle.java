package OOP.oopLab.rehearse;

public class abstractRectangle extends abstractShape{
    private double length;
    private double width;

    public abstractRectangle(double length, double width){
        super("Circle");
        this.length = length;
        this.width = width;
    }
    public double area(){
        return length * width;
    }
    public static void main(String[] args){
        abstractShape rec = new abstractRectangle(23.0, 4.0);
        System.out.println(rec.area());
    }
    
}
