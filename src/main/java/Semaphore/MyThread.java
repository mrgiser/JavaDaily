package Semaphore;

import java.util.concurrent.Semaphore;

/**
 * 描述:
 *
 * @Author he
 * @Create 2019-03-01 3:25 PM
 */
public class MyThread extends Thread{
    private int name;
    private Semaphore semaphore;

    MyThread(int name, Semaphore semaphore){
        this.name = name;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {

        try {
            semaphore.acquire();
            System.out.println(name + " is running");
            Thread.sleep(5000);
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}