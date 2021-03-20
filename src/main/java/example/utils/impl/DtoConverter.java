package example.utils.impl;

import example.model.DummyObject;
import example.model.dto.ObjectWithServiceParameters;

public interface DtoConverter {
    public ObjectWithServiceParameters convert(DummyObject object, String inputParam, String status);
}
