package ua.allugard.hotel.model.entity;

import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * Created by allugard on 29.06.17.
 */
public class Booking {
    private int id;
    private User user;
    private Apartment apartment;
    private Bill bill;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Status status;

    public enum Status {
        PROCESSED,
        CONFIRMED,
        REJECTED
    }

    public static class Builder {
        private int id;
        private User user;
        private Apartment apartment;
        private Bill bill;
        private LocalDate dateFrom;
        private LocalDate dateTo;
        private Status status;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setApartment(Apartment apartment) {
            this.apartment = apartment;
            return this;
        }

        public Builder setBill(Bill bill) {
            this.bill = bill;
            return this;
        }

        public Builder setDateFrom(Timestamp dateFrom) {
            this.dateFrom = dateFrom.toLocalDateTime().toLocalDate();
            return this;
        }

        public Builder setDateTo(Timestamp dateTo) {
            this.dateTo = dateTo.toLocalDateTime().toLocalDate();
            return this;
        }

        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public Booking build() {
            Booking booking = new Booking();
            booking.setId(id);
            booking.setApartment(apartment);
            booking.setBill(bill);
            booking.setDateFrom(dateFrom);
            booking.setDateTo(dateTo);
            booking.setStatus(status);
            booking.setUser(user);
            return booking;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking booking = (Booking) o;

        if (id != booking.id) return false;
        if (user != null ? !user.equals(booking.user) : booking.user != null) return false;
        if (apartment != null ? !apartment.equals(booking.apartment) : booking.apartment != null) return false;
        if (bill != null ? !bill.equals(booking.bill) : booking.bill != null) return false;
        if (dateFrom != null ? !dateFrom.equals(booking.dateFrom) : booking.dateFrom != null) return false;
        if (dateTo != null ? !dateTo.equals(booking.dateTo) : booking.dateTo != null) return false;
        return status == booking.status;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (apartment != null ? apartment.hashCode() : 0);
        result = 31 * result + (bill != null ? bill.hashCode() : 0);
        result = 31 * result + (dateFrom != null ? dateFrom.hashCode() : 0);
        result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
