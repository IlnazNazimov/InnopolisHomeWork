package ru.innopolis.homework6;

import java.util.Scanner;

public class Console {
    private int iteration;
    private int countThread;
    private Map map;

    public Console() {
    }

    public Console(int iteration, int countThread, Map map) {
        this.iteration = iteration;
        this.countThread = countThread;
        this.map = map;
    }

    /**
     * Запускает в консоли получения конфигураций для игры
     */
    public void runConsole() {
        Scanner scanner = new Scanner(System.in);
        getMap(scanner);
        getCountThread(scanner);
        getIteration(scanner);
    }

    /**
     * Спрашивает у пользователя количество итераций и считывает его
     *
     * @param scanner Scanner, который считывает информацию с консоли
     */
    private void getIteration(Scanner scanner) {
        boolean flag = true;
        while (flag) {
            System.out.println("Введите количество итераций:");
            int countIter = Integer.parseInt(scanner.nextLine());
            if (countIter < 1) {
                System.out.println("Количество итераций должно быть больше еденицы!");
            } else {
                iteration = countIter;
                flag = false;
            }
        }
    }

    /**
     * Спрашивает у пользователя количество потоков и считывает его
     *
     * @param scanner Scanner, который считывает информацию с консоли
     */
    private void getCountThread(Scanner scanner) {
        boolean flag = true;
        while (flag) {
            System.out.println("Введите количество потоков:");
            int threadCount = Integer.parseInt(scanner.nextLine());
            if (threadCount < 1) {
                System.out.println("Количество потоков должно быть больше нуля!");
            } else {
                countThread = threadCount;
                flag = false;
            }
            System.out.println();
        }
    }

    /**
     * Спрашивает у пользователя формат построения карты и строит её
     *
     * @param scanner Scanner, который считывает информацию с консоли
     */
    private void getMap(Scanner scanner) {
        boolean flag = true;
        while (flag) {
            System.out.println("Введите способ построения карты:");
            System.out.println("1 - Скачать карту из файла ");
            System.out.println("2 - Сгенерировать карту ");
            int index = Integer.parseInt(scanner.nextLine());

            if (index == 1) {
                map = Loader.loadMap();
                flag = false;
            } else if (index == 2) {
                map = Loader.generateMap(getWeight(scanner), getLength(scanner));
                flag = false;
            } else {
                System.out.println("Можно ввести только значение 1 или 2!");
            }
        }
    }

    /**
     * Спрашивает у пользователя длину карты и считывает его
     *
     * @param scanner Scanner, который считывает информацию с консоли
     * @return Длина карты
     */
    private int getLength(Scanner scanner) {
        boolean flag = true;
        int length = 0;
        while (flag) {
            System.out.println("Введите длину карты:");
            length = Integer.parseInt(scanner.nextLine());
            if (length < 3 || length > 150) {
                System.out.println("Что за длина карты? Введи нормальное значение!");
            } else {
                flag = false;
            }
        }
        return length;
    }

    /**
     * Спрашивает у пользователя ширину карты и считывает его
     *
     * @param scanner Scanner, который считывает информацию с консоли
     * @return Ширина карты
     */
    private int getWeight(Scanner scanner) {
        boolean flag = true;
        int weight = 0;
        while (flag) {
            System.out.println("Введите ширину карты:");
            weight = Integer.parseInt(scanner.nextLine());
            if (weight < 3 || weight > 150) {
                System.out.println("Что за ширина карты? Введи нормальное значение!");
            } else {
                flag = false;
            }
        }
        return weight;
    }

    public int getIteration() {
        return iteration;
    }

    public int getCountThread() {
        return countThread;
    }

    public Map getMap() {
        return map;
    }
}
