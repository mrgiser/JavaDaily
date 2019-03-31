package Semaphore;

import java.util.concurrent.Semaphore;

/**
 * 描述:
 * Semaphore
 *
 * @Author he
 * @Create 2019-03-01 3:11 PM
 */
public class SemaphoreTest {
    public static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) throws Exception{
        MyThread myRunable1 = new MyThread(1, semaphore);
        MyThread myRunable2 = new MyThread(2, semaphore);

        myRunable1.start();
        myRunable2.start();

        myRunable2.join();
    }



}