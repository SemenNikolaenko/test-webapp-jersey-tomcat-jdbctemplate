package example.controller;

import example.exception.NoDatabaseConnectionException;
import example.exception.NoDataFoundException;
import example.exception.WrongInputParameterException;
import example.model.DummyObject;
import example.model.dto.ObjectWithServiceInfo;
import example.service.DummyObjectService;
import example.service.DummyObjectServiceImpl;
import example.utils.impl.ConverterToRequiredFormImpl;
import example.utils.DtoConverter;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Rest controller which can send response on request.
 * response consist from object which was retrieved from database
 * and some service info
 */
@Path("/bykey")
public class EntityRestController {
    //converter which help wrap object from db and add some service info
    DtoConverter converter;
    //service which work with db not directly
    DummyObjectService dummyObjectService = new DummyObjectServiceImpl();

    /**
     * method receive get request with param , and send in response ready object
     * or object with exception text, processing of exception provides method{@link EntityRestController#handleAllExceptionForReadyObjectAndReturnResponse(String)}
     * Activated when customer send get request to path localhost:8080/db2any/bykey/getjson
     *
     * @param id receive object parameter, and with it help retrieve ready object from database
     * @return ready object with all fields
     */
    @GET
    @Path("/getjson")
    @Produces(MediaType.APPLICATION_JSON)
    public ObjectWithServiceInfo getReadyObjectFromDb(
            @QueryParam("id") String id
    ) {
        //future ready object
        ObjectWithServiceInfo readyObject;
        //invokes private method which may handles probable exception
        readyObject = handleAllExceptionForReadyObjectAndReturnResponse(id);

        return readyObject;
    }

    /**
     * Inner method  designed to handling any exception occurs in process composing ready object from database
     *
     * @param id receive object parameter, and with it help retrieve ready object from database
     * @return ready object with all fields
     */
    private ObjectWithServiceInfo handleAllExceptionForReadyObjectAndReturnResponse(String id) {
        //object from database
        DummyObject object = null;
        ObjectWithServiceInfo readyObject = null;
        try {
            object = dummyObjectService.getObjectFromDbById(id);
            converter = new ConverterToRequiredFormImpl(object, "ok");
            //catch exception which will be thrown when the program failed search config datasource, or search sql query
        } catch (NamingException e) {
            //created object with null inner object, and comprise only exception's text and input parameter
            converter = new ConverterToRequiredFormImpl(null, e.getMessage());
            //catch exception which will be thrown when the program don't find any information in database
        } catch (NoDataFoundException e) {
            //created object with null inner object, and comprise only exception's text and input parameter
            converter = new ConverterToRequiredFormImpl(null, e.getMessage());
            //catch exception which will be thrown when input parameter is wrong
        } catch (WrongInputParameterException e) {
            //created object with null inner object, and comprise only exception's text and input parameter
            converter = new ConverterToRequiredFormImpl(null, e.getMessage());
            //created object with null inner object, and comprise only exception's text and input parameter
        } catch (NoDatabaseConnectionException e) {
            converter = new ConverterToRequiredFormImpl(null, e.getMessage());
        }
        readyObject = converter.convert(id);
        return readyObject;
    }
}
