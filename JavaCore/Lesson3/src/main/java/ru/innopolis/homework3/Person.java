package ru.innopolis.homework3;

public class Person implements Comparable<Person> {
    private final String name;
    private final int age;
    private final Sex sex;

    public Person(final String name, final int age, final Sex sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    /**
     * Сравнивает объекты сначала пол, потом возраст, потом имя
     * @param person объект Person  с которым будем сравнивать
     * @return 1 - если сравниваемый объект больше, -1 если меньше, иначе 0
     */
    public int compareTo(final Person person) {
        int flag = 0;

        if (this.name.equals(person.name) && this.age == person.age) {
            try {
                throw new BadPersonException("Имя и возраст двух объектов одинаковые!");
            } catch (BadPersonException e) {
                e.printStackTrace();
            }
        }

        if (this.sex.equals(Sex.MAN) && person.sex.equals(Sex.WOMAN)) {
            flag = 1;
        } else if (this.sex.equals(Sex.WOMAN) && person.sex.equals(Sex.MAN)) {
            flag = -1;
        }

        if (flag == 0) {
            if ((this.age - person.age) < 0) {
                flag = -1;
            } else if ((this.age - person.age) > 0) {
                flag = 1;
            }
        }

        if (flag == 0) {
            flag = person.name.compareTo(this.name);
        }

        return flag;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
