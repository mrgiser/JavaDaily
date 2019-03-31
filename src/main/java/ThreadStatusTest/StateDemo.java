package ThreadStatusTest;

/**
 * 描述:
 * StateDemo
 *
 * @Author he
 * @Create 2019-01-27 4:15 PM
 */
public class StateDemo extends Thread {

    @Override
    public void run() {
        System.out.printf("%-18s %s\n","in run method:", getState());
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new StateDemo();
        System.out.printf("%-18s %s\n","init:",  thread.getState());
        thread.run();
        System.out.printf("%-18s %s\n", "after run:", thread.getState());
        thread.start();
        System.out.printf("%-18s %s\n","after start:", thread.getState());
        Thread.sleep(1);
        System.out.printf("%-18s %s\n","finish:", thread.getState());
    }
}