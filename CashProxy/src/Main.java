import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        Service testService = new UseService();
        Service cacheTestService = (Service) Proxy.newProxyInstance(
                UseService.class.getClassLoader(),
                UseService.class.getInterfaces(),
                new CacheProxy(testService));
//        cacheTestService.doHardWork(4L);
//        cacheTestService.doHardWork(4L);
//        cacheTestService.doHardWork(6L);
//        cacheTestService.doHardWork(6L);
//        cacheTestService.doHardWork((long) 4);
//        cacheTestService.doHardWork(8L);
//        cacheTestService.doHardWork();
//        cacheTestService.doHardWork(8L);
//        cacheTestService.doHardWork();
//        cacheTestService.doHardWork(6L);
//        cacheTestService.doVeryHardWork(5, "Зря...");
//        cacheTestService.doVeryHardWork(5, "Зря...");
//        cacheTestService.doVeryHardWork(5, "Что-то изменилось?");
//        cacheTestService.doVeryHardWork(5, "Что-то изменилось?");
//        cacheTestService.doVeryHardWork(7, "Что-то изменилось?");
//        cacheTestService.doVeryHardWork(7, "Что-то изменилось?");
        cacheTestService.doWorkList(9);
        cacheTestService.doWorkList(9);

    }
}
