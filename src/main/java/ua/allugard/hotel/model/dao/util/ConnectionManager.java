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
    private static ThreadLocal<JdbcConnection> connectionThreadLocal = new ThreadLocal<>();

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

    public synchronized JdbcConnection getConnection() {
        if(connectionThreadLocal.get() == null) {
            try {
                return new JdbcConnection(dataSource.getConnection());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            return connectionThreadLocal.get();
        }
    }

    public void startTransaction(){
        JdbcConnection connection = getConnection();
        connection.startTransaction();
        connectionThreadLocal.set(connection);
    }
    public void commit(){
        JdbcConnection connection = connectionThreadLocal.get();
        connection.commit();
        close(connection);
    }

    public void rollback(){
        JdbcConnection connection = connectionThreadLocal.get();
        connection.rollback();
        close(connection);
    }

    private void close(JdbcConnection connection){
        connectionThreadLocal.remove();
        connection.close();
    }

}
