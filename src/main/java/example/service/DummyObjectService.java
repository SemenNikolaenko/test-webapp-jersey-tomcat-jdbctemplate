package example.service;

import example.model.DummyObject;

import javax.naming.NamingException;

public interface DummyObjectService {
    public DummyObject getObjectFromDbById(String id) throws NamingException;
}
