import java.util.Set;

public class Dictionary<K, V> {
    private final Set<K> keys;
    private final V value;

    public Dictionary(Set<K> key, V value) {
        this.keys = key;
        this.value = value;
    }

    public void addKey(K key) {
        keys.add(key);
    }

    public V getValue(K key) {
        for (K k : keys) {
            if (k.equals(key)) {
                return value;
            }
        }
        return null;
    }

    static <T extends Number> T calc(T t) {
        if (t.intValue() < 1000) {
            System.out.println("lol");
            return t;
        } else {
            return (T) new Integer(42);
        }
    }
}
