package ru.innopolis.homework3;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class PersonTest {

    @Test
    void compareToFirstMore() {
        Person personFirst = new Person("вася", 15, Sex.MAN);
        Person personSecond = new Person("петя", 15, Sex.WOMAN);

        Assert.assertEquals(personSecond.compareTo(personFirst),-1);
    }

    @Test
    void compareToSecondMore() {
        Person personFirst = new Person("вася", 15, Sex.MAN);
        Person personSecond = new Person("петя", 16, Sex.MAN);

        Assert.assertEquals(personSecond.compareTo(personFirst),1);
    }

    @Test
    void compareToEqual() {
        Person personFirst = new Person("вася", 16, Sex.MAN);
        Person personSecond = new Person("вася", 16, Sex.MAN);

        Assert.assertEquals(personSecond.compareTo(personFirst),0);
    }
}