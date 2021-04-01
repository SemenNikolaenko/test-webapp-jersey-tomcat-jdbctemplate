package example.controller;

import com.sun.jersey.json.impl.writer.JsonEncoder;
import example.exception.NoDatabaseConnectionException;
import example.exception.NoDataFoundException;
import example.exception.WrongInputParameterException;
import example.model.DummyObject;
import example.service.DummyObjectService;
import example.service.DummyObjectServiceImpl;
import example.utils.SqlQuerySupplier;
import example.utils.impl.CustomSqlQuerySupplier;

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

    //service which work with db not directly
    DummyObjectService dummyObjectService = new DummyObjectServiceImpl();


    @GET
    @Path("/getjson")
    @Produces(MediaType.APPLICATION_JSON)
    public DummyObject getReadyObjectFromDb(
            @QueryParam("id") String id
    ) {
        //future ready object
        DummyObject readyObject =handleAllExceptionForReadyObjectAndReturnResponse(id);
        return readyObject;
    }


    private DummyObject handleAllExceptionForReadyObjectAndReturnResponse(String...params) {
        //object from database
        DummyObject readyObject = null;
        try {

            readyObject = dummyObjectService.getObjectFromDbById(params);
            readyObject.setRequest(params[0]);
            readyObject.setStatus("ok");
            //catch exception which will be thrown when the program failed search config datasource, or search sql query
        } catch (NamingException e) {
            //created object with null inner object, and comprise only exception's text and input parameter
            //catch exception which will be thrown when the program don't find any information in database
            readyObject.setStatus(e.getMessage());
        } catch (NoDataFoundException e) {
            //created object with null inner object, and comprise only exception's text and input parameter
            //catch exception which will be thrown when input parameter is wrong
            readyObject.setStatus(e.getMessage());
        } catch (WrongInputParameterException e) {
            //created object with null inner object, and comprise only exception's text and input parameter
            //created object with null inner object, and comprise only exception's text and input parameter
            readyObject.setStatus(e.getMessage());
        }

        return readyObject;
    }
}
