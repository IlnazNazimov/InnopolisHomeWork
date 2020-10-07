package ru.innopolis.homework6;

public class Printer {
    /**
     * Выводит двумерный массив в консоль
     *
     * @param fields Двумерный массив, который нужно вывести
     */
    public static void printMap(int[][] fields) {
        int width = fields[0].length;
        for (int[] field : fields) {
            for (int j = 0; j < width; j++) {
                System.out.print((field[j] == 0 ? "." : "*") + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
