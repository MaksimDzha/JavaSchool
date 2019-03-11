import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        CountMap<String> test1 = new MyCountMap<>();
        test1.add("aaa");
        test1.add("aaa");
        test1.add("ccc");
        test1.add("");

        CountMap<String> test2 = new MyCountMap<>();
        test2.add("aaa");
        test2.add("bbb");
        test2.add(null);
        test2.add(null);

        test1.addAll(test2);

        Map<String, Integer> test3 = test1.toMap();
        test3.put("aaa", 1);

        Map<String, Integer> test4 = new HashMap<>();
        test4.put("ppp", 1);
        test1.toMap(test4);

        System.out.println(test1.getCount("aaa"));
        System.out.println(test1.getCount(null));
        System.out.println(test1.keySet());


    }

}
