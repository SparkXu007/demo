package com.example.demo.test.day2.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO: 同步阻塞
 *   IO即input和output，也就是输入输出，一般就是客户端client和服务端server之间通信。
 *   而因为在BIO模型中，在服务端是使用accept方法获取客户端连接，而accept方法是阻塞的，所以线程会一直等待，直到获取到客户端连接。
 *   当服务端获取到连接后，会使用read方法读取客户端发送的消息，而read方法也是阻塞的，所以线程也会一直等待，直到获取到客户端发送的消息。
 * 问题：当同时有1W个客户端向服务端发送建立连接时，如果IO模型是BIO，则只有1个客户端能和服务端建立连接，其他9999个客户端必须等待服务端接收到该客户端的消息，
 *   并处理完成后才能建立连接，并且每次只能有1个客户端获取到连接。-- 这种就相当于每次只能处理一个客户端的消息，其他客户端必须等待，吞吐量极低
 *
 * 方案1：将处理客户端消息放入异步线程中，这样就可以同时建立多个连接。
 * 方案1的问题：当同时有1W个客户端向服务端发送建立连接时，则需要1W个线程，资源消耗大。
 *
 *
 *
 */
public class BioServer {

    public static void main(String[] args) throws IOException {

        testBio();
    }

    public static void testBio() throws IOException {

        // 启动服务端，监听9000端口
        ServerSocket serverSocket = new ServerSocket(9000);
        while (true) {
            // 阻塞方法 接收客服端连接
            Socket clientSocket = serverSocket.accept();
            System.out.println("服务端：有客户端连接了");
            // 读取并处理接收到的客户端的信息
            handle(clientSocket);
        }
    }

    public static void handle(Socket clientSocket) throws IOException {
        byte[] bytes = new byte[1024];
        System.out.println("服务端：准备read客户端发送的消息");
        // 阻塞方法
        int read = clientSocket.getInputStream().read(bytes);
        if (read != -1) {
            System.out.println("服务端：客户端发送的消息为：" + new String(bytes, 0, read));
        }
        System.out.println("end");
    }
}
