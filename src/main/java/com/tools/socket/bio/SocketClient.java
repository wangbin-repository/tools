package com.tools.socket.bio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClient {
    final static String ADDRESS = "localhost";
    final static int PORT = 4434;

    public static void main(String[] args) {
        Socket socket = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(ADDRESS, PORT));
            outputStream = socket.getOutputStream();
            outputStream.write("客户端发送请求客户端发送请求客户端发送请求客户端发送请求客户端发送请求客户端发送请求客户端发送请求客户端发送请求客户端发送请求客户端发送请求客户端发送请求".getBytes());
            outputStream.flush();

            inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            int length;
            while ((length = inputStream.read(bytes)) != -1) {
                byteArray.write(bytes, 0, length);
                byteArray.flush();
            }

            byte[] data = byteArray.toByteArray();
            String res = new String(data);
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                inputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
