package com.nixsolutions.angelin.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nixsolutions.angelin.jdbc.interfaces.UserDao;
import com.nixsolutions.angelin.jdbc.pojo.User;

public class JdbcUserDao extends AbstractJdbcDao implements UserDao{
    private final String queryFindByLogin = "SELECT * FROM user WHERE login = ?";
    private final String queryFindByEmail = "SELECT * FROM user WHERE email = ?";
    private final String queryFindAll = "SELECT * FROM user";
    private final String queryCreate = "INSERT INTO user(id_user, login, password, email, name, surname, birthday, role_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private final String queryUpdate = "UPDATE user SET login = ?, password = ?, email = ?, name = ?, surname = ?, birthday = ?, role_id = ? WHERE id_user = ?";
    private final String queryRemove = "DELETE FROM user WHERE id_user = ?";
    private boolean isTest = false;

    public JdbcUserDao() {
        getPropertiesData();
    }

    // Constructor for the test
    public JdbcUserDao(boolean isTest) {
        getPropertiesData();
        if (isTest == true) {
            this.isTest = isTest;
            URL = "jdbc:h2:mem:test";
        }
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = createConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryFindAll);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id_user"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("surname"));
                user.setBirthday(resultSet.getDate("birthday"));
                long roleId = resultSet.getLong("role_id");
                user.setRole(new JdbcRoleDao(isTest).findById(roleId));
                list.add(user);
            }
            connection.commit();
        } catch (SQLException e) {
            LOG.error("Some SQL Exception!");
            try {
                connection.rollback();
            } catch (SQLException e1) {
                LOG.error("Rollback Exception!");
                throw new RuntimeException(e);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOG.error("Session not closed!");
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    @Override
    public void create(User user) {
        if (user == null) {
            LOG.error("Passed an invalid object (equals null)");
            throw new NullPointerException();
        }
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = createConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(queryCreate);
            statement.setLong(1, user.getId());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getFirstName());
            statement.setString(6, user.getLastName());
            statement.setDate(7, user.getBirthday());
            statement.setLong(8, user.getRole().getId());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            LOG.error("Some SQL Exception!");
            try {
                connection.rollback();
            } catch (SQLException e1) {
                LOG.error("Rollback Exception!");
                throw new RuntimeException(e);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOG.error("Session not closed!");
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void update(User user) {
        if (user == null) {
            LOG.error("Passed an invalid object (equals null)");
            throw new NullPointerException();
        }
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = createConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(queryUpdate);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setDate(6, user.getBirthday());
            statement.setLong(7, user.getRole().getId());
            statement.setLong(8, user.getId());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            LOG.error("Some SQL Exception!");
            try {
                connection.rollback();
            } catch (SQLException e1) {
                LOG.error("Rollback Exception!");
                throw new RuntimeException(e);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOG.error("Session not closed!");
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void remove(User user) {
        if (user == null) {
            LOG.error("Passed an invalid object (equals null)");
            throw new NullPointerException();
        }
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = createConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(queryRemove);
            statement.setLong(1, user.getId());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            LOG.error("Some SQL Exception!");
            try {
                connection.rollback();
            } catch (SQLException e1) {
                LOG.error("Rollback Exception!");
                throw new RuntimeException(e);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOG.error("Session not closed!");
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public User findByLogin(String login) {
        if (login == null) {
            LOG.error("Passed an invalid login (equals null)");
            throw new NullPointerException();
        }
        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = createConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(queryFindByLogin);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            resultSet.next();
            user = new User();
            user.setId(resultSet.getLong("id_user"));
            user.setLogin(login);
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setFirstName(resultSet.getString("name"));
            user.setLastName(resultSet.getString("surname"));
            user.setBirthday(resultSet.getDate("birthday"));
            long roleId = resultSet.getLong("role_id");
            user.setRole(new JdbcRoleDao(isTest).findById(roleId));
            connection.commit();
        } catch (SQLException e) {
            LOG.error("Some SQL Exception!");
            try {
                connection.rollback();
            } catch (SQLException e1) {
                LOG.error("Rollback Exception!");
                throw new RuntimeException(e);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOG.error("Session not closed!");
                throw new RuntimeException(e);
            }
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        if (email == null) {
            LOG.error("Passed an invalid email (equals null)");
            throw new NullPointerException();
        }
        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = createConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(queryFindByEmail);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            resultSet.next();
            user = new User();
            user.setId(resultSet.getLong("id_user"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(email);
            user.setFirstName(resultSet.getString("name"));
            user.setLastName(resultSet.getString("surname"));
            user.setBirthday(resultSet.getDate("birthday"));
            long roleId = resultSet.getLong("role_id");
            user.setRole(new JdbcRoleDao(isTest).findById(roleId));
            connection.commit();
        } catch (SQLException e) {
            LOG.error("Some SQL Exception!");
            try {
                connection.rollback();
            } catch (SQLException e1) {
                LOG.error("Rollback Exception!");
                throw new RuntimeException(e);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOG.error("Session not closed!");
                throw new RuntimeException(e);
            }
        }
        return user;
    }
}
