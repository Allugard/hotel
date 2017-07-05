package ua.allugard.hotel.model.dao.util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by allugard on 01.07.17.
 */
public class JdbcConnection implements DatabaseConnection {
    private Connection connection;
    private boolean inTransaction;

    public JdbcConnection(Connection connection) {
        this.connection = connection;
        inTransaction = false;
    }

    @Override
    public void startTransaction() {
        try {
            connection.setAutoCommit(false);
            inTransaction = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
            inTransaction = false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
            inTransaction = false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        if (inTransaction) {
            rollback();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
