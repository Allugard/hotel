package ua.allugard.hotel.model.dao.util;


import ua.allugard.hotel.model.dao.*;
import ua.allugard.hotel.model.dao.impl.*;

import java.sql.Connection;

/**
 * Created by allugard on 01.07.17.
 */
public class DaoFactory {

    private DaoFactory() {
    }

    private static class Holder {
        static final DaoFactory INSTANCE = new DaoFactory();
    }

    public static DaoFactory getInstance() {
        return Holder.INSTANCE;
    }

    public ApartmentDao createApartmentDao(DatabaseConnection connection){
        return new JdbcApartmentDao(connection.getConnection());
    }

    public BillDao createBillDao(DatabaseConnection connection){
        return new JdbcBillDao(connection.getConnection());
    }

    public BookingDao createBookingDao(DatabaseConnection connection){
        return new JdbcBookingDao(connection.getConnection());
    }

    public UserAuthenticationDao createUserAuthenticationDao(DatabaseConnection connection){
        return new JdbcUserAuthenticationDao(connection.getConnection());
    }

    public UserDao createUserDao(DatabaseConnection connection){
        return new JdbcUserDao(connection.getConnection());
    }
}
