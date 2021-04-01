package example.repository;

import example.model.DummyObject;

import java.util.List;
import java.util.Map;

/**
 * <code>DummyObjectDao</code> is interface which provides methods to interaction with database
 */
public interface DummyObjectDao {
    public List<Map<String, Object>> getObjectFromDbById(String sqlQuery, String...params);
}
