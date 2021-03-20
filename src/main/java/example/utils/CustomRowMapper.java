package example.utils;

import org.springframework.jdbc.core.RowMapper;

public interface CustomRowMapper {
    public RowMapper getRowMapper();
}
