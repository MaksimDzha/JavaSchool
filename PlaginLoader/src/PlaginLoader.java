import java.io.*;

public class PlaginLoader extends ClassLoader{

        // Путь к директории с плагинами
        private String pluginRootDirectory;

        public PlaginLoader(String pluginRootDirectory, ClassLoader parent) {
            super(parent);
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

        //Метод для поучения байт-кода
        private byte[] fetchClassFromFS(String path) throws FileNotFoundException, IOException {
            InputStream is = new FileInputStream(new File(path));

            // Get the size of the file
            long length = new File(path).length();

            if (length > Integer.MAX_VALUE) {
                // File is too large
            }

            // Create the byte array to hold the data
            byte[] bytes = new byte[(int)length];

            // Read in the bytes
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length
                    && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                offset += numRead;
            }

            // Ensure all the bytes have been read in
            if (offset < bytes.length) {
                throw new IOException("Could not completely read file "+path);
            }

            // Close the input stream and return bytes
            is.close();
            return bytes;
        }
}
