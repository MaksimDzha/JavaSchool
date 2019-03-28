import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        Service testService = new UseService();
//        testService.doHardWork(2L);
        Service cacheTestService = (Service) Proxy.newProxyInstance(
                UseService.class.getClassLoader(),
                UseService.class.getInterfaces(),
                new CacheProxy(testService));

        cacheTestService.doHardWork(4L);
        cacheTestService.doHardWork(4L);
//        cacheTestService.doHardWork(6L);
//        cacheTestService.doHardWork(6L);
//        cacheTestService.doHardWork((long) 4);
//        cacheTestService.doHardWork(6L);
        cacheTestService.doHardWork();
//        cacheTestService.doHardWork(6L);
//        cacheTestService.doHardWork();

//        cacheTestService.doHardWork(6L);
//        Object result = new Object();
//        try{
//            FileInputStream fis = new FileInputStream("Temp.res");
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            result = (UseService) ois.readObject();
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println(result.getClass());
//        System.out.println(((UseService) result).getResult());
//        System.out.println(((UseService) testService).getResult());
    }
}
