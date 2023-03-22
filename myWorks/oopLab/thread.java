package OOP.oopLab;

public class thread {
    public static void main(String [] args){
        countdown Cobj = new countdown();
        abc abcObj = new abc();
        Thread t = new Thread(Cobj);
        Thread t2 = new Thread(abcObj);
        t.start();
        t2.start();
    }
}

class countdown implements Runnable{
    public void run(){
        for(int i = 0; i <= 10; i++){
            System.out.println(i);
        }
    }

}
class abc implements Runnable{
    public void run(){
        for(int i = 0; i <= 10; i++){
            System.out.println("Betty");
        }
    }

} 
