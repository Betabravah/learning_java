package OOP.oopLab;

public class SharedObject {
    public synchronized void PrintNumbers(int min, int max){
        while(min <= max){
            System.out.println(min);
            min++;
        }
    }
    public static void main(String [] args){
        SharedObject sharedObject = new SharedObject();
        Class1 class1 = new Class1(sharedObject);
        Class2 class2 = new Class2(sharedObject);

        Thread thread1 = new Thread(class1);
        Thread thread2 = new Thread(class2);

        thread1.start();
        thread2.start();
    }
}
class Class1 implements Runnable{
    SharedObject sharedObject;
    public Class1(SharedObject SO){
        this.sharedObject = SO;
    }
    public void run(){
        this.sharedObject.PrintNumbers(0, 5);
    }
}
class Class2 implements Runnable{
    SharedObject sharedObject;
    public Class2(SharedObject SO){
        this.sharedObject = SO;
    }
    public void run(){
        this.sharedObject.PrintNumbers(6, 10);
    }
}