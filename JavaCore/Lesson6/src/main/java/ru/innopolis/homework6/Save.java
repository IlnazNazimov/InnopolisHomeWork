package ru.innopolis.homework6;

import java.io.FileWriter;
import java.io.IOException;

public class Save {
    private static final String PATH = "./src/main/resources/result.txt";

    /**
     * Сохраняет двумерный массив в файл
     *
     * @param fields Массив, который нужно сохранить
     */
    public static void addInFile(int[][] fields) {
        try (FileWriter writer = new FileWriter(PATH, false)) {
            for (int[] field : fields) {
                for (int j = 0; j < fields[0].length; j++) {
                    writer.write(field[j] + " ");
                }
                writer.write("\n");
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
