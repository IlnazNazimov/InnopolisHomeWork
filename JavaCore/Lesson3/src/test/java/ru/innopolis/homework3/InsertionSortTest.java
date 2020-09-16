package ru.innopolis.homework3;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class InsertionSortTest {

    @Test
    void sort() {
        Person personFirst = new Person("вася", 17, Sex.MAN);
        Person personSecond = new Person("петя", 15, Sex.MAN);
        Person personThird = new Person("катя", 16, Sex.WOMAN);

        Person[] firstMas = new Person[3];
        firstMas[0] = personFirst;
        firstMas[1] = personSecond;
        firstMas[2] = personThird;

        Person[] secondMas = new Person[3];
        secondMas[0] = personThird;
        secondMas[1] = personFirst;
        secondMas[2] = personSecond;

        Assert.assertArrayEquals(new InsertionSort().sort(secondMas),firstMas);
    }
}