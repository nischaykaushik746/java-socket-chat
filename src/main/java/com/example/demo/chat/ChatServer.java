package com.example.demo.chat;

import java.net.ServerSocket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServer {

    static Map<String, ClientHandler> users = new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(4000);
        System.out.println("Chat server started on 4000");

        while (true) {
            new Thread(new ClientHandler(server.accept())).start();
        }
    }
}

