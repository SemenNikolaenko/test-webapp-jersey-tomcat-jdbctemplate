package example.utils.impl;

import example.utils.SqlQuerySupplier;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * <code>CustomSqlQuerySupplier</code> is implementation of {@link SqlQuerySupplier}
 * allows retrieve sql query from web.xml file
 */
public class CustomSqlQuerySupplier implements SqlQuerySupplier {
    /**
     * retrieves DML query for select any object from database
     *
     * @return string with sql query's body
     * @throws NamingException if required file not found
     */
    @Override
    public String getQueryForGetObject() throws NamingException {
        Context context = new InitialContext();
        String requiredQuery = String.valueOf(context.lookup("java:comp/env/sql-query"));
        return requiredQuery;
    }
}
