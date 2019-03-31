package io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 描述:
 * bio服务端
 *
 * @Author he
 * @Create 2019-03-08 2:38 PM
 */
public class BioServer {
    private static int DEFAULT_PORT = 12345;
    private static ServerSocket server;

    public static void start() throws IOException {
        start(DEFAULT_PORT);
    }

    public synchronized static void start(int port) throws IOException{
        if(server != null)
            return;

        try{
            server = new ServerSocket(port);
            System.out.println("bio server satrted at port :" + port);

            while (true){
                Socket socket = server.accept();

                new Thread(new BioServerHandler(socket)).start();
            }
        } finally {
            if(server != null){
                System.out.println("服务器已关闭。");
                server.close();
                server = null;
            }
        }
    }

}