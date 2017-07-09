package ua.allugard.hotel.model.entity;

/**
 * Created by allugard on 29.06.17.
 */
public class User {

    private String firstName;
    private String lastName;
    private String phone;
    private UserAuthentication userAuthentication;

    public static class Builder {
        private String firstName;
        private String lastName;
        private String phone;
        private UserAuthentication userAuthentication;

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setUserAuthentication(UserAuthentication userAuthentication) {
            this.userAuthentication = userAuthentication;
            return this;
        }

        public User build() {
            User user = new User();
            user.setUserAuthentication(userAuthentication);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhone(phone);
            return user;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserAuthentication getUserAuthentication() {
        return userAuthentication;
    }

    public void setUserAuthentication(UserAuthentication userAuthentication) {
        this.userAuthentication = userAuthentication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        return userAuthentication != null ? userAuthentication.equals(user.userAuthentication) : user.userAuthentication == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (userAuthentication != null ? userAuthentication.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", userAuthentication=" + userAuthentication +
                '}';
    }
}
