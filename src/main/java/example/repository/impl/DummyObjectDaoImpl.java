package example.repository.impl;

import example.exception.NoDataFoundException;
import example.exception.WrongInputParameterException;
import example.model.DummyObject;
import example.repository.DummyObjectDao;
import example.utils.CustomRowMapper;
import example.utils.impl.RowMapperForDummyObject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

/**
 * <code>DummyObjectDaoImpl</code> is implementation of {@link DummyObjectDao}
 * implements all methods to interaction with database
 */
public class DummyObjectDaoImpl implements DummyObjectDao {
    private JdbcTemplate jdbcTemplate;
    //supplier of custom RowMapper which can create new DummyObject from database
    private final CustomRowMapper customRowMapper;
    //RowMapper which will be used to create object
    private RowMapper<DummyObject> rowMapper;

    public DummyObjectDaoImpl(DataSource dataSource, CustomRowMapper customRowMapper) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.customRowMapper = customRowMapper;
        rowMapper = customRowMapper.getRowMapper();
    }

    /**
     * method retrieve DummyObject from database, or throw appropriate exception
     *
     * @param sqlQueryWithoutParam ready sql query from web.xml
     * @param idParam              id belonging to required object
     * @return object from database
     */
    @Override
    public DummyObject getObjectFromDbById(String sqlQueryWithoutParam, String idParam) {
        //substitutes "?" in sql request with parameter which has sent in request
        String sqlQueryWithParam = sqlQueryWithoutParam.replace("?", idParam);
        DummyObject objectFromDb;
        try {
            objectFromDb = jdbcTemplate.queryForObject(sqlQueryWithParam, rowMapper);
        }
        //if after request data wasn't found, throws new exception
        catch (EmptyResultDataAccessException e) {
            throw new NoDataFoundException("No one entry was found");
        }
        //if input parameter was wrong throws new exception
        catch (BadSqlGrammarException e) {
            throw new WrongInputParameterException("Wrong input parameter");
        }
        return objectFromDb;
    }
}
