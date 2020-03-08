package com.tools.socket.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class SocketServer {
    public static final Logger logger = LoggerFactory.getLogger(SocketServer.class);

    @Autowired
    SocketService socketService;

    private ServerSocket serverSocket;

    public void start() {
        try {
            serverSocket = new ServerSocket(4434);
            logger.info("Socket服务已启动，占用端口： {}", serverSocket.getLocalPort());

            while (true) {
                Socket socket = serverSocket.accept();
                socketService.handle(socket);
            }
        } catch (Exception e) {
            logger.info("Socket服务启动失败： {}", e.getMessage());
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            serverSocket = null;
        }
    }
}
