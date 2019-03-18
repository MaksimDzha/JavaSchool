//Применение refleсtion для отображения всех методов класса,
// включая приватные методы

import java.lang.reflect.*;
import java.util.regex.Pattern;


public class ShowDeclaredMethods {
    private static String usage =
            "Используйте:\n" +
                    "Showmethods qualified.class.name\n" +
                    "чтобы показать все реализованные методы и конструкторы класса, или:\n" +
                    "Showmethods qualified.class.name word\n" +
                    "чтобы найти все реализованные методы и конструкторы, содержащие слово 'word'";
    private static Pattern p = Pattern.compile("\\w+\\.");

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println(usage);
            System.exit(0);
        }
        int countMethods = 0;
        int countCtors = 0;
        try {
            Class<?> c = Class.forName(args[0]);
            Method[] methods = c.getDeclaredMethods();
            Constructor[] constructors = c.getDeclaredConstructors();
            if (args.length == 1) {
                for (Method method : methods)
                    System.out.println(p.matcher(method.toString()).replaceAll(""));
//                    System.out.println(method.toString());

                for (Constructor constructor : constructors)
                    System.out.println(p.matcher(constructor.toString()).replaceAll(""));
                countMethods = methods.length;
                countCtors = constructors.length;
            } else {
                for (Method method : methods)
                    if (method.toString().indexOf(args[1]) != -1) {
                        System.out.println(p.matcher(method.toString()).replaceAll(""));
                        countMethods++;
                    }
                for (Constructor constructor : constructors)
                    if (constructor.toString().indexOf(args[1]) != -1) {
                        System.out.println(p.matcher(constructor.toString()).replaceAll(""));
                        countCtors++;
                    }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Класс не найден: " + e);
        }
        if (countMethods > 0)
            System.out.println("Найдено методов: " + countMethods);
        if (countCtors > 0)
            System.out.println("Найдено конструкторов: " + countCtors);

    }
}
