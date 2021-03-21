package example.utils.impl;

import example.model.DummyObject;
import example.model.dto.ObjectWithServiceInfo;
import example.utils.DtoConverter;

/**
 * <code>ConverterToRequiredFormImpl</code> is implementation of {@link DtoConverter}
 * class allows convert object from database to dto object with service info
 */
public class ConverterToRequiredFormImpl implements DtoConverter {
    private Object innerObject;
    private String status;

    public ConverterToRequiredFormImpl(Object innerObject, String status) {
        this.innerObject = innerObject;
        this.status = status;
    }

    public ObjectWithServiceInfo convert(String... inputParams) {
        ObjectWithServiceInfo output = new ObjectWithServiceInfo();
        output.setData((DummyObject) innerObject);
        //add response status and input parameter
        output.setResponse(status);
        output.setRequest(inputParams[0]);
        return output;
    }
}
