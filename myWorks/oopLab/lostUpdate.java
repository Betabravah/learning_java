package OOP.oopLab;

public class lostUpdate {
    static int x = 0;
    public static void main(String [] args){
        update obj = new update();
        Thread t = new Thread(obj);
        Thread t2 = new Thread(obj);
        t.start();
        t2.start();
    }
}
    class update implements Runnable{
        public void run(){
            for (int i = 0; i < 5; i++){
                lostUpdate.x -= 1;
                System.out.println(lostUpdate.x);
            }
        }
    }

