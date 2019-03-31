package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述:
 * Condition实现通知
 *
 * @Author he
 * @Create 2019-02-24 9:48 AM
 */
public class UseSingleConditionWaitNotify {

    public static void main(String[] args)  throws InterruptedException{
        MyService service = new MyService();

        MyThread a = new MyThread(service);
        a.start();

        Thread.sleep(3000);

        service.signal();
    }

    static public class MyService{
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        public void await(){
            lock.lock();

            try{
                System.out.println("await 开始时间： " + System.currentTimeMillis());
                condition.await();
                System.out.println("这是condition.await()方法之后的语句，condition.signal()方法之后我才被执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

        public void signal(){
            lock.lock();

            try{
                System.out.println("signal 开始时间： " + System.currentTimeMillis());
                condition.signal();
                Thread.sleep(3000);
                System.out.println("这是condition.signal()方法之后的语句");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

    }

    static public class MyThread extends Thread{
        public MyService service;

        public MyThread(MyService service){
            super();
            this.service = service;
        }

        @Override
        public void run(){
            service.await();
        }

    }

}