package ru.innopolis.homework5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MySocketServer {
    private final int port;

    public MySocketServer(int port) {
        this.port = port;
    }

    /**
     * Запускает сокет сервер и отправляет клиенту названия файлов в текущей директории
     */
    public void runSocketServer() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен!");

            sendFileNames(serverSocket);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * На GET запросы отвечает названиями файлов в текущей директории
     *
     * @param serverSocket Сокет сервер
     * @throws IOException Ошибка чтения/записи данных
     */
    private void sendFileNames(ServerSocket serverSocket) throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();

            try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                 PrintWriter output = new PrintWriter(socket.getOutputStream())) {

                while (!input.ready()) ;
                String type = getType(input);

                printQuery(input);

                sendResponse(output, type);
            }
        }
    }

    /**
     * Вывод в консоль запрос клиента
     *
     * @param input входящий поток даннных
     * @throws IOException ошибка чтения потока
     */
    private void printQuery(BufferedReader input) throws IOException {
        while (input.ready()) {
            System.out.println(input.readLine());
        }
    }

    /**
     * Отправка ответа клиенту
     *
     * @param output Поток исходящих данных
     * @param type   Тип запроса
     */
    private void sendResponse(PrintWriter output, String type) {
        List<String> fileNames;
        if (type.equals("GET")) {
            fileNames = readCurrentDirectory();
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println(fileNames);
            System.out.println(fileNames);
            System.out.println("====================================");
        } else {
            output.println("HTTP/1.1 404 ERROR");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
        }
        output.flush();
    }

    /**
     * Определяет тип запроса
     *
     * @param input входящий поток данных
     * @return Тип запроса
     * @throws IOException Ошибка чтения данных
     */
    private static String getType(BufferedReader input) throws IOException {
        String type = "";
        if (input.ready()) {
            String firstLine = input.readLine();
            type = firstLine.split(" ")[0];
            System.out.println(firstLine);
        }
        return type;
    }

    /**
     * Находит файлы в текущей директории
     *
     * @return Названия файлов
     */
    private static List<String> readCurrentDirectory() {
        List<String> names = new ArrayList<>();
        File folder = new File(new File(".").getAbsolutePath());

        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            names.add(fileEntry.getName());
        }

        return names;
    }
}
