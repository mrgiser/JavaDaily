package ThreadStatusTest;

/**
 * 描述:
 * MyThread
 *
 * @Author he
 * @Create 2019-01-27 5:04 PM
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 500000; i++) {
//            System.out.println("i=" + (i + 1));
        }
    }
}