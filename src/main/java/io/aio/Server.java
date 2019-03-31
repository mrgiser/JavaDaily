package io.aio;

/**
 * 描述:
 * Server
 *
 * @Author he
 * @Create 2019-03-08 5:43 PM
 */
public class Server {
    private static int DEFAULT_PORT = 12345;
    public volatile static long clientCount = 0;
    private static AsyncServerHandler serverHandle;

    public static void start(){
        start(DEFAULT_PORT);
    }
    public static synchronized void start(int port){
        if(serverHandle!=null)
            return;
        serverHandle = new AsyncServerHandler(port);
        new Thread(serverHandle,"Server").start();
    }
    public static void main(String[] args){
        Server.start();
    }
}