package com.tools.socket.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@Service
public class SocketService {
    public static final Logger logger = LoggerFactory.getLogger(SocketService.class);

    @Async
    public void handle(Socket socket) throws Exception {
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        try {
            byte[] bytes = new byte[1024];
            int length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while ((length = inputStream.read(bytes)) != -1) {
                byteArrayOutputStream.write(bytes, 0, length);//将b中存放的字节写到bos中
                byteArrayOutputStream.flush();
                //如果不判断break,会一直读取,导致客户端一直等待状态
                if (length < bytes.length) {
                    break;
                }
            }
            byte[] data = byteArrayOutputStream.toByteArray();
            String msg = new String(data);
            logger.info(msg);
            logger.info("线程名称：" + Thread.currentThread().getName());
            outputStream.write("服务端返回数据".getBytes());
            outputStream.flush();
        } finally {
            inputStream.close();
            outputStream.close();
            socket.close();
        }
    }
}
