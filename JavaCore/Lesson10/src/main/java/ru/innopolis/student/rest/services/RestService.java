package ru.innopolis.student.rest.services;


import ru.innopolis.student.repository.api.Environment;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.Optional;

import static javax.ws.rs.core.Response.*;

/**
 * REST сервис для предоставления списка переменных среды.
 */
@Stateless
@Path("/environments")
public class RestService {

    /**
     * Внедрение компонента environment
     */
    @EJB
    private Environment environment;

    /**
     * Возвращает json список переменных окружения на GET запрос https://locahost:8080/rest/environments.
     * @return - список переменных окружения.
     */
    @GET
    @Produces("application/json")
    public Map<String, String> getEnvironments() {
        return environment.getAll();
    }

    /**
     * Возвращает строку значения переменной окружения срежы по имени на GET запрос
     * https://locahost:8080/rest/environments/{name}. Если такого имени нет среди переменных
     * возвращает код ошибки - 404.
     * @param nameEnv - имя переменной.
     * @return - строку значения переменной окружения среды или статус код 404.
     */
    @GET
    @Path("{name}")
    public Response getByName(@PathParam("name") String nameEnv) {
         return Optional.ofNullable(environment.get(nameEnv))
                 .flatMap(result -> Optional.of(status(Status.OK).entity(result).build()))
                 .orElse(status(Status.NOT_FOUND).build());
    }

}
