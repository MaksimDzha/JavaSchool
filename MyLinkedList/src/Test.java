import java.util.ArrayList;
import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("aaa");
        names.add("bbb");
        names.add("ccc");
        names.add("ddd");
        for (Object n: names) System.out.println(n);
        System.out.println();

        MyLinkedList<String> myNames = new MyLinkedList<>(names);
        for (Object n: myNames) System.out.println(n);
        System.out.println();

        ArrayList<String> names2 = new ArrayList<>();
        names2.add("aaa2");
        names2.add("bbb2");
        names2.add("ccc2");
        names2.add("ddd2");

        myNames.copy(names2);
        for (Object o: myNames) System.out.println(o);
        System.out.println();

    }
}
