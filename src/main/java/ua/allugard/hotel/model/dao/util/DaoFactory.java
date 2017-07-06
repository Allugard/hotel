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

    public ApartmentDao createApartmentDao(){
        return JdbcApartmentDao.getInstance();
    }

    public BillDao createBillDao(){
        return JdbcBillDao.getInstance();
    }

    public BookingDao createBookingDao(){
        return JdbcBookingDao.getInstance();
    }

    public UserAuthenticationDao createUserAuthenticationDao(){
        return JdbcUserAuthenticationDao.getInstance();
    }

    public UserDao createUserDao(){
        return JdbcUserDao.getInstance();
    }
}
