package example.utils;

import javax.naming.NamingException;

/**
 * <code>SqlQuerySupplier</code> is interface allows get some sql from any external file
 */
public interface SqlQuerySupplier {
    /**
     * method allows retrieve DML select query from external file
     * @return String with query
     * @throws NamingException if file not found
     */
     String getQueryForGetObject() throws NamingException;
}
