import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

class CacheInMemory {

    private static Map<Method, Map> cacheMemori = new HashMap<>();
    private static Map<String, Object> cacheMethods = new HashMap<>();

    //Поиск в памяти
    static Object findInMemory(Method method, Object[] args, Object cacheObject) {
        String cacheArgs = new String();
        if (args != null) {
            for (Object arg : args)
                cacheArgs += arg.toString() + " ";
        } else {
            cacheArgs = "";
        }
        if (cacheMemori.containsKey(method))
            if (cacheMethods.containsKey(cacheArgs)) {
                System.out.println("Результат из кеша с аргументами ( " + cacheArgs + "): " + cacheMemori.get(method).get(cacheArgs));
                return cacheMemori.get(method).get(cacheArgs);
            }
        System.out.println("Результат не найден, запускается выполнение основного алгоритма:");
        return cacheInMemori(method, args, cacheArgs, cacheObject);
    }

    //Записываем в память
    private static Object cacheInMemori(Method method, Object[] args, String cacheArgs, Object cacheObject) {
//        System.out.println("Кэш не содержит результатов");
        Object result = null;
        try {
            result = method.invoke(cacheObject, args);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        cacheMethods.put(cacheArgs, result);
        cacheMemori.put(method, cacheMethods);
//        System.out.println("Результат расчета в кеш: " + cacheMemori.get(method).get(cacheArgs));
//        System.out.println("Результат сохранен в кеш");
        return result;
    }
}
