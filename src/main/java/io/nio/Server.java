package io.nio;

/**
 * 描述:
 * Server
 *
 * @Author he
 * @Create 2019-03-08 4:21 PM
 */
public class Server {
    private static int DEFAULT_PORT = 12345;
    private static ServerHandle serverHandle;

    public static void start(){
        start(DEFAULT_PORT);
    }

    public static synchronized void start(int prot){
        if(serverHandle != null)
            serverHandle.stop();

        serverHandle = new ServerHandle(prot);
        new Thread(serverHandle, "server").start();


    }

    public static void main(String[] args) {
        start();
    }

}