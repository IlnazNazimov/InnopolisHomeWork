package ru.innopolis.homework5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Сервер запущен!");
            List<String> fileNames;

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Клиент подключился");

                try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                     PrintWriter output = new PrintWriter(socket.getOutputStream())) {

                    while (!input.ready()) ;

                    String type = getType(input);

                    while (input.ready()) {
                        System.out.println(input.readLine());
                    }

                    if (type.equals("GET")) {
                        fileNames = readCurrentDirectory();
                        output.println("HTTP/1.1 200 OK");
                        output.println("Content-Type: text/html; charset=utf-8");
                        output.println();
                        output.println(fileNames);
                        System.out.println(fileNames);
                    } else {
                        output.println("HTTP/1.1 404 ERROR");
                        output.println("Content-Type: text/html; charset=utf-8");
                        output.println();
                    }

                    output.flush();
                    System.out.println("Клиент отключился");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String getType(BufferedReader input) throws IOException {
        String type = "";
        if (input.ready()) {
            String firstLine = input.readLine();
            type = firstLine.split(" ")[0];
            System.out.println(firstLine);
        }
        return type;
    }

    private static List<String> readCurrentDirectory() {
        List<String> names = new ArrayList<>();
        File folder = new File(new File(".").getAbsolutePath());

        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            names.add(fileEntry.getName());
        }

        return names;
    }
}

