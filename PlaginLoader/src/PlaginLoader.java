import java.io.*;

public class PlaginLoader extends ClassLoader {

    // Путь к директории с плагинами
    private String pluginRootDirectory;
    private final ClassLoader parent;

    public PlaginLoader(String pluginRootDirectory, ClassLoader parent) {
        super(parent);
        this.parent = parent;
        this.pluginRootDirectory = pluginRootDirectory;
    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            //Получаем байт-код из файла и загружаем класс в рантайм
            byte b[] = fetchClassFromFS(pluginRootDirectory + className + ".class");
            return defineClass(className, b, 0, b.length);
        } catch (FileNotFoundException ex) {
            return super.findClass(className);
        } catch (IOException ex) {
            return super.findClass(className);
        }
    }

    //Преобразование файла класса в байт-код для передачи в метод defineClass
    private byte[] fetchClassFromFS(String path) throws FileNotFoundException, IOException {
        InputStream is = new FileInputStream(new File(path));

        // Размер файла
        long length = new File(path).length();

//            if (length > Integer.MAX_VALUE) {
//                // Файл слишком большой
//            }

        // Байт-массив для данных
        byte[] bytes = new byte[(int) length];

        // Наполняем массив
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        // Убеждаемся, что прочитали весь файл
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + path);
        }

        // Закрываем файл
        is.close();
        return bytes;
    }


}
