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

    public ApartmentDao createApartmentDao(Connection connection){
        return new JdbcApartmentDao(connection);
    }

    public BillDao createBillDao(Connection connection){
        return new JdbcBillDao(connection);
    }

    public BookingDao createBookingDao(Connection connection){
        return new JdbcBookingDao(connection);
    }

    public UserAuthenticationDao createUserAuthenticationDao(Connection connection){
        return new JdbcUserAuthenticationDao(connection);
    }

    public UserDao createUserDao(Connection connection){
        return new JdbcUserDao(connection);
    }
}
