package ua.allugard.hotel.model.dao.util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by allugard on 01.07.17.
 */
public interface DatabaseConnection extends AutoCloseable {

        void startTransaction();
        void commit();
        void rollback();
        void close();
        Connection getConnection();

}
