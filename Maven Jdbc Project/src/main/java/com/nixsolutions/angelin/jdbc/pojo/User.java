package com.nixsolutions.angelin.jdbc.pojo;

import java.sql.Date;

public class User {
    private long id;
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Date birthday;
    private Role role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + password.hashCode();
        result = 31 * result + login.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        User user = (User) obj;
        if (!login.equals(user.login))
            return false;
        if (!password.equals(user.password))
            return false;
        return id == user.id;
    }

    @Override
    public String toString() {
        return "User - " + firstName + " " + lastName + ", email - " + email
                + ", date of birth - " + birthday + ", id role - "
                + role.getId() + ", role name - " + role.getName();
    }
}
