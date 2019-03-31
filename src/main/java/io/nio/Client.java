package io.nio;

/**
 * 描述:
 * Client
 *
 * @Author he
 * @Create 2019-03-08 5:00 PM
 */
public class Client {
    private static String DEFAULT_HOST = "127.0.0.1";
    private static int DEFAULT_PORT = 12345;
    private static ClientHandle clientHandle;

    public static void start(){
        start(DEFAULT_HOST,DEFAULT_PORT);
    }

    public static synchronized void start(String ip,int port){
        if(clientHandle != null)
            clientHandle.stop();
        clientHandle = new ClientHandle(ip,port);
        new Thread(clientHandle,"client").start();

    }

    public static boolean sendMsg(String msg) throws Exception {
        if(msg.equals("q")) return false;
        clientHandle.sendMsg(msg);
        return true;
    }

    public static void main(String[] args) {
        start();
    }
}