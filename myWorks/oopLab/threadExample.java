package OOP.oopLab;

public class threadExample {
    public static void main (String [] args){
        C1 obj1 = new C1();
        C2 obj2 = new C2();
        Thread t1 = new Thread (obj1);
        Thread t2 = new Thread (obj2);

        t1.start();
        t2.start();
        try{
            t1.sleep(3000);
            System.out.println("After Sleep");
            t2.sleep(3000);
            System.out.println("After Sleep");
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
}

class C1 implements Runnable{
    public void run(){
        for(int i = 0; i <= 10; i++){
            System.out.println(i);
        }
    }
}

class C2 extends Thread{
    public void run(){
        for(int i = 0; i <= 5; i++){
            System.out.println("Betty");
        }
    }
}