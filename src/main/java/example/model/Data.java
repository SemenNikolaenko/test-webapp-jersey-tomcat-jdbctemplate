package example.model;

import javax.xml.bind.annotation.XmlElement;

public class Data {
    @XmlElement(name = "ID")
    private int id;
    @XmlElement(name = "VALUE")
    private String value;


    public Data(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public Data() {
    }

//    public void setId(Object id) {
//        if (id instanceof Integer)
//            this.id=(Integer) id;
//    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

//    public void setValue(Object value) {
//      if (value instanceof String) {
//          this.value=String.valueOf(value);
//      }
//
//    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
