package ua.allugard.hotel.model.service;

import ua.allugard.hotel.model.dao.util.ConnectionManager;
import ua.allugard.hotel.model.dao.util.DaoFactory;
import ua.allugard.hotel.model.dao.util.DatabaseConnection;
import ua.allugard.hotel.model.entity.Bill;

import java.util.List;
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
        try (DatabaseConnection connection = connectionManager.getConnection()) {
            bill = daoFactory.createBillDao(connection).find(id);
        }
        return bill;
    }

    boolean create(Bill bill){
        boolean created;
        try (DatabaseConnection connection = connectionManager.getConnection()) {
            created = daoFactory.createBillDao(connection).create(bill);
        }
        return created;
    }

    boolean delete(Bill bill){
        boolean deleted;
        try (DatabaseConnection connection = connectionManager.getConnection()) {
            deleted = daoFactory.createBillDao(connection).delete(bill.getId());
        }
        return deleted;
    }
}
