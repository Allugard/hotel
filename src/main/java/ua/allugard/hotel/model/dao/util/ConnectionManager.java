package ua.allugard.hotel.model.dao.util;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by allugard on 01.07.17.
 */
public class ConnectionManager {

    private DataSource dataSource;

    private ConnectionManager() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/hotelDB");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static class Holder {
        static final ConnectionManager INSTANCE = new ConnectionManager();
    }

    public static ConnectionManager getInstance() {
        return Holder.INSTANCE;
    }

    public synchronized DatabaseConnection getConnection() {
        try {
            return new JdbcConnection(dataSource.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
