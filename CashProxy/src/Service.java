import java.util.*;

//Интерфейс для подключения CacheProxy

interface Service {

    long doHardWork(long time);
    long doHardWork();
    long doVeryHardWork(long time, String message);
//    Set<? super Number> doWorkList(int count);
    Object[] doWorkList(int count);
}
