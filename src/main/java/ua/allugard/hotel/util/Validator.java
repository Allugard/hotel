package ua.allugard.hotel.util;

import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by allugard on 16.07.17.
 */
public class Validator {
    private ResourceBundle resourceBundle;
    private static final String REG_EX_BUNDLE_NAME = "reg_ex";
    private static final String LOGIN_REGEX = "login.regexp";
    private static final String PASSWORD_REGEX = "password.regexp";
    private static final String NAME_REGEX = "name.regexp";
    private static final String PHONE_REGEX = "phone.regexp";
    private static final String NUMBER = "number.regexp";
    private final int MIN_CAPACITY = 1;
    private final int MAX_CAPACITY = 4;


    Validator() {
//        Locale locale = Locale.forLanguageTag(language.replace('_', '-'));
        this.resourceBundle = ResourceBundle.getBundle(REG_EX_BUNDLE_NAME);
    }

    public static Validator getInstance() {
        return new Validator();
    }

    public boolean validateLogin(String login){
        return login.matches(resourceBundle.getString(LOGIN_REGEX));
    }

    public boolean validatePassword(String password){
        return password.matches(resourceBundle.getString(PASSWORD_REGEX));
    }

    public boolean validateName(String firstName) {
        return firstName.matches(resourceBundle.getString(NAME_REGEX));
    }

    public boolean validatePhone(String phone) {
        return phone.matches(resourceBundle.getString(PHONE_REGEX));
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


    public boolean validateNumber(String number) {
        return number.matches(resourceBundle.getString(NUMBER));
    }
}
