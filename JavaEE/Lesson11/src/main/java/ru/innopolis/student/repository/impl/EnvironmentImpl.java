package ru.innopolis.student.repository.impl;

import ru.innopolis.student.repository.api.Environment;

import javax.ejb.Stateless;
import java.util.Map;

/**
 * Реализация интерфеса Environment.
 * Является stateless компонентом EJB.
 */
@Stateless
public class EnvironmentImpl implements Environment {

    /**
     * Возвращает полный список переменных окружения.
     *
     * @return - полный список переменных окружения.
     */
    public Map<String, String> getAll() {
        return System.getenv();
    }

    /**
     * Возращает значение по имени переменной.
     *
     * @param nameEnv - имя переменной среды.
     * @return - значение по имени переменной.
     */
    public String get(String nameEnv) {
        return System.getenv(nameEnv);
    }
}
