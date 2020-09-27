package ru.innopolis.homework6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) {
        Console console = new Console();
        console.runConsole();

        Map map = console.getMap();
        int countThread = console.getCountThread();

        CyclicBarrier barrier = new CyclicBarrier(countThread);

        long startTime = System.currentTimeMillis();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < countThread; i++) {
            int startPosition = map.getWidth() / countThread * i;
            int endPosition = map.getWidth() / countThread * i + map.getWidth() / countThread;

            Game game;
            if (map.getWidth() % countThread != 0 && i == countThread - 1) {
                game = new Game(map, startPosition, endPosition + map.getWidth() % countThread, console, barrier);
            } else {
                game = new Game(map, startPosition, endPosition, console, barrier);
            }
            game.start();
            threads.add(game);
        }

        threads.forEach(x -> {
            try {
                x.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long endTime = System.currentTimeMillis();
        Printer.printMap(map.getFields());
        System.out.println(endTime - startTime);
        Save.addInFile(map.getFields());
    }
}
