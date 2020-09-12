import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();
        map.put("1",1);
        map.put("2",2);
        map.put("3",1);
        map.put("4",2);

        Map<Integer,List<String>> resultMap = ReverceMap.reverse(map);

        for (Map.Entry<Integer, List<String>> entryResult : resultMap.entrySet()) {
            System.out.println(entryResult.getKey().toString() + " " + entryResult.getValue().toString());
        }
    }
}
