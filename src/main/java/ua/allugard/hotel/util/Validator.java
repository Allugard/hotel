package ua.allugard.hotel.util;

import ua.allugard.hotel.model.entity.Booking;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by allugard on 16.07.17.
 */
public class Validator {
//    private ResourceBundle resourceBundle;
//    private static final String REG_EX_BUNDLE_NAME = "reg_ex";
//    private static final String LOGIN_REGEX = "login.regexp";
//    private static final String PASSWORD_REGEX = "password.regexp";
//    private static final String NAME_REGEX = "name.regexp";
//    private static final String PHONE_REGEX = "phone.regexp";
    private static final String LOGIN_REGEX = "^(?=.{1,50}$)[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD_REGEX = "^.*(?=.{4,10})(?=.*\\d)(?=.*[a-zA-Z]).*$";
    private static final String NAME_REGEX = "^[\\p{L} .'-]+$";
    private static final String PHONE_REGEX = "^(\\+\\d{1,2}\\s?)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{2}\\s?\\d{2}$";
    private static final int MIN_CAPACITY = 1;
    private static final int MAX_CAPACITY = 4;


    Validator() {
//        Locale locale = Locale.forLanguageTag(language.replace('_', '-'));
//        this.resourceBundle = ResourceBundle.getBundle(REG_EX_BUNDLE_NAME);
    }

    public static Validator getInstance() {
        return new Validator();
    }

    public boolean validateLogin(String login){
        return login.matches(LOGIN_REGEX);
    }

    public boolean validatePassword(String password){
        return password.matches(PASSWORD_REGEX);
    }

    public boolean validateName(String firstName) {
        return firstName.matches(NAME_REGEX);
    }

    public boolean validatePhone(String phone) {
        return phone.matches(PHONE_REGEX);
    }

    public boolean validateDate(LocalDate dateFrom, LocalDate dateTo) {
        return dateFrom.isAfter(LocalDate.now()) && dateFrom.isBefore(dateTo);
    }

    public boolean validateCapacity(int persons) {
        return persons >= MIN_CAPACITY && persons <= MAX_CAPACITY;
    }

    public boolean validatePrice(int price) {
        return price > 0;
    }


    public boolean validateNumber(int number) {
        return number > 0;
    }

    public boolean validateNumberAndDateUpdatedBookings(List<Booking> bookings) {
        boolean res = true;
        for (int i = 0; i < bookings.size() - 1; i++) {
            if(bookings.get(i).getApartment() != null) {
                for (int j = i + 1; j < bookings.size(); j++) {
                    if (bookings.get(j).getApartment() != null &&
                            bookings.get(i).getApartment().getId() == bookings.get(j).getApartment().getId() &&
                            !(bookings.get(i).getDateFrom().isAfter(bookings.get(j).getDateTo()) ||
                             bookings.get(i).getDateFrom().isEqual(bookings.get(j).getDateTo()) ||
                             bookings.get(i).getDateTo().isBefore(bookings.get(j).getDateFrom())||
                            bookings.get(i).getDateTo().isEqual(bookings.get(j).getDateFrom()))) {
                        res = false;
                    }
                }
            }
        }
        return res;
    }
}
