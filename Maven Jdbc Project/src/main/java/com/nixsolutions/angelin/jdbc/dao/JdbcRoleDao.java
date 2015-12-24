package com.nixsolutions.angelin.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nixsolutions.angelin.jdbc.interfaces.RoleDao;
import com.nixsolutions.angelin.jdbc.pojo.Role;

public class JdbcRoleDao extends AbstractJdbcDao implements RoleDao{ 
    private final String queryFindByName = "SELECT * FROM role WHERE name = ?";
    private final String queryFindById = "SELECT * FROM role WHERE id_role = ?";
    private final String queryCreate = "INSERT INTO role(id_role, name) VALUES(?, ?)";
    private final String queryUpdate = "UPDATE role SET name = ? WHERE id_role = ?";
    private final String queryRemove = "DELETE FROM role WHERE id_role = ?";

    public JdbcRoleDao() {
        getPropertiesData();
    }

    // Constructor for the test
    public JdbcRoleDao(boolean isTest) {
        getPropertiesData();
        if (isTest == true) {
            URL = "jdbc:h2:mem:test";
        }
    }

    @Override
    public void create(Role role) {
        if (role == null) {
            LOG.error("Passed an invalid object (equals null)");
            throw new NullPointerException();
        }
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = createConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(queryCreate);
            statement.setLong(1, role.getId());
            statement.setString(2, role.getName());
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
    public void update(Role role) {
        if (role == null) {
            LOG.error("Passed an invalid object (equals null)");
            throw new NullPointerException();
        }
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = createConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(queryUpdate);
            statement.setString(1, role.getName());
            statement.setLong(2, role.getId());
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
    public void remove(Role role) {
        if (role == null) {
            LOG.error("Passed an invalid object (equals null)");
            throw new NullPointerException();
        }
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = createConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(queryRemove);
            statement.setLong(1, role.getId());
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
    public Role findByName(String name) {
        if (name == null) {
            LOG.error("Passed an invalid name (equals null)");
            throw new NullPointerException();
        }
        Role role = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = createConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(queryFindByName);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            resultSet.next();
            role = new Role();
            role.setId(resultSet.getInt("ID_ROLE"));
            role.setName(resultSet.getString("NAME"));
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
        return role;
    }

    public Role findById(long id) {
        if (id == 0) {
            LOG.error("Passed an invalid id (equals 0)");
            throw new IllegalArgumentException();
        }
        Role role = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = createConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(queryFindById);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();
            role = new Role();
            role.setId(id);
            role.setName(resultSet.getString("name"));
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
        return role;
    }
}
