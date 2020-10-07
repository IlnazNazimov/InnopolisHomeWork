package ru.innopolis.homework6;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Game extends Thread {
    private final int[][] fields;
    private final int iteration;
    private final Map map;
    private int countLive;
    private final int start;
    private final int finish;
    private final CyclicBarrier barrier;
    private static boolean flag = true;

    public Game(Map map, int start, int finish, Console console, CyclicBarrier barrier) {
        this.fields = map.getFields();
        this.map = map;
        this.start = start;
        this.finish = finish;
        this.iteration = console.getIteration();
        this.barrier = barrier;
    }

    @Override
    public void run() {
        for (int i = 0; i < iteration; i++) {
            startGame();
        }

        if (flag) {
            synchronized (Game.class) {
                if (flag) {
                    flag = false;
                    map.setFields(fields);
                }
            }
        }
    }

    /**
     * Запускает игру
     */
    void startGame() {
        int[][] newFields = new int[map.getLength()][finish - start];
        for (int i = 0; i < map.getLength(); i++) {
            for (int j = start, k = 0; j < finish; j++, k++) {
                check(i - 1, j);
                check(i - 1, j + 1);
                check(i, j + 1);
                check(i + 1, j + 1);
                check(i + 1, j);
                check(i + 1, j - 1);
                check(i, j - 1);
                check(i - 1, j - 1);

                setNewField(newFields, i, j, k);
                countLive = 0;
            }
        }

        updateFields(newFields);
    }

    /**
     * Перезаписывает поле fields
     *
     * @param newFields Новый двумерный массив
     */
    private void updateFields(int[][] newFields) {
        try {
            barrier.await();

            for (int i = 0; i < map.getLength(); i++) {
                for (int j = start, k = 0; j < finish; j++, k++) {
                    fields[i][j] = newFields[i][k];
                }
            }

            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    /**
     * Вычисляет новое значение поля
     *
     * @param newFields Новое поле
     * @param i         Строка
     * @param j         Столбец основного массива
     * @param k         Столбец нового массива
     */
    private void setNewField(int[][] newFields, int i, int j, int k) {
        if (fields[i][j] == 0 && countLive == 3) {
            newFields[i][k] = 1;
        } else if (fields[i][j] == 1 && (countLive < 2 || countLive > 3)) {
            newFields[i][k] = 0;
        } else newFields[i][k] = fields[i][j];
    }

    /**
     * Считает количество живых клеток вокруг
     *
     * @param i Строка
     * @param j Столбец
     */
    private void check(int i, int j) {
        if (i < 0) i = map.getLength() - 1;
        if (i > map.getLength() - 1) i = 0;
        if (j < 0) j = map.getWidth() - 1;
        if (j > map.getWidth() - 1) j = 0;

        if (fields[i][j] == 1) countLive++;
    }
}
