package example.utils.impl;

import example.model.DummyObject;
import example.model.dto.ObjectWithServiceParameters;

public class ConverterToRequiredFormImpl implements DtoConverter {
    public ObjectWithServiceParameters convert(DummyObject object, String inputParam, String status){
        ObjectWithServiceParameters output = new ObjectWithServiceParameters();
        output.setData(object);
        output.setResponse(status);
        output.setRequest(inputParam);
        return output;
    }
}
