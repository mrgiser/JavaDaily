package io.nio;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 描述:
 * ClientHandle
 *
 * @Author he
 * @Create 2019-03-08 5:00 PM
 */
public class ClientHandle implements Runnable {
    private String host;
    private int port;

    private volatile boolean started;
    private Selector selector;
    private SocketChannel socketChannel;

    public ClientHandle(String ip,int port) {
        this.host = ip;
        this.port = port;

        try{
            selector = Selector.open();
            socketChannel = SocketChannel.open();

            socketChannel.configureBlocking(false);
            started =true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try{
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (started){
            try{
                selector.select(1000);

                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();

                SelectionKey key = null;

                while (it.hasNext()){
                    key = it.next();
                    it.remove();

                    try{
                        handleInput(key);
                    }catch (Exception e){
                        if(key != null){
                            key.cancel();
                            if(key.channel() != null){
                                key.channel().close();
                            }
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        if (selector != null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void stop(){
        started = false;
    }

    private void handleInput(SelectionKey key) throws IOException{
        if (key.isValid()){
            SocketChannel sc = (SocketChannel) key.channel();

            if (key.isConnectable()){
                if (sc.finishConnect());
                else System.exit(1);
            }


            if (key.isReadable()){
                ByteBuffer buffer = ByteBuffer.allocate(1024);

                int readBytes = sc.read(buffer);

                if(readBytes > 0 ){
                    buffer.flip();
                    //根据缓冲区可读字节数创建字节数组
                    byte[] bytes = new byte[buffer.remaining()];
                    //将缓冲区可读字节数组复制到新建的数组中
                    buffer.get(bytes);
                    String result = new String(bytes,"UTF-8");
                    System.out.println("客户端收到消息：" + result);

                } else if(readBytes <0 ){
                    key.cancel();
                    sc.close();
                }
            }
        }

    }

    private void doWrite(SocketChannel channel,String request) throws IOException{
        //将消息编码为字节数组
        byte[] bytes = request.getBytes();
        //根据数组容量创建ByteBuffer
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        //将字节数组复制到缓冲区
        writeBuffer.put(bytes);
        //flip操作
        writeBuffer.flip();
        //发送缓冲区的字节数组
        channel.write(writeBuffer);
        //****此处不含处理“写半包”的代码
    }

    private void doConnect() throws IOException{
        if(socketChannel.connect(new InetSocketAddress(host,port)));

        else socketChannel.register(selector, SelectionKey.OP_CONNECT);
    }
    public void sendMsg(String msg) throws Exception{
        socketChannel.register(selector, SelectionKey.OP_READ);
        doWrite(socketChannel, msg);
    }
}