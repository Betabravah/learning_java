package OOP.oopLab;

public class thrdInterrupt {
    public static void main (String [] args){
        C11 obj = new C11();
        Thread t1 = new Thread(obj);
        Thread t2 = new Thread(obj);

        t1.start();
        t2.start();
        t1.interrupt();
    }
}
class C11 implements Runnable{
    public void run(){
        try {
            Thread.sleep(3000);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("HI");
    }
}