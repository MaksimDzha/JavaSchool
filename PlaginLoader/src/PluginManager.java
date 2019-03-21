import java.io.File;

public class PluginManager {

    private final String pluginRootDirectory;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public void load(String pluginName, String pluginClassName) {

        //Создаем загрузчик плагинов
        PlaginLoader loader = new PlaginLoader(pluginRootDirectory, ClassLoader.getSystemClassLoader());

        //Получаем список доступных плагинов
        File dir = new File(pluginRootDirectory);
        String[] plagins = dir.list();

        //Ищем и загружаем нужный плагин
        for (String plagin : plagins) {
            try {
                String plaginTempName = plagin.split(".class")[0];
                Class clazz = loader.loadClass(plaginTempName);
//                System.out.println(clazz.toString());
                if (plaginTempName.equals(pluginName) && (clazz.toString().equals(pluginClassName))) {
                    Plagin execute = (Plagin) clazz.newInstance();
                    System.out.println(execute.getClass().getClassLoader().toString());
                    execute.load();
                    execute.unload();

                    break;
                }
            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
            } catch (InstantiationException e) {
//                e.printStackTrace();
            } catch (IllegalAccessException e) {
//                e.printStackTrace();
            } catch (ClassCastException e) {
//                e.printStackTrace();
            }
        }
    }
}
