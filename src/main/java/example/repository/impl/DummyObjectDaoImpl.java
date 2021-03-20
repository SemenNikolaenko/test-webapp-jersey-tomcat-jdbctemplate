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

public class DummyObjectDaoImpl implements DummyObjectDao {
    private JdbcTemplate jdbcTemplate;
    private final CustomRowMapper  customRowMapper;
    private RowMapper<DummyObject> rowMapper;

    public DummyObjectDaoImpl(DataSource dataSource, CustomRowMapper customRowMapper) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.customRowMapper = customRowMapper;
        rowMapper=customRowMapper.getRowMapper();
    }

    @Override
    public DummyObject getObjectFromDbById(String sqlQueryWithoutParam, String  idParam) {
        String sqlQueryWithParam = sqlQueryWithoutParam.replace("?",idParam);
        DummyObject objectFromDb;
        try {
             objectFromDb = jdbcTemplate.queryForObject(sqlQueryWithParam, rowMapper);
        }
        catch (EmptyResultDataAccessException e){
            throw new NoDataFoundException("No one entry was found");
        }
        catch (BadSqlGrammarException e){
            throw new WrongInputParameterException("Wrong input parameter");
        }
        return objectFromDb;
    }
}
