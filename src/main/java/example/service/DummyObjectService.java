package example.service;

import example.model.DummyObject;

import javax.naming.NamingException;

/**
 * <code>DummyObjectService</code> is interface which allow interact with dao layer
 */
public interface DummyObjectService {
    public DummyObject getObjectFromDbById(String id) throws NamingException;
}
