package example.service;

import example.model.Data;
import example.model.DummyObject;
import example.repository.DummyObjectDao;
import example.repository.impl.DummyObjectDaoImpl;
import example.utils.CustomDataSource;

import example.utils.SqlQuerySupplier;
import example.utils.impl.CustomSqlQuerySupplier;
import example.utils.impl.DataSourceFromContext;

import javax.naming.NamingException;
import java.util.List;
import java.util.Map;

/**
 * <code>DummyObjectServiceImpl</code> implementation of {@link DummyObjectService}
 * implements all methods to interaction with dao layer and add some extra function
 */
public class DummyObjectServiceImpl implements DummyObjectService {
    //dao layer class
    DummyObjectDao dummyObjectDao;
    //service which provides DataSource from external file
    CustomDataSource customDataSource;
    //service which provides required sql query
    SqlQuerySupplier sqlQuerySupplier = new CustomSqlQuerySupplier();


    public DummyObjectServiceImpl() {
        this.customDataSource = new DataSourceFromContext();
        //creating new dao layer, and providing it with datasource and RowMapper
        this.dummyObjectDao = new DummyObjectDaoImpl(customDataSource.getDataSource());

    }


    @Override
    public DummyObject getObjectFromDbById(String... params) throws NamingException {
        DummyObject dummyObject = new DummyObject();
        Data data = new Data();
        //extracts sql query from file
        String sqlQuery = sqlQuerySupplier.getQueryForGetObject();
        //extracts parameters from database
        List<Map<String, Object>> resultFromDatabase = dummyObjectDao.getObjectFromDbById(sqlQuery, params);
        //set parameters which were injected from database
        data.setId(getIntFromObject(resultFromDatabase.get(0).get("ID")));
        data.setValue(getStringFromObject(resultFromDatabase.get(0).get("VALUE")));
        dummyObject.setData(data);
        return dummyObject;
    }

    //helps convert object from Object type to int
    private int getIntFromObject(Object ob) {
        if (ob instanceof Integer)
            return (Integer) ob;
        else throw new RuntimeException();
    }

    //helps convert object from Object type to String
    private String getStringFromObject(Object ob) {
        if (ob instanceof String)
            return String.valueOf(ob);
        else throw new RuntimeException();
    }


}
