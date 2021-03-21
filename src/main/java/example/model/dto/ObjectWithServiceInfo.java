package example.model.dto;

import example.model.DummyObject;

/**
 * Dto class which can provide object with additional service info
 */
public class ObjectWithServiceInfo {
    private DummyObject data;
    private String response;
    private String request;

    public ObjectWithServiceInfo() {
    }

    public DummyObject getData() {
        return data;
    }

    public void setData(DummyObject dummyObject) {
        this.data = dummyObject;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String requestParam) {
        this.request = requestParam;
    }
}
