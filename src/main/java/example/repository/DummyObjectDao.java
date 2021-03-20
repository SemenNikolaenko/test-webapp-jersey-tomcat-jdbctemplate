package example.repository;

import example.model.DummyObject;

public interface DummyObjectDao {
    public DummyObject getObjectFromDbById(String query, String idParam);
}
