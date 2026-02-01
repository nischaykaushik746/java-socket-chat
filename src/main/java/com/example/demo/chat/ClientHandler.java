package com.example.demo.chat;


import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String username;
    private long lastActivity;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.lastActivity = System.currentTimeMillis();
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            handleLogin();
            handleMessages();

        } catch (IOException e) {
            // Client crashed or disconnected
        } finally {
            cleanup();
        }
    }

    private void handleLogin() throws IOException {
        while (true) {
            String line = in.readLine();
            if (line == null) throw new IOException();

            line = line.trim();
            if (!line.startsWith("LOGIN ")) {
                out.println("ERR invalid-login");
                continue;
            }

            String name = line.substring(6).trim();
            if (name.isEmpty() || ChatServer.users.containsKey(name)) {
                out.println("ERR username-taken");
            } else {
                username = name;
                ChatServer.users.put(username, this);
                out.println("OK");
                broadcast("INFO " + username + " joined");
                break;
            }
        }
    }

    private void handleMessages() throws IOException {
        String line;
        while ((line = in.readLine()) != null) {

            if (line.startsWith("MSG ")) {
                broadcast("MSG " + username + " " + line.substring(4).trim());
            }
            else if (line.equals("WHO")) {
                ChatServer.users.keySet()
                        .forEach(u -> out.println("USER " + u));
            }
            else if (line.startsWith("DM ")) {
                String[] p = line.split(" ", 3);
                ClientHandler t = ChatServer.users.get(p[1]);
                if (t != null) t.out.println("DM " + username + " " + p[2]);
            }
            else if (line.equals("PING")) {
                out.println("PONG");
            }
        }
    }


    private void handleDM(String line) {
        String[] parts = line.split(" ", 3);
        if (parts.length < 3) return;
        ClientHandler target = ChatServer.users.get(parts[1]);

        if (target != null) {
            target.out.println("DM " + username + " " + parts[2]);
        }
    }

    private void broadcast(String message) {
        ChatServer.users.values()
                .forEach(client -> client.out.println(message));
    }

    private void cleanup() {
        if (username != null) {
            ChatServer.users.remove(username);
            broadcast("INFO " + username + " disconnected");
        }
        try { socket.close(); } catch (IOException ignored) {}
    }
}


