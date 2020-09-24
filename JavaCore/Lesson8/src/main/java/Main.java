
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {

    public static final String CLASS_NAME_JAVA = "SomeClass.java";
    public static final String CLASS_NAME_CLASS = "SomeClass.class";
    public static final String METHOD_NAME = "doWork";
    public static final String PATH = "./src/main/java/";

    public static void main(String[] args) {
        StringBuilder stringBuilder = getTextClass();

        createNewJavaFile(stringBuilder);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, PATH + CLASS_NAME_JAVA);

        Class<?> clazz = new MyLoader().findClass(PATH + CLASS_NAME_CLASS);

        runMethod(clazz);
    }

    private static void runMethod(Class<?> clazz) {
        try {
            Method method = clazz.getMethod(METHOD_NAME);
            Object object = clazz.newInstance();
            method.invoke(object);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void createNewJavaFile(StringBuilder stringBuilder) {
        File file = new File(PATH + CLASS_NAME_JAVA);

        try (BufferedWriter buf = new BufferedWriter(new FileWriter(file))) {
            buf.write(stringBuilder.toString());
            file.createNewFile();
            buf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static StringBuilder getTextClass() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();

        String s;
        stringBuilder.append("public class SomeClass implements Worker {\n\t");
        stringBuilder.append("public void doWork() {\n\t\t");

        while (!(s = scanner.nextLine()).equals(" ")) {
            stringBuilder.append(s);
        }

        stringBuilder.append("\n\t}");
        stringBuilder.append("\n}");
        System.out.println(stringBuilder);

        return stringBuilder;
    }
}
