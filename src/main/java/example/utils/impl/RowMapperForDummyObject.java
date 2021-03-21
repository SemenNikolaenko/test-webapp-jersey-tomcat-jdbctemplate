package example.utils.impl;

import example.model.DummyObject;
import example.utils.CustomRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <code>RowMapperForDummyObject</code> is implementation of {@link CustomRowMapper}
 * retrieve {@link RowMapper} for DummyObject
 */
public class RowMapperForDummyObject implements CustomRowMapper {
    /**
     * create new {@link RowMapper} for DummyObject
     *
     * @return new instance of  {@link RowMapper}
     */
    @Override
    public RowMapper getRowMapper() {
        return new RowMapper() {
            @Override
            public DummyObject mapRow(ResultSet resultSet, int i) throws SQLException {
                return new DummyObject(resultSet.getInt("id"), resultSet.getString("value"));

            }
        };
    }
}
