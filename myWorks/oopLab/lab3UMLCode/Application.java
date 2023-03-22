package lab3UMLCode;

public class Application {
    public static void main(String[] args) {
        FinalExam3 oopFinalOne = new FinalExam3(30, 5, 2);
        FinalExam3 oopFinalTwo = new FinalExam3(30, 5, 2);
        FinalExam3 oopFinalThree = new FinalExam3(30, 15, 2);

        System.out.println("Oop final one : " + oopFinalOne.getScore() + " / 60");
        System.out.println("Oop final three : " + oopFinalThree.getScore() + " / 60");

        System.out.println(oopFinalOne.equals(oopFinalTwo));
        System.out.println(oopFinalOne.isLess(oopFinalThree));
        System.out.println(oopFinalOne.isGreater(oopFinalThree));
    }
}
