package example.repository;

import example.model.DummyObject;

/**
 * <code>DummyObjectDao</code> is interface which provides methods to interaction with database
 */
public interface DummyObjectDao {
    public DummyObject getObjectFromDbById(String query, String idParam);
}
