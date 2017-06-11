import java.util.concurrent.locks.*;


class Reader implements Runnable{
    public void run(){
        if(RWLock.rwlock.isWriteLocked()){
            System.out.println("write lock present");
        }
        RWLock.rwlock.readLock().lock();
        try{
            Long duration = (long)(Math.random()*10000);
            System.out.println(Thread.currentThread().getName()+" Time taken "+(duration/1000) + " seconds");
            Thread.sleep(duration);
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            System.out.println(Thread.currentThread().getName()+": "+RWLock.message);
        }
    }
}


class WriterA implements Runnable{
    public void run(){
        RWLock.rwlock.writeLock().lock();
        try{
            Long duration = (long)(Math.random()*10000);
            System.out.println(Thread.currentThread().getName()+" Time taken "+(duration/1000) + " seconds");
            Thread.sleep(duration);
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            RWLock.message = RWLock.message.concat("a");
            RWLock.rwlock.writeLock().unlock();
        }
    }
}



class WriterB implements Runnable{
    public void run(){
        RWLock.rwlock.writeLock().lock();
        try{
            Long duration = (long)(Math.random()*10000);
            System.out.println(Thread.currentThread().getName()+" Time taken "+(duration/1000) + " seconds");
            Thread.sleep(duration);
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            RWLock.message = RWLock.message.concat("b");
            RWLock.rwlock.writeLock().unlock();
        }
    }
}



public class RWLock{

    public static final ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    public static String message = "a";

    public static void main(String args[])throws InterruptedException{
        Thread t1 = new Thread(new WriterA());
        t1.setName("writer A");
        Thread t2 = new Thread(new WriterB());
        t2.setName("wirter B");
        Thread t3 = new Thread(new Reader());
        t3.setName("reader");

        t1.start();t2.start();t3.start();
        t1.join();t2.join();t3.join();
    }

}