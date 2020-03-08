package com.tools;

import com.tools.socket.bio.SocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class ToolsApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ToolsApplication.class, args);
        context.getBean(SocketServer.class).start();
    }

}
