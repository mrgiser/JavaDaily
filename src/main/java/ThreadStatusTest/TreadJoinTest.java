package ThreadStatusTest;

/**
 * 描述:
 * thread.join 测试
 *
 * @Author he
 * @Create 2019-01-21 4:42 PM
 */
public class TreadJoinTest {
    public static void main(String[] args) {

        Thread2 thread2 = new Thread2();
        Thread1 thread1 = new Thread1(thread2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}


class Thread1 extends Thread {
    public Thread2 thread2;

    Thread1(Thread2 thread2) {
        this.thread2 = thread2;
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread 1 : print " + i);

                thread2.join(3000);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class Thread2 extends Thread {

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread 2 : print " + i);

                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}