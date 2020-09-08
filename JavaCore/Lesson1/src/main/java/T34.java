public class T34 extends Vertolet {
    public T34() {
        super(100, "black");
        System.out.println("Создали вертолет т34");
    }

    @Override
    public void flyfast() {
        System.out.println("ускорение т34");
    }
}
