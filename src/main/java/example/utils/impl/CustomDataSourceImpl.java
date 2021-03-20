package example.utils.impl;

import example.utils.CustomDataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;

public class CustomDataSourceImpl implements CustomDataSource {

    @Override
    public DataSource getDataSource() {
        JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
        DataSource dataSource = jndiDataSourceLookup.getDataSource("java:comp/env/jdbc/pool");
        return dataSource;
    }
}
