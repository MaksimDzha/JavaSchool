import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy implements InvocationHandler {

    private Object cacheObject;
    private static Map<String, Object> cacheMethods = new HashMap<>();
    private static Map<Method, Map> cache = new HashMap<>();

    public CacheProxy(Object cacheObject) {
        this.cacheObject = cacheObject;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Работает прокси");
        System.out.println("Проверяем аннотацию @Cache");
        Cache cacheAnn = cacheObject.getClass().getMethod(method.getName(), method.getParameterTypes()).getAnnotation(Cache.class);
        if (cacheAnn == null) {
            System.out.println("Метод без аннотации @Cache, выполняем: ");
            return method.invoke(cacheObject, args);
        }
        String cacheArgs = new String();
        for (Object arg : args)
            cacheArgs += arg.toString() + " ";
        System.out.print("Проверяем кэш...");
        switch (cacheAnn.value()){

        }
        return findInMemory(method, args, cacheArgs);
    }

    //Записываем в память
    private Object cacheInMemori(Method method, Object[] args, String cacheArgs) {
//        System.out.println("Кэш не содержит результатов");
        Object result = null;
        try {
            result = method.invoke(cacheObject, args);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        cacheMethods.put(cacheArgs, result);
        cache.put(method, cacheMethods);
//        System.out.println("Результат расчета в кеш: " + cache.get(method).get(cacheArgs));
//        System.out.println("Результат сохранен в кеш");
        return result;
    }

    //Записываем в файл
    private Object cacheInFile(Method method, Object[] args, String cacheArgs) {
        Object result = null;
        try {
            result = method.invoke(cacheObject, args);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        cacheMethods.put(cacheArgs, result);
        try {
            FileOutputStream fos = new FileOutputStream(method.getName());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cacheMethods);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //Поиск в памяти
    private Object findInMemory(Method method, Object[] args, String cacheArgs) {
        if (cache.containsKey(method))
            if (cacheMethods.containsKey(cacheArgs)) {
                System.out.println("Результат из кеша: " + cache.get(method).get(cacheArgs));
                return cache.get(method).get(cacheArgs);
            }
        System.out.println("Результат не найден, запускается выполнение основного алгоритма:");
        return cacheInMemori(method, args, cacheArgs);
    }
}
