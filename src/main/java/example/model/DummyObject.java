package example.model;

/**
 * Object from database which store any info
 */
public class DummyObject {
    private int id;
    private String name;

    public DummyObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public DummyObject() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DummyObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
