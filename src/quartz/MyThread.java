package quartz;

class MyThread1 implements Runnable{
    private int ticket=5;
    public void run(){
        //for (int i = 0; i < 100; i++) {
            this.sale();
        //}
    }
    public synchronized void sale(){
        if(ticket>=0){
            try{
                Thread.sleep(600);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(ticket--);
        }

    }
};

public class MyThread{
    public static void main(String[] args){
        MyThread1 mt=new MyThread1();
        Thread t1=new Thread(mt);
        Thread t2=new Thread(mt);
        Thread t3=new Thread(mt);
        Thread t4=new Thread(mt);
        Thread t5=new Thread(mt);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}