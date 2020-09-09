public class Main {
    public static void main(String[] args) {
        MySvyazSpisok m = new MySvyazSpisok();
        m.put(1);
        m.put(2);
        m.put(3);
        m.put(4);
        m.put(5);
        System.out.println(m.get(4));
        System.out.println(m.toString());
    }
}
