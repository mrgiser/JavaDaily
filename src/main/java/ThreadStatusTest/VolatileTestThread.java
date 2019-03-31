package ThreadStatusTest;

/**
 * 描述:
 * volatile
 *
 * @Author he
 * @Create 2019-02-21 4:28 PM
 */
public class VolatileTestThread extends Thread {
    volatile public static int count;

    private static void addCount() {
        for (int i = 0; i < 100; i++) {
            count=i;
        }
        System.out.println("count=" + count);

    }
    @Override
    public void run() {
        addCount();
    }


    public static void main(String[] args) {
        VolatileTestThread[] mythreadArray = new VolatileTestThread[100];
        for (int i = 0; i < 100; i++) {
            mythreadArray[i] = new VolatileTestThread();
        }

        for (int i = 0; i < 100; i++) {
            mythreadArray[i].start();
        }
    }

}