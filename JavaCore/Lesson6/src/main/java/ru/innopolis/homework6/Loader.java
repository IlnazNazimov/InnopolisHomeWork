package ru.innopolis.homework6;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Loader {
    private static int weight = 0;
    private static int length = 0;
    private static final String PATH = "./src/main/resources/prop.properties";

    /**
     * Загружает данные и заполняет мапу
     *
     * @return Карта
     */
    public static Map loadMap() {
        Properties properties = read();
        int[][] fields = covertStringToMassiv(properties.getProperty("fields"));
        return new Map(fields, weight, length);
    }

    /**
     * Загружает файл формата .properties
     *
     * @return Загруженный файл
     */
    private static Properties read() {
        Properties properties = new Properties();
        try {
            FileReader fileReader = new FileReader(PATH);
            properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    /**
     * Преобразовывает строку в двумерный массив
     *
     * @param stringFields Строка, которую нужно преобразовать
     * @return Результат преобразования
     */
    private static int[][] covertStringToMassiv(String stringFields) {
        String[] numbers = stringFields.split(";");
        length = numbers.length;
        weight = getWeight(numbers[0]);

        int[][] fields = new int[length][weight];

        for (int i = 0; i < length; i++) {
            String[] mas = numbers[i].split(",");

            if (mas.length != weight) {
                throw new BadMapException("В файле некорректная карта");
            }

            fillInLine(fields, i, mas);
        }
        return fields;
    }

    /**
     * Конвертирует из String в int и заполняет строку line в двумерном массиве
     *
     * @param fields  Двумерный массив, который нужно заполнить
     * @param line    Номер строки в двумерном массиве
     * @param strings Массив, откуда берем значения
     */
    private static void fillInLine(int[][] fields, int line, String[] strings) {
        for (int j = 0; j < weight; j++) {
            int value = Integer.parseInt(strings[j]);
            fields[line][j] = value;
        }
    }

    /**
     * Получает количество элементов, которые разделены запятой
     *
     * @param numbers Строка в которой нужно посчитать элементы
     * @return Количество элементов
     */
    private static int getWeight(String numbers) {
        return numbers.split(",").length;
    }

    /**
     * Генерирует двумерный массив единицами и нулями
     *
     * @param weight Ширина двумерного массива
     * @param length Длина двумерного массива
     * @return Карта, с заполненным двумерным массивом
     */
    public static Map generateMap(int weight, int length) {
        int[][] fields = new int[length][weight];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < weight; j++) {
                fields[i][j] = (int) (Math.random() * 2);
            }
        }
        return new Map(fields, weight, length);
    }
}
