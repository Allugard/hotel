package ua.allugard.hotel.model.service;

import ua.allugard.hotel.model.dao.util.ConnectionManager;
import ua.allugard.hotel.model.dao.util.DaoFactory;
import ua.allugard.hotel.model.entity.Bill;
import ua.allugard.hotel.util.exceptions.DaoException;

import java.util.Optional;

/**
 * Created by allugard on 05.07.17.
 */
public class BillService {

    private ConnectionManager connectionManager;
    private DaoFactory daoFactory;

    BillService(ConnectionManager connectionManager, DaoFactory daoFactory) {
        this.connectionManager = connectionManager;
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        static final BillService INSTANCE = new BillService(ConnectionManager.getInstance(), DaoFactory.getInstance());
    }

    public static BillService getInstance() {
        return Holder.INSTANCE;
    }

    Optional<Bill> find(int id){
        Optional<Bill> bill;
        bill = daoFactory.getBillDao().find(id);
        return bill;
    }

    boolean create(Bill bill){
        boolean created = false;
        try {
            created = daoFactory.getBillDao().create(bill);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return created;
    }

    boolean delete(Bill bill){
        boolean deleted;
        deleted = daoFactory.getBillDao().delete(bill.getId());
        return deleted;
    }
}
