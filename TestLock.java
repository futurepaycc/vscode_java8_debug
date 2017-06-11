import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 相当于linux线程下的互斥量
 */
class PrintDemo {
    private final Lock queueLock = new ReentrantLock();

    public void print() {
        queueLock.lock();
        try {
            Long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + "Time Taken " + (duration / 1000) + "seconds.");
            Thread.sleep(duration);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            System.out.printf("%s printed the document successfully.\n",Thread.currentThread().getName());
            queueLock.unlock();
        }
    }
}

class ThreadDemo extends Thread{
    PrintDemo printDemo;
    ThreadDemo(String name,PrintDemo printDemo){
        super(name);
        this.printDemo = printDemo;
    }

    @Override
    public void run(){
        System.out.printf("%s starts printing document\n",Thread.currentThread().getName());
        printDemo.print();
    }
}

public class TestLock{
    public static void main(String[] args) {
        PrintDemo pd = new PrintDemo();
        
        ThreadDemo t1 = new ThreadDemo("Thread - 1", pd);
        ThreadDemo t2 = new ThreadDemo("Thread - 2", pd);
        ThreadDemo t3 = new ThreadDemo("Thread - 3", pd);
        ThreadDemo t4 = new ThreadDemo("Thread - 4", pd);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}