import java.lang.reflect.Field;
import java.util.*;

public class Main {
    public static void main(String[] args) {

    }

    public static void cleanup(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput) {
        Class<?> clas = object.getClass();

        if (!(object instanceof Map)) {
            chekSetFieldsNames(fieldsToCleanup, fieldsToOutput, clas);

            for (Field field : clas.getDeclaredFields()) {
                field.setAccessible(true);

                setToNullAllField(object, fieldsToCleanup, field);

                outputSetPrint(object, fieldsToOutput, field);
            }
        } else {
            Map<?, ?> map = (Map<?, ?>) object;
            for (String s : fieldsToOutput) {
                if (!map.containsKey(s)) {
                    throw new IllegalArgumentException("Поле не найдено");
                }
            }
            deleteKeysFromMap(fieldsToCleanup, map);
            printValuesFromMap(fieldsToOutput, map);
        }
    }

    private static void chekSetFieldsNames(Set<String> fieldsToCleanup, Set<String> fieldsToOutput, Class<?> clas) {
        List<Field> fields = Arrays.asList(clas.getDeclaredFields());
        Set<String> fieldNames = new HashSet<>();

        fields.forEach(v -> fieldNames.add(v.getName()));

        if (!fieldNames.containsAll(fieldsToCleanup) || !fieldNames.containsAll(fieldsToOutput)) {
            throw new IllegalArgumentException("Не найдено поле");
        }
    }

    private static void printValuesFromMap(Set<String> fieldsToOutput, Map<?, ?> map) {
        fieldsToOutput.forEach(v -> {
            if (map.get(v) != null) {
                System.out.println(map.get(v));
            }
        });

    }

    private static void deleteKeysFromMap(Set<String> fieldsToCleanup, Map<?, ?> map) {
        fieldsToCleanup.forEach(map::remove);
    }

    private static void setToNullAllField(Object object, Set<String> fieldsToCleanup, Field field) {
        for (String nameField : fieldsToCleanup) {
            if (field.getName().equals(nameField)) {
                try {
                    if (field.getType().isPrimitive()) {
                        switch (field.getType().toString()) {
                            case "String":
                                field.set(object, "");
                                break;
                            case "char":
                                field.set(object, Character.MIN_VALUE);
                                break;
                            case "boolean":
                                field.set(object, false);
                                break;
                            default:
                                field.set(object, 0);
                        }
                    } else {
                        field.set(object, null);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void outputSetPrint(Object object, Set<String> fieldsToOutput, Field field) {
        try {
            for (String nameField : fieldsToOutput) {
                if (field.getName().equals(nameField)) {
                    System.out.println(field.get(object));
                    break;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
