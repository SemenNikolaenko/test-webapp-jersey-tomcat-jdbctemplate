package example.service;

import example.model.DummyObject;
import example.repository.DummyObjectDao;
import example.repository.impl.DummyObjectDaoImpl;
import example.utils.CustomDataSource;
import example.utils.SqlQuerySupplier;
import example.utils.impl.CustomDataSourceImpl;
import example.utils.impl.CustomSqlQuerySupplier;
import example.utils.impl.RowMapperForDummyObject;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.naming.NamingException;


public class DummyObjectServiceImpl implements DummyObjectService {

    DummyObjectDao dummyObjectDao;
    CustomDataSource customDataSource;
    SqlQuerySupplier sqlQuerySupplier;

    public DummyObjectServiceImpl() {
        this.customDataSource = new CustomDataSourceImpl();
        this.dummyObjectDao = new DummyObjectDaoImpl(customDataSource.getDataSource(), new RowMapperForDummyObject());
        this.sqlQuerySupplier = new CustomSqlQuerySupplier();
    }

    @Override
    public DummyObject getObjectFromDbById(String id) throws NamingException {
        DummyObject objectFromDb = null;
        String sqlQuery = null;

            sqlQuery = sqlQuerySupplier.getQueryForGetObject();

        objectFromDb = dummyObjectDao.getObjectFromDbById(sqlQuery, id);

        return objectFromDb;
    }
}
