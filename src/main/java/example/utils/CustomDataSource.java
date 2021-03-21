package example.utils;

import javax.sql.DataSource;

/**
 * <code>CustomDataSource</code> interface provides instance of {@link DataSource} for connecting to database
 */
public interface CustomDataSource {
    /**
     * allow retrieves {@link DataSource} from external files
     * @return new instance of {@link DataSource}
     */
     DataSource getDataSource();
}
