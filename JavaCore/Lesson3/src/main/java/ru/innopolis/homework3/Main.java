package ru.innopolis.homework3;

public class Main {
    public static void main(String[] args) {
        final Person[] persons = GeneratorPerson.generate(1000);
        Person[] personsSort;

        personsSort = sort(persons, new BubbleSort());
        print(personsSort);

        personsSort = sort(persons, new InsertionSort());
        print(personsSort);
    }

    private static Person[] sort(Person[] persons, Sortable sorter) {
        Person[] personsSort;
        long startTime = System.currentTimeMillis();
        personsSort = sorter.sort(persons);
        long endTime = System.currentTimeMillis();

        System.out.println("Время выполнения сортировки: " + (endTime - startTime) + "ms");
        return personsSort;
    }

    public static void print(Person[] persons) {
        for (Person person : persons) {
            System.out.println(person);
        }
        System.out.println("===================================");
    }
}
