package ru.innopolis.student.rest;

import ru.innopolis.student.rest.services.RestService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * REST application для приложения.
 */
@ApplicationPath("/rest")
public class MyRestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(){{add(RestService.class);}};
    }
}
