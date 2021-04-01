package example.model;

import com.sun.xml.bind.v2.model.core.ID;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

/**
 * Object from database which store any info
 */
public class DummyObject {

    private Data data;
    private String status;
    private String request;

    public DummyObject(String status, String request) {
        this.status = status;
        this.request = request;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public DummyObject() {
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "DummyObject{" +
                "data=" + data +
                ", status='" + status + '\'' +
                ", request='" + request + '\'' +
                '}';
    }

    public String getRequest() {
        return request;
    }


    public String getStatus() {
        return status;
    }
}
