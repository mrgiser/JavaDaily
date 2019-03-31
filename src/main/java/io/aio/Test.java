package io.aio;

import java.util.Scanner;

/**
 * 描述:
 * Test
 *
 * @Author he
 * @Create 2019-03-08 6:29 PM
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
        System.out.println("请输入请求消息：");
        Scanner scanner = new Scanner(System.in);
        while(Client.sendMsg(scanner.nextLine()));
    }
}