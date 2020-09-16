package ru.innopolis.homework3;

public class InsertionSort implements Sortable{

    @Override
    public Person[] sort(final Person[] person) {
        for (int left = 0; left < person.length; left++) {
            Person value = person[left];

            int i = left- 1;
            for (; i >= 0; i--) {
                if (value.compareTo(person[i]) > 0) {
                    person[i + 1] = person[i];
                } else {
                    break;
                }
            }
            person[i + 1] = value;
        }
        return person;
    }
}
