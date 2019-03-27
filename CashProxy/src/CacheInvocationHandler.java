import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CacheInvocationHandler implements InvocationHandler {

    private Object testClass;
//    Map<Method, Object[]> index = new HashMap<>();
    Map<Method, Integer> cache = new HashMap<>();

    public CacheInvocationHandler(Object testClass) {
        this.testClass = testClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.out.println("Проверяем кэш");
//        Object obj = cache.get(method);
//        if (obj == null) {
//            obj = method.invoke(testClass, args);
//            cache.put(method, (Integer) obj);
//        }
        System.out.println("Работает прокси");
        return method.invoke(testClass, args);
    }
}
