package example.repository.impl;

import example.exception.NoDatabaseConnectionException;
import example.exception.NoDataFoundException;
import example.exception.WrongInputParameterException;
import example.model.DummyObject;
import example.repository.DummyObjectDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <code>DummyObjectDaoImpl</code> is implementation of {@link DummyObjectDao}
 * implements all methods to interaction with database
 */
public class DummyObjectDaoImpl implements DummyObjectDao {
    private JdbcTemplate jdbcTemplate;
    //supplier of custom RowMapper which can create new DummyObject from database
    //RowMapper which will be used to create object
    private RowMapper<DummyObject> rowMapper;

    public DummyObjectDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Map<String, Object>> getObjectFromDbById(String sqlQuery, String... params) {
        //list contains all data which were injected from database
        List<Map<String, Object>> dataFromBd = new ArrayList<Map<String, Object>>();

        try {
            SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sqlQuery, params);

            SqlRowSetMetaData metaData = sqlRowSet.getMetaData();
            String[] columnNames = metaData.getColumnNames();
            //set row cursor before first element
            sqlRowSet.beforeFirst();
            //while extracted data is present, moved and retrieves information
            while (sqlRowSet.next()){
                Map<String, Object> map = new HashMap<String, Object>();
                //every row will save in separate map
                for (int i = 0; i < columnNames.length; i++) {

                    map.put(columnNames[i],sqlRowSet.getObject(columnNames[i]));
                    dataFromBd.add(map);
                }
            }
        }
        //if after request data wasn't found, throws new exception
        catch (EmptyResultDataAccessException e) {
            throw new NoDataFoundException("No one entry was found");
        }
        //if input parameter was wrong throws new exception
        catch (BadSqlGrammarException e) {
            throw new WrongInputParameterException("Wrong input parameter");
        } catch (CannotGetJdbcConnectionException e) {
            throw new NoDatabaseConnectionException("Connection to database was fail");
        }
        return dataFromBd;
    }
}
