package ua.allugard.hotel.model.entity;

import static ua.allugard.hotel.model.entity.Apartment.ApartmentsType.SUITE;

/**
 * Created by allugard on 29.06.17.
 */
public class Apartment {
    private int id;
    private int capacity;
    private int price;
    private String number;
    private ApartmentsType apartmentsType;

    public enum ApartmentsType {
        SUITE,
        STANDART;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public static class Builder {
        private int id;
        private int capacity;
        private int price;
        private String number;
        private ApartmentsType apartmentsType;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setCapacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder setPrice(int price) {
            this.price = price;
            return this;
        }

        public Builder setNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder setApartmentsType(ApartmentsType apartmentsType) {
            this.apartmentsType = apartmentsType;
            return this;
        }

        public Apartment build() {
            Apartment apartment = new Apartment();
            apartment.setId(id);
            apartment.setCapacity(capacity);
            apartment.setPrice(price);
            apartment.setNumber(number);
            apartment.setApartmentsType(apartmentsType);
            return apartment;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ApartmentsType getApartmentsType() {
        return apartmentsType;
    }

    public void setApartmentsType(ApartmentsType apartmentsType) {
        this.apartmentsType = apartmentsType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Apartment that = (Apartment) o;

        if (id != that.id) return false;
        if (capacity != that.capacity) return false;
        if (price != that.price) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        return apartmentsType == that.apartmentsType;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + capacity;
        result = 31 * result + price;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (apartmentsType != null ? apartmentsType.hashCode() : 0);
        return result;
    }
}
