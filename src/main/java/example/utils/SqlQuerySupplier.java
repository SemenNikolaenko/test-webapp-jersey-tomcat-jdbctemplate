package example.utils;

import javax.naming.NamingException;

public interface SqlQuerySupplier {
    public String getQueryForGetObject() throws NamingException;
}
