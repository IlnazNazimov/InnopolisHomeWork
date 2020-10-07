package ru.innopolis.homework6;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class GameTest {

    @Test
    public void startGame() throws InterruptedException {
        int[][] mas = {{0, 0, 0, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}};
        int[][] result = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 1, 1, 1}, {0, 0, 0, 0}};

        int countThread = 1;
        Map map = new Map(mas, 4, 4);
        Console console = new Console(1, 1, map);

        CyclicBarrier barrier = new CyclicBarrier(countThread);

        Game game = new Game(map, 0, 4, console, barrier);
        game.start();
        game.join();
        Assert.assertArrayEquals(map.getFields(), result);
    }

    @Test
    public void threadExecutionTime() {
        Map map = Loader.generateMap(100, 100);
        long timeOneTread = runGame(1, map);
        long timeTwoThread = runGame(2, map);

        System.out.println(timeOneTread);
        System.out.println(timeTwoThread);

        Assert.assertTrue(timeTwoThread < timeOneTread);
    }

    private long runGame(int countThread, Map map) {
        int iteration = 1500;

        Console console = new Console(iteration, countThread, map);

        CyclicBarrier barrier = new CyclicBarrier(countThread);
        long startTime = System.currentTimeMillis();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < countThread; i++) {
            int startPosition = map.getWidth() / countThread * i;
            int endPosition = map.getWidth() / countThread * i + map.getWidth() / countThread;

            Game game = new Game(map, startPosition, endPosition, console, barrier);
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
        return endTime - startTime;
    }
}