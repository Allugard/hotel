package ua.allugard.hotel.model.dto;

import ua.allugard.hotel.model.entity.Apartment;
import ua.allugard.hotel.model.entity.Bill;
import ua.allugard.hotel.model.entity.Booking;

import java.time.LocalDate;

/**
 * Created by allugard on 19.07.17.
 */
public class BookingDto {

    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Apartment.ApartmentsType apartmentsType;
    private int persons;

    public static class Builder {
        private LocalDate dateFrom;
        private LocalDate dateTo;
        private Apartment.ApartmentsType apartmentsType;
        private int persons;

        public Builder setDateFrom(LocalDate dateFrom) {
            this.dateFrom = dateFrom;
            return this;
        }

        public Builder setDateTo(LocalDate dateTo) {
            this.dateTo = dateTo;
            return this;
        }

        public Builder setApartmentsType(Apartment.ApartmentsType apartmentsType) {
            this.apartmentsType = apartmentsType;
            return this;
        }

        public Builder setPersons(int persons) {
            this.persons = persons;
            return this;
        }
        public BookingDto build(){
            BookingDto bookingDto = new BookingDto();
            bookingDto.setDateFrom(dateFrom);
            bookingDto.setDateTo(dateTo);
            bookingDto.setApartmentsType(apartmentsType);
            bookingDto.setPersons(persons);
            return bookingDto;

        }
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

    public Apartment.ApartmentsType getApartmentsType() {
        return apartmentsType;
    }

    public void setApartmentsType(Apartment.ApartmentsType apartmentsType) {
        this.apartmentsType = apartmentsType;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }
}
