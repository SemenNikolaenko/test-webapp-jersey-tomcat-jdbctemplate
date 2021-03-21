package example.utils;

import org.springframework.jdbc.core.RowMapper;

/**
 * <code>CustomRowMapper</code> is interface which allows get any {@link RowMapper}
 */
public interface CustomRowMapper {
    /**
     * retrieve new instance of {@link RowMapper} for specific object
     * @return
     */
     RowMapper getRowMapper();
}
