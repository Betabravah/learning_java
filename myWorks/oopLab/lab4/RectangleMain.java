package OOP.oopLab.lab4;

public class RectangleMain {
    public static void main(String [] args){
        Rectangle box1 = new Rectangle();
        Rectangle box2 = new Rectangle();
        box1.setLength(4.5);
        box1.setWidth(11.8);
        box2.setLength(34);
        box2.setWidth(12);
        box1.getLength();
        box1.getWidth();
        box2.getLength();
        box2.getWidth();
        System.out.println(box1.getArea());

    }
    
}
