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
    private static final Logger logger = LoggerFactory.getLogger(JdbcRoleDao.class);
    
    private static final String SQL_SAVE = ""
            + "INSERT INTO TRAINEESHIP_DB.ROLE (NAME) VALUES (?);";
    
    private static final String SQL_UPDATE = ""
            + "UPDATE TRAINEESHIP_DB.ROLE SET NAME = ? "
            + "WHERE ROLE_ID = ?;";
    
    private static final String SQL_DELETE = ""
            + "DELETE FROM TRAINEESHIP_DB.ROLE WHERE ROLE_ID = ?;";
    
    private static final String SQL_SELECT = ""
            + "SELECT * FROM TRAINEESHIP_DB.ROLE"; 

    @Override
    public void create(Role role) {
        logger.trace("create '" + role + "' in db");
        try(Connection connection = createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE)) {
            preparedStatement.setString(1, role.getName());
            
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            logger.error("Exception durin creating '" + role + "' in db", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Role role) {
        logger.trace("update '" + role + "' in db");
        try(Connection connection = createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setString(1, role.getName());
            preparedStatement.setInt(2, role.getId());
            
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            logger.error("Exception durin updating '" + role + "' in db", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(Role role) {
        logger.trace("remove '" + role + "' from db");
        try(Connection connection = createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setInt(1, role.getId());
            
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            logger.error("Exception durin removing '" + role + "' in db", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Role findByName(String name) {
        logger.trace("searching for role by name = " + name);
        final String PREDICATE = "WHERE NAME = " + name + ";";
        final String SQL = SQL_SELECT + " " + PREDICATE;
        try(Connection connection = createConnection();
                Statement statement = connection.createStatement()) {
            try(ResultSet resultSet = statement.executeQuery(SQL)) {
                Role role = null;
                if (resultSet.next()) {
                    role = createRoleByResultSet(resultSet);
                }
                return role;
            }
        } catch (SQLException e) {
            logger.error("Exception durin searching role by name = " + name, e);
            throw new RuntimeException(e);
        }
    }
    
    public Role findByRoleId(int id) {
        logger.trace("searching role by id = " + id);
        final String PREDICATE = "WHERE ROLE_ID = " + id + ";";
        final String SQL = SQL_SELECT + " " + PREDICATE;
        try(Connection connection = createConnection();
                Statement statement = connection.createStatement()) {
            try(ResultSet resultSet = statement.executeQuery(SQL)) {
                Role role = null;
                if (resultSet.next()) {
                    role = createRoleByResultSet(resultSet);
                }
                return role;
            }
        } catch (SQLException e) {
            logger.error("Exception durin searching role by id = " + id, e);
            throw new RuntimeException(e);
        }
    }
    
    private Role createRoleByResultSet(final ResultSet resultSet) throws SQLException{
        Role role = new Role();
        role.setId(resultSet.getInt("ROLE_ID"));
        role.setName(resultSet.getString("NAME"));
        
        return role;
    }

}
