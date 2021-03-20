package example.utils.impl;

import example.model.DummyObject;
import example.utils.CustomRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapperForDummyObject implements CustomRowMapper {
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
