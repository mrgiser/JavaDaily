package io.nio;

import java.util.Scanner;

/**
 * 描述:
 *
 * @Author he
 * @Create 2019-03-08 5:28 PM
 */
public class Test {
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception{
        //运行服务器
        Server.start();
        //避免客户端先于服务器启动前执行代码
        Thread.sleep(100);
        //运行客户端
        Client.start();
        while(Client.sendMsg(new Scanner(System.in).nextLine()));
    }

}