package example.controller;

import example.exception.NoDataFoundException;
import example.exception.WrongInputParameterException;
import example.model.DummyObject;
import example.model.dto.ObjectWithServiceParameters;
import example.service.DummyObjectService;
import example.service.DummyObjectServiceImpl;
import example.utils.impl.ConverterToRequiredFormImpl;
import example.utils.impl.DtoConverter;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/bykey")
public class EntityRestController {

    DtoConverter converter = new ConverterToRequiredFormImpl();
    DummyObjectService dummyObjectService = new DummyObjectServiceImpl();


    @GET
    @Path("/getjson")
    @Produces(MediaType.APPLICATION_JSON)
    public ObjectWithServiceParameters getReadyObjectFromDb(
            @QueryParam("id") String id
    ) {
        ObjectWithServiceParameters readyObject;
        readyObject = handleAllExceptionForReadyObjectAndReturnResponse(id);

        return readyObject;
    }

    public ObjectWithServiceParameters handleAllExceptionForReadyObjectAndReturnResponse(String id) {
        DummyObject object;
        ObjectWithServiceParameters readyObject;
        try {
            object = dummyObjectService.getObjectFromDbById(id);
            readyObject = converter.convert(object, id, "ok");
        } catch (NamingException e) {
            readyObject = converter.convert(null, id, e.getMessage());
        } catch (NoDataFoundException e) {
            readyObject = converter.convert(null, id, e.getMessage());
        } catch (WrongInputParameterException e) {
            readyObject = converter.convert(null, id, e.getMessage());
        }
        return readyObject;
    }
}
