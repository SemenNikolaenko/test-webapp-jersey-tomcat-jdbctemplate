package example.utils.impl;

import example.model.DummyObject;
import example.model.dto.ObjectWithServiceInfo;
import example.utils.DtoConverter;

/**
 * <code>ConverterToRequiredFormImpl</code> is implementation of {@link DtoConverter}
 * class allows convert object from database to dto object with service info
 */
public class ConverterToRequiredFormImpl implements DtoConverter {
    public ObjectWithServiceInfo convert(Object object, String status, String... inputParams) {
        ObjectWithServiceInfo output = new ObjectWithServiceInfo();
        output.setData((DummyObject) object);
        //add response status and input parameter
        output.setResponse(status);
        output.setRequest(inputParams[0]);
        return output;
    }
}
