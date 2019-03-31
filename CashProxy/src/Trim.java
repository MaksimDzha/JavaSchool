import java.lang.reflect.Method;
import java.util.*;

class Trim {

    /**
     * Метод проверяет, является ли передаваемый объект коллекцией, множеством или массивом.
     * Если является, то уменьшает количество элементов до указанного значения.
     *
     * @param method Метод, возвращамый класс которого проверяется
     * @param result Результат работы method
     * @param count  Ограничение, наложенное на количество элементов
     */
    static Object trim(Method method, Object result, int count) {
        if (method.getReturnType().getName().equals("java.util.List"))
            return trimList(result, count);
        if (method.getReturnType().getName().equals("java.util.Set"))
            return trimSet(result, count);
        if (method.getReturnType().isArray())
            return trimArray(result, count);
        try {
            if (method.getReturnType().newInstance() instanceof Collection)
                return trimCollection(method, result, count);
            if (method.getReturnType().newInstance() instanceof Set)
                return trimSetImpl(method, result, count);
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("Результат не является коллекцией, множеством или массивом.");
        }
        return result;
    }

    //Удаление "лишних" элементов из коллекции, объявленной как List
    private static Object trimList(Object result, int count) {
        List newResult = (List) result;
        if (newResult.size() > count) {
            ArrayList cacheResult = new ArrayList();
            System.out.println("Предупреждение. Результат будет преобразован (?)List -> ArrayList");
            cacheResult.addAll(newResult.subList(0, count));
            return cacheResult;
        }
        return result;
    }

    //Удаление "лишних" элементов из множества, объявленного как Set
    private static Object trimSet(Object result, int count) {
        Set newResult = (Set) result;
        if (newResult.size() > count) {
            System.out.println("Предупреждение. Результат будет преобразован (?)Set -> Object[]");
            return trimArray(newResult, count);
        }
        return result;
    }

    //Удаление "лишних" элементов из коллекции
    private static Object trimCollection(Method method, Object result, int count) {
        try {
            if (method.getReturnType().newInstance() instanceof ArrayList) {
                ArrayList newResult = (ArrayList) result;
                if (newResult.size() > count) {
                    ArrayList cacheResult = new ArrayList();
                    cacheResult.addAll(newResult.subList(0, count));
                    return cacheResult;
                }
            }
            if (method.getReturnType().newInstance() instanceof LinkedList) {
                LinkedList newResult = (LinkedList) result;
                if (newResult.size() > count) {
                    LinkedList cacheResult = new LinkedList();
                    cacheResult.addAll(newResult.subList(0, count));
                    return cacheResult;
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("'Обрезка' данного типа коллекции не поддерживается. Используйте ArrayList или LinkedList.");
            e.printStackTrace();
        }
        return result;
    }

    //Удаление "лишних" элементов из множества или преобразование к массиву
    private static Object trimSetImpl(Method method, Object result, int count) {
        try {
            if (method.getReturnType().newInstance() instanceof TreeSet) {
                TreeSet newResult = (TreeSet) result;
                if (newResult.size() > count) {
                    TreeSet cacheResult = new TreeSet();
                    cacheResult.addAll(newResult.subSet(0, count));
                    return cacheResult;
                }
            }
            if (method.getReturnType().newInstance() instanceof HashSet) {
                HashSet newResult = (HashSet) result;
                if (newResult.size() > count) {
                    System.out.println("Результат преобразован: HashSet -> Object[]");
                    return trimArray(newResult.toArray(), count);
                }
            }
            if (method.getReturnType().newInstance() instanceof LinkedHashSet) {
                LinkedHashSet newResult = (LinkedHashSet) result;
                if (newResult.size() > count) {
                    System.out.println("Результат преобразован: LinkedHashSet -> Object[]");
                    return trimArray(newResult.toArray(), count);
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("'Обрезка' данного типа множества не поддерживается: " +
                    "рекомендуется использовать TreeSet, HashSet или LinkedHashSet.");
//            e.printStackTrace();
        }
        return result;
    }

    //Удаление "лишних" элементов из массива
    private static Object[] trimArray(Object result, int count) {
        Object[] newResult = (Object[]) result;
        Object[] cacheResult = new Object[newResult.length > count ? count : newResult.length];
        for (int i = 0; i < cacheResult.length; i++)
//            System.arraycopy(result, 0, cacheResult, 0, count);
            cacheResult[i] = newResult[i];
        return cacheResult;

    }

}
