package ThreadStatusTest;

import java.util.concurrent.TimeUnit;

/**
 * 描述:
 * InterruptDemo
 *
 * @Author he
 * @Create 2019-01-27 4:37 PM
 */
public class InterruptDemo {
    public static void main(String[] args) {
        Thread demo = new Thread(new Runnable() {
            @Override
            public void run() {
                int threshold = 10000;
                int index = 1;
                //不会重置当前中断标志
                while (!Thread.currentThread().isInterrupted()) {

                }
                //会重置当前中断标志
//                while (!Thread.interrupted()) {
//
//                }

                System.out.println("4: " + Thread.currentThread().isInterrupted());


            }
        });

        demo.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        demo.interrupt();

        System.out.println("1: " + demo.isInterrupted());
        System.out.println("2: " +demo.interrupted());
        System.out.println("3: " +demo.isInterrupted());
    }
}