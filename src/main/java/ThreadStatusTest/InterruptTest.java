package ThreadStatusTest;

/**
 * 描述:
 * interrupt
 *
 * @Author he
 * @Create 2019-01-27 5:02 PM
 */
public class InterruptTest {

    public static void main(String[] args) {
            MyThread thread = new MyThread();
            thread.start();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread.interrupt();
            //Thread.currentThread().interrupt();
            System.out.println("是否停止1？=" + thread.isInterrupted());//测试的是thread对象所代表的线程的中断状态
            System.out.println("是否停止2？=" + thread.interrupted());//测试的是当前的线程的中断状态
//        thread.
        return;
    }
}