package ThreadStatusTest;

import java.util.concurrent.TimeUnit;

/**
 * 描述:
 * DaemonDemo
 *
 * @Author he
 * @Create 2019-01-27 4:26 PM
 */
public class DaemonDemo {
    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("run finally in daemon thread.");
                }
            }
        });

//        thread.setDaemon(true);
        thread.start();
    }
}
