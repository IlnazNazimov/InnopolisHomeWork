import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) {
        try {
            byte[] bytesClass = Files.readAllBytes(Paths.get(name));
            return defineClass("SomeClass", bytesClass, 0, bytesClass.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
