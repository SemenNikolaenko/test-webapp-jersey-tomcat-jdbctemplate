package example.service;

import example.model.DummyObject;
import example.repository.DummyObjectDao;
import example.repository.impl.DummyObjectDaoImpl;
import example.utils.CustomDataSource;
import example.utils.SqlQuerySupplier;
import example.utils.impl.CustomSqlQuerySupplier;
import example.utils.impl.DataSourceFromContext;
import example.utils.impl.RowMapperForDummyObject;

import javax.naming.NamingException;

/**
 * <code>DummyObjectServiceImpl</code> implementation of {@link DummyObjectDao}
 * implements all methods to interaction with dao layer and add some extra function
 */
public class DummyObjectServiceImpl implements DummyObjectService {
    //dao layer class
    DummyObjectDao dummyObjectDao;
    //service which provides DataSource from external file
    CustomDataSource customDataSource;
    //service which provides required sql query
    SqlQuerySupplier sqlQuerySupplier;

    public DummyObjectServiceImpl() {
        this.customDataSource = new DataSourceFromContext();
        //creating new dao layer, and providing it with datasource and RowMapper
        this.dummyObjectDao = new DummyObjectDaoImpl(customDataSource.getDataSource(), new RowMapperForDummyObject());
        this.sqlQuerySupplier = new CustomSqlQuerySupplier();
    }

    /**
     * method allow retrieve ready object from database
     * use immutable sql from external file
     *
     * @param id required object's id
     * @return ready object from  database
     * @throws NamingException if parameters from external files weren't found
     */
    @Override
    public DummyObject getObjectFromDbById(String id) throws NamingException {
        DummyObject objectFromDb = null;
        String sqlQuery = null;
        //retrieve immutable sql query from external file
        sqlQuery = sqlQuerySupplier.getQueryForGetObject();

        objectFromDb = dummyObjectDao.getObjectFromDbById(sqlQuery, id);

        return objectFromDb;
    }
}
