package ua.allugard.hotel.model.dao.impl;

import ua.allugard.hotel.model.dao.BillDao;
import ua.allugard.hotel.model.entity.Bill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by allugard on 01.07.17.
 */
public class JdbcBillDao implements BillDao {

    private Connection connection;
    private static final int COLUMN_ID_INDEX = 2;
    private static final int COLUMN_PRICE_INDEX = 1;

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PRICE = "price";

    private static final String INSERT_BILL = "INSERT INTO `hotel`.`bills` (`price`) VALUES (?);";
    private static final String UPDATE_BILL = "UPDATE `hotel`.`bills` SET `price`=? WHERE `id`=?;";
    private static final String DELETE_BILL= "DELETE FROM `hotel`.`bills` WHERE `id`=?;";


    private static final String FIND_ALL = "SELECT * FROM bills";
    private static final String FIND_BY_ID = FIND_ALL + " WHERE id = ?";

    public JdbcBillDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Bill> find(int id) {
        Optional<Bill> result = null;
        try (PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_ID)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            result = getBillFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Bill> findAll() {
        List<Bill> result = null;
        try (PreparedStatement statement =
                     connection.prepareStatement(FIND_ALL)){
            ResultSet resultSet = statement.executeQuery();
            result = getBillsFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean create(Bill bill) {
        int insertedRow = 0;
        try (PreparedStatement statement =
                     connection.prepareStatement(INSERT_BILL,
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(COLUMN_PRICE_INDEX, bill.getPrice());
            insertedRow = statement.executeUpdate();
            bill.setId(statement.getGeneratedKeys().getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertedRow > 0;
    }

    @Override
    public boolean update(Bill bill) {
        int updatedRow = 0;
        try (PreparedStatement statement =
                     connection.prepareStatement(UPDATE_BILL)) {
            statement.setInt(COLUMN_PRICE_INDEX, bill.getPrice());
            statement.setInt(COLUMN_ID_INDEX, bill.getId());
            updatedRow = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updatedRow > 0;
    }

    @Override
    public boolean delete(int id) {
        int deletedRow = 0;
        try (PreparedStatement statement =
                     connection.prepareStatement(DELETE_BILL)) {
            statement.setInt(1, id);
            deletedRow = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deletedRow > 0;
    }

    private List<Bill> getBillsFromResultSet(ResultSet resultSet) throws SQLException {
        List<Bill> result = new ArrayList<>();
        if(resultSet.isBeforeFirst()) {
            while (resultSet.next()) {
                result.add(buildBill(resultSet));
            }
        }
        return result;
    }

    private Optional<Bill> getBillFromResultSet(ResultSet resultSet) throws SQLException {
        Optional<Bill> result = Optional.empty();
        if(resultSet.isBeforeFirst()){
            while (resultSet.next()) {
                result = Optional.of(buildBill(resultSet));
            }
        }
        return result;
    }

    private Bill buildBill(ResultSet resultSet) throws SQLException {
        return new Bill.Builder()
                .setId(resultSet.getInt(COLUMN_ID))
                .setPrice(resultSet.getInt(COLUMN_PRICE))
                .build();
    }
}
