package com.example.demo.test.day2.nio;

import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioSelectorServer {

    public static void main(String[] args) throws IOException {

        testNio();
    }

    public static void testNio() throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(9000));
        // 设置ServerSocketChannel为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 打开Selector处理Channel，即创建epoll
        Selector selector = Selector.open();
        // 把服务端ServerSocketChannel注册到Selector上，并且监听服务端连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务启动成功");

        while (true) {
            // 阻塞
            selector.select();

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            if (!CollectionUtils.isEmpty(selectionKeys)) {
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) key.channel();
                        SocketChannel clientSocketChannel = serverSocketChannel1.accept();
                        clientSocketChannel.configureBlocking(false);
                        clientSocketChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println("客户端连接成功");
                    } else if (key.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(12);
                        int read = socketChannel.read(byteBuffer);
                        if (read > 0) {
                            System.out.println("服务端：客户端发送的消息为：" + new String(byteBuffer.array()));
                        } else if (read == -1) {
                            System.out.println("客户端断开连接");
                            socketChannel.close();
                        }
                    }
                    iterator.remove();
                }
            }
        }
    }
}
