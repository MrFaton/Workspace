package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nixsolutions.dao.AbstractJdbsDao;
import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.entity.Role;

public class JdbcRoleDao extends AbstractJdbsDao implements RoleDao {
    private static final Logger logger = LoggerFactory
            .getLogger(JdbcRoleDao.class);

    private static final String SQL_SAVE = ""
            + "INSERT INTO TRAINEESHIP_DB.ROLE (NAME) VALUES (?);";

    private static final String SQL_UPDATE = ""
            + "UPDATE TRAINEESHIP_DB.ROLE SET NAME = ? " + "WHERE ROLE_ID = ?;";

    private static final String SQL_DELETE = ""
            + "DELETE FROM TRAINEESHIP_DB.ROLE WHERE ROLE_ID = ?;";

    private static final String SQL_SELECT = ""
            + "SELECT * FROM TRAINEESHIP_DB.ROLE";

    @Override
    public void create(Role role) {
        logger.trace("create " + role);

        if (role == null) {
            throw new NullPointerException("Argument is null");
        }

        if (role.getName() == null || role.getName() == "") {
            throw new IllegalArgumentException("Role's name is requiered");
        }

        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = createConnection();
            ps = connection.prepareStatement(SQL_SAVE);

            ps.setString(1, role.getName());

            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    logger.error("Can't rollback connection", e1);
                }
            }
            logger.error("Exception during creating " + role, e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public void update(Role role) {
        logger.trace("update " + role);

        if (role == null) {
            throw new NullPointerException("Argument is null");
        }

        if (role.getName() == null || role.getName() == "") {
            throw new IllegalArgumentException("Role's name is requiered");
        }

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = createConnection();
            ps = connection.prepareStatement(SQL_UPDATE);

            ps.setString(1, role.getName());
            ps.setInt(2, role.getId());

            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    logger.error("Can't rollback connection", e1);
                }
            }
            logger.error("Exception during updating " + role, e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public void remove(Role role) {
        logger.trace("remove " + role);

        if (role == null) {
            throw new NullPointerException("Argument is null");
        }

        if (role.getId() == null) {
            throw new IllegalArgumentException("Role's id is requiered");
        }

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = createConnection();
            ps = connection.prepareStatement(SQL_DELETE);

            ps.setInt(1, role.getId());

            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    logger.error("Can't rollback connection", e1);
                }
            }
            logger.error("Exception during removing " + role, e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public Role findByName(String name) {
        logger.trace("searching for role by name = " + name);

        if (name == null) {
            throw new NullPointerException("Argument is null");
        }

        if (name.length() == 0) {
            throw new IllegalArgumentException("Role's name is empty");
        }

        final String PREDICATE = "WHERE NAME = '" + name + "';";
        final String SQL = SQL_SELECT + " " + PREDICATE;

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = createConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);

            Role role = null;
            if (resultSet.next()) {
                role = createRoleByResultSet(resultSet);
            }
            connection.commit();
            return role;

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    logger.error("Can't rollback connection", e1);
                }
            }
            logger.error("Exception durin searching role by name = " + name, e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    public Role findByRoleId(int id) {
        logger.trace("searching role by id = " + id);

        final String PREDICATE = "WHERE ROLE_ID = " + id + ";";
        final String SQL = SQL_SELECT + " " + PREDICATE;

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            connection = createConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);

            Role role = null;
            if (resultSet.next()) {
                role = createRoleByResultSet(resultSet);
            }
            connection.commit();
            return role;

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    logger.error("Can't rollback connection", e1);
                }
            }
            logger.error("Exception durin searching role by id = " + id, e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    private Role createRoleByResultSet(final ResultSet resultSet)
            throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getInt("ROLE_ID"));
        role.setName(resultSet.getString("NAME"));

        return role;
    }

}
