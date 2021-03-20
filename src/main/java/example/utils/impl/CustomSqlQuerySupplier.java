package example.utils.impl;

import example.utils.SqlQuerySupplier;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CustomSqlQuerySupplier implements SqlQuerySupplier {
    @Override
    public String getQueryForGetObject() throws NamingException {
        Context context = new InitialContext();
        String requiredQuery = String.valueOf(context.lookup("java:comp/env/sql-query"));
        return requiredQuery;
    }
}
