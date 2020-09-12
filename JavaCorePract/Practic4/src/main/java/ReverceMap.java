import java.util.*;

public class ReverceMap {
    public static <K,V> Map<V, List<K>> reverse(Map<K, V> map) {
        Map<V, List<K>> mapResult = new HashMap<>();

        for (Map.Entry<K, V> mapEntry : map.entrySet()) {
            if (mapResult.containsKey(mapEntry.getValue())) {
                mapResult.get(mapEntry.getValue()).add(mapEntry.getKey());
            } else {
                ArrayList<K> newArray = new ArrayList<>();
                newArray.add(mapEntry.getKey());
                mapResult.put(mapEntry.getValue(), newArray);
            }
        }

        return mapResult;
    }
}
