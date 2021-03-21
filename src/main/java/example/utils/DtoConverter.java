package example.utils;

import example.model.dto.ObjectWithServiceInfo;

/**
 * <code>DtoConverter</code> interface provides methods to convert any class to dto
 */
public interface DtoConverter {
    /**
     * converts one instance object to another with adding some important info
     * @param inputParams incoming data
     * @return ready object with service info
     */
     ObjectWithServiceInfo convert(String...inputParams);
}
