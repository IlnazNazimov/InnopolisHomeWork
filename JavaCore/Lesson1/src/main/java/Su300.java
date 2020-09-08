public class Su300 extends Vertolet{
    public Su300(int speed, String color) {
        super(speed, color);
        System.out.println("Создали вертолет су300");
    }

    @Override
    public void flyfast() {
        System.out.println("Ускорение су300");
    }
}
