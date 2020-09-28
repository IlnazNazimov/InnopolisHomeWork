import javassist.CannotCompileException;
import javassist.ClassPool;

import java.util.ArrayList;

public class Main {
    static ClassPool classPool = ClassPool.getDefault();

    public static void main(String[] args) throws Exception {
        //getOutOfMemoryMetaspase();
        //getOutOfMemory();
    }

    /**
     * Получает ошибку java.lang.OutOfMemoryError : Metaspace
     * @throws CannotCompileException
     */
    private static void getOutOfMemoryMetaspase() throws CannotCompileException {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            Class clas = classPool.makeClass(
                    i + " outofmemory.OutOfMemoryErrorMetaspace ").toClass();
            System.out.println(clas.getName());
        }
    }

    /**
     * Получает ошибку java.lang.OutOfMemoryError : Java heap space
     */
    private static void getOutOfMemory() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("1");

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String s = list.get(i);
            list.add(s);
        }
    }
}

