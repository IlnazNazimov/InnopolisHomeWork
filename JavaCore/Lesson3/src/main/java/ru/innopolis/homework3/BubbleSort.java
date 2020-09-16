package ru.innopolis.homework3;

public class BubbleSort implements Sortable {
    @Override
    public Person[] sort(Person[] person) {
        for (int i = person.length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                switch (person[j].compareTo(person[j + 1])) {
                    case 1:
                        break;
                    case -1:
                        Person newPerson = person[j];
                        person[j] = person[j + 1];
                        person[j + 1] = newPerson;
                }
            }
        }
        return person;
    }
}
