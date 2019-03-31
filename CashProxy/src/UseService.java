import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

//Реализация интерфейса Service

public class UseService implements Service, Serializable {

    private long result;

    @Override
    @Cache(value = StorageType.IN_FILES)
    public long doHardWork(long time) {
        System.out.print("Сервис запущен главным приложением. Время выполнения: ");
        long t1 = System.currentTimeMillis();
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long t2 = System.currentTimeMillis();
        result = (t2 - t1) / 1000;
        System.out.println(result + " сек.");

        return result;
    }

    @Override
    @Cache(value = StorageType.IN_FILES)
    public long doHardWork() {
        System.out.print("Сервис запущен главным приложением. Время выполнения: ");
        long t1 = System.currentTimeMillis();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long t2 = System.currentTimeMillis();
        result = (t2 - t1) / 1000;
        System.out.println(result + " сек.");
        return result;
    }

    @Override
    @Cache(value = StorageType.IN_FILES)
    public long doVeryHardWork(long time, String message) {
        System.out.println("Долгий сервис запущен главным приложением. Сообщение: " + message);
        System.out.print("Время работы: ");
        long t1 = System.currentTimeMillis();
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long t2 = System.currentTimeMillis();
        result = (t2 - t1) / 1000;
        System.out.println(result + " сек.");
        return result;
    }

    @Override
    @Cache(value = StorageType.IN_MEMORY, countElement = 10)
    public Object[] doWorkList(int count) {
        Object[] list = new Object[count];
        for (int i=0; i < count; i++)
            list[i] = i;
        System.out.println("Количество элементов массива в расчете = " + list);
        return list;
    }


//    public long getResult() {
//        return result;
//    }
}
