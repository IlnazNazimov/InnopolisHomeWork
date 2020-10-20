package ru.innopolis.student.repository.api;

import javax.ejb.Local;
import java.util.Map;

/**
 * Интерфейс, предоставляющий переменные окружения среды.
 */
@Local
public interface Environment {

    /**
     * Возвращает полный список переменных окружения.
     *
     * @return - полный список переменных окружения.
     */
    Map<String, String> getAll();

    /**
     * Возращает значение по имени переменной.
     *
     * @param nameEnv - имя переменной среды.
     * @return - значение по имени переменной.
     */
    String get(String nameEnv);
}
