package Callable;

import java.lang.annotation.Inherited;
import java.util.concurrent.*;

/**
 * 描述:
 *
 * @Author he
 * @Create 2019-03-02 4:06 PM
 */
public class CallableDemo implements Callable<Integer> {

    public static void main(String[] args) throws Exception{
        CallableDemo demo = new CallableDemo();

        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Integer> result =  service.submit(demo);
        System.out.println("result = " + result.get());
    }

    @Override
    public Integer call() throws Exception {

        System.out.println("call start");
        Thread.sleep(1000);
        System.out.println("call end");
        return 1;
    }
}