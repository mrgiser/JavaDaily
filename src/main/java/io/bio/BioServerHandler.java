package io.bio;

import io.util.Calculator;

import java.io.*;
import java.net.Socket;

/**
 * 描述:
 * handler
 *
 * @Author he
 * @Create 2019-03-08 2:44 PM
 */
public class BioServerHandler implements Runnable {
    private Socket socket;

    public BioServerHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;

        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);

            String expression;
            String result;
            while (true){
                if((expression = in.readLine()) == null){
                    break;
                }

                System.out.println("bio accept message :" + expression);
                try{
                    result = Calculator.cal(expression).toString();
                } catch (Exception e){
                    result = "计算错误：" + e.getMessage();
                }

                out.println(result);
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            if (in != null){
                try{
                    in.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
                in = null;

            }
            if (out != null){
                out.close();
                out = null;

            }
            if (socket != null){
                try{
                    socket.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
                socket = null;
            }
        }

    }
}