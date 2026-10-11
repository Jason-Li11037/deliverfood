import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("Hello");
        strings.add("你好");
        strings.add("Hola");

        List<Integer> integers = List.of(1, 3, 5, 7);

        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        System.out.println(strings);
        System.out.println(integers);
        System.out.println(map);
        System.out.println(set);
    }
}
