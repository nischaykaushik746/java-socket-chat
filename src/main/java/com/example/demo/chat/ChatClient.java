package com.example.demo.chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 4000);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        new Thread(() -> in.lines().forEach(System.out::println)).start();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            out.println(sc.nextLine());
        }
    }
}
