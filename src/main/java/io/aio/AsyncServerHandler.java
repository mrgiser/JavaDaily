package io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * 描述:
 * AsyncServerHandler
 *
 * @Author he
 * @Create 2019-03-08 5:44 PM
 */
public class AsyncServerHandler implements Runnable {
    public CountDownLatch latch;
    public AsynchronousServerSocketChannel channel;

    public AsyncServerHandler(int port){
        try{
            channel =  AsynchronousServerSocketChannel.open();

            channel.bind(new InetSocketAddress(port));
            System.out.println("服务器已启动，端口号：" + port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {

        latch = new CountDownLatch(1);
        channel.accept(this,new AcceptHandler());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}