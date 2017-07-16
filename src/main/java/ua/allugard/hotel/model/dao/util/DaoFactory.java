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

    public ApartmentDao getApartmentDao(){
        return JdbcApartmentDao.getInstance();
    }

    public BillDao getBillDao(){
        return JdbcBillDao.getInstance();
    }

    public BookingDao getBookingDao(){
        return JdbcBookingDao.getInstance();
    }

    public UserAuthenticationDao getUserAuthenticationDao(){
        return JdbcUserAuthenticationDao.getInstance();
    }

    public UserDao getUserDao(){
        return JdbcUserDao.getInstance();
    }
}
