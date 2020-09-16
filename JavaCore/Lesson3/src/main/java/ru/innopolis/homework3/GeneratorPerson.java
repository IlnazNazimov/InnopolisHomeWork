package ru.innopolis.homework3;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GeneratorPerson {
    private static final List<Sex> VALUES = Arrays.asList(Sex.values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    private static final int COUNT_SYMBOL_IN_NAME = 5;
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Генерирует объект ru.innopolis.homework3.Person со всеми полями(sex,age,name)
     * @param count количество генерируемых объектов
     * @return массив сгенерированных оъектов
     */
    public static Person[] generate(final int count) {
        final Person[] persons = new Person[count];
        for (int i = 0; i < count; i++) {
            final Sex sex = randomSex();
            final int age = randomAge();
            final String name = randomName();
            persons[i] = new Person(name, age, sex);
        }
        return persons;
    }

    private static Sex randomSex() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    private static int randomAge() {
        return RANDOM.nextInt(101);
    }

    private static String randomName() {
        int i = COUNT_SYMBOL_IN_NAME;
        StringBuilder name = new StringBuilder();
        while (i-- > 0) {
            name.append(LETTERS.charAt(RANDOM.nextInt(LETTERS.length())));
        }
        return name.toString();
    }
}
