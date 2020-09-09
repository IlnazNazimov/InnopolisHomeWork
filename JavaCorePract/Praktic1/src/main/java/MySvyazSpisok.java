import java.util.Objects;

public class MySvyazSpisok {
    private Object current;
    private MySvyazSpisok next;
    private MySvyazSpisok last;

    /**
     * Сует элемент в односвязный список
     *
     * @param obj Объект, который помещается в список
     */
    public void put(Object obj) {
        if (current == null) {
            current = obj;
        } else if (next == null) {
            MySvyazSpisok newMySvyazSpisok = new MySvyazSpisok();
            newMySvyazSpisok.current = obj;
            next = newMySvyazSpisok;
            last = newMySvyazSpisok;
        } else {
            last.put(obj);
        }
    }

    /**
     * Получаем объект по номеру
     *
     * @param i номер элемента, который хотим получить
     * @return Объект под данным номером
     */
    public Object get(int i) {
        if (isBoolean(i, 0)) {
            return current;
        }
        MySvyazSpisok mySvyazSpisokItaya = this;
        for (int j = 0; j < i; j++) {
            if (mySvyazSpisokItaya.next != null) {
                mySvyazSpisokItaya = mySvyazSpisokItaya.next;
            }
            else return null;
            if (isBoolean(j, i - 1)) {
                return mySvyazSpisokItaya.current;
            }
        }
        return null;
    }

    private boolean isBoolean(int j, int i) {
        return j == i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MySvyazSpisok that = (MySvyazSpisok) o;
        return Objects.equals(current, that.current) &&
                Objects.equals(next, that.next) &&
                Objects.equals(last, that.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(current, next, last);
    }

    @Override
    public String toString() {
        MySvyazSpisok mySvyazSpisokItaya = next;
        StringBuilder stringBuilder = new StringBuilder(current.toString());
        while (mySvyazSpisokItaya != null) {
            stringBuilder.append(" ").append(mySvyazSpisokItaya.current.toString());
            mySvyazSpisokItaya = mySvyazSpisokItaya.next;
        }
        return stringBuilder.toString();
    }
}
