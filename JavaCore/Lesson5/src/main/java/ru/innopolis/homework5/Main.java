package ru.innopolis.homework5;

public class Main {
    public static final int PORT = 8080;

    public static void main(String[] args) {
        MySocketServer socketServer = new MySocketServer(PORT);
        socketServer.runSocketServer();
    }
}

