package ru.innopolis.student.jsf.services;

import ru.innopolis.student.repository.api.Environment;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Сервис для предоставления переменных окружения среды для JFS страницы.
 */
@Named("envView")
@ApplicationScoped
public class EnvView {

    /**
     * Список переменных окружения среды.
     */
    private List<Map.Entry<String, String>> environments;

    /**
     * Компонент Environment. Внедряется bean контейнером.
     */
    @EJB
    private Environment env;

    /**
     * Инициализация списка переменных окружения среды.
     */
    @PostConstruct
    public void init() {
        environments = new ArrayList<>(env.getAll().entrySet());
    }

    public List<Map.Entry<String, String>> getEnvironments() {
        return environments;
    }
}
