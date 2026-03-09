package com.example.demo.test.day2.nio;

import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NioServer {

    // 保存客户端连接
    static List<SocketChannel> channelList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        testNio();
    }

    public static void testNio() throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(9000));
        // 设置ServerSocketChannel为非阻塞
        serverSocketChannel.configureBlocking(false);
        System.out.println("服务启动成功");

        while (true) {
            SocketChannel clientSocketChannel = serverSocketChannel.accept();
            if (clientSocketChannel != null) {
                System.out.println("有客户端连接了");
                // 设置ServerSocketChannel为非阻塞
                clientSocketChannel.configureBlocking(false);
                // 保存客户端连接集合
                channelList.add(clientSocketChannel);
            }

            if (!CollectionUtils.isEmpty(channelList)) {
                Iterator<SocketChannel> iterator = channelList.iterator();
                while (iterator.hasNext()) {
                    SocketChannel socketChannel = iterator.next();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(12);
                    System.out.println("服务端：准备read客户端发送的消息");
                    int read = socketChannel.read(byteBuffer);
                    if (read > 0) {
                        System.out.println("服务端：客户端发送的消息为：" + new String(byteBuffer.array()));
                    } else if (read == -1) {
                        iterator.remove();
                        System.out.println("客户端断开连接");
                    }
                }
            }
        }
    }
}
