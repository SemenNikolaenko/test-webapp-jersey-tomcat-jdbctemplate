package example.utils.impl;

import example.utils.CustomDataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;

/**
 * <code>DataSourceFromContext</code> is a class which return {@link DataSource} from context.xml file
 * implements {@link CustomDataSource}
 */
public class DataSourceFromContext implements CustomDataSource {
    /**
     * retrieve {@link DataSource} through using {@link JndiDataSourceLookup}
     *
     * @return
     */
    @Override
    public DataSource getDataSource() {
        JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
        DataSource dataSource = jndiDataSourceLookup.getDataSource("java:comp/env/jdbc/pool");
        return dataSource;
    }
}
