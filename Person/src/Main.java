import java.util.*;

public class Main {
    public static void main(String[] args) {
        Person ivan = new Person(true, "Ivan");
        Person petr = new Person(true, "Petr");
        Person rita = new Person(false, "Rita");
        Person vika = new Person(false, "Vika");
        Person ivan2 = new Person(true, "Ivan");

/*        ivan.marry(ivan);
        petr.divorce();
        rita.marry(vika);
        vika.marry(ivan);
        ivan.marry(rita);
        petr.marry(null);
        petr.marry(new Person(false, "Irina"));
        petr.marry(rita);*/

        List<Person> personList = new ArrayList<>();
        personList.add(ivan);
        personList.add(ivan2);
        personList.add(petr);
        personList.add(rita);
        personList.add(vika);
        for (Person p: personList) System.out.println(p.getName());
        System.out.println();

        Set<Person> personSet = new HashSet<>();
        personSet.add(ivan);
        personSet.add(ivan2);
        personSet.add(petr);
        personSet.add(rita);
        personSet.add(vika);
        for (Person p: personSet) System.out.println(p.getName());

        if (ivan.getName() == ivan2.getName()) System.out.println("ivan.name = ivan2.name");
        else System.out.println("ivan.name != ivan2.name");

        if (ivan.equals(ivan2)) System.out.println("ivan.obj = ivan2.obj");
        else System.out.println("ivan.obj != ivan2.obj");

        if (personSet.) System.out.println("ivan = ivan2");
        else System.out.println("ivan != ivan2");

    }
}
