import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy implements InvocationHandler {

    private Object cacheObject;

    public CacheProxy(Object cacheObject) {
        this.cacheObject = cacheObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Работает прокси");
        System.out.println("Проверяем аннотацию @Cache");
        Cache cacheAnn = cacheObject.getClass()
                .getMethod(method.getName(), method.getParameterTypes())
                .getAnnotation(Cache.class);
        if (cacheAnn == null) {
            System.out.println("Метод без аннотации @Cache, выполняем: ");
            return method.invoke(cacheObject, args);
        }
        System.out.print("Проверяем кэш...");
        switch (cacheAnn.value()) {
            case IN_MEMORY:
                System.out.println(StorageType.IN_MEMORY);
                return CacheInMemory.findInMemory(method, args, cacheObject);
            case IN_FILES:
                System.out.println(StorageType.IN_FILES);
                return CacheInFiles.findInFiles(method, args, cacheObject);
        }
        return 0;
    }


}
