package com.nixsolutions.angelin.jdbc.interfaces;

import java.util.List;

import com.nixsolutions.angelin.jdbc.pojo.User;

public interface UserDao {
    void create(User user);

    void update(User user);

    void remove(User user);

    List<User> findAll();

    User findByLogin(String login);

    User findByEmail(String email);
}
