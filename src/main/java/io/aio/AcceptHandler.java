package io.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * 描述:
 * AcceptHandler
 *
 * @Author he
 * @Create 2019-03-08 5:44 PM
 */
public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel,AsyncServerHandler>  {

    @Override
    public void completed(AsynchronousSocketChannel channel, AsyncServerHandler serverHandler) {
        Server.clientCount++;
        System.out.println("连接的客户端数：" + Server.clientCount);

        //继续接受其他客户端的请求
        serverHandler.channel.accept(serverHandler, this);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        channel.read(buffer, buffer, new ReadHandler(channel));

    }

    @Override
    public void failed(Throwable exc, AsyncServerHandler serverHandler) {
        exc.printStackTrace();
        serverHandler.latch.countDown();

    }
}