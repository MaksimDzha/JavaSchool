import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

class CacheInFiles {

    private static Map<String, Object> cacheMethods = new HashMap<>();

    //Поиск в файлах
    static Object findInFiles(Method method, Object[] args, Object cacheObject) {
        String cacheArgs = new String();
        if (args != null) {
            for (Object arg : args)
                cacheArgs += arg.toString() + " ";
        } else {
            cacheArgs = "";
        }
        try {
            FileInputStream fis = new FileInputStream(method.getName());
            ObjectInputStream ois = new ObjectInputStream(fis);
            cacheMethods = (HashMap) ois.readObject();
            if (cacheMethods.containsKey(cacheArgs)) {
                System.out.println("Результат из кеша с аргументами ( " + cacheArgs + "): " + cacheMethods.get(cacheArgs));
                return cacheMethods.get(cacheArgs);
            }
        } catch (FileNotFoundException e) {
            cacheMethods.clear();
            System.out.println("Метод не кэшировался, запускается выполнение основного алгоритма:");
            return cacheInFiles(method, args, cacheArgs, cacheObject);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Результат не найден, запускается выполнение основного алгоритма:");
        return cacheInFiles(method, args, cacheArgs, cacheObject);
    }

    //Записываем в файл
    private static Object cacheInFiles(Method method, Object[] args, String cacheArgs, Object cacheObject) {
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
}
