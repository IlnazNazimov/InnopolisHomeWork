public class Person {
    private String name ="Петя";
    private int weight = 50;

    public Person(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
