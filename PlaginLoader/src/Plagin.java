//Интерфейс для всех плагинов

public interface Plagin {

    public static final int EXIT_SUCCESS = 0;
    public static final int EXIT_FAILURE = 1;

    public void load();
    public void unload();

}
