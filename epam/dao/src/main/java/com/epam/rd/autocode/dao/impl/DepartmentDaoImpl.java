package com.epam.rd.autocode.dao.impl;

import com.epam.rd.autocode.ConnectionSource;
import com.epam.rd.autocode.dao.DepartmentDao;
import com.epam.rd.autocode.domain.Department;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentDaoImpl implements DepartmentDao {

    private static final String SQL_SELECT_ALL_DEPARTMENTS =
            "SELECT ID, NAME, LOCATION FROM DEPARTMENT";
    private static final String SQL_SELECT_DEPARTMENT_BY_ID =
            "SELECT NAME, LOCATION FROM DEPARTMENT WHERE ID=?";
    private static final String SQL_INSERT_DEPARTMENT =
            "INSERT INTO DEPARTMENT(ID, NAME, LOCATION) VALUES(?, ?, ?)";
    private static final String SQL_DELETE_DEPARTMENT =
            "DELETE FROM DEPARTMENT WHERE ID=? AND NAME=? AND LOCATION=?";
    private static final String SQL_UPDATE_DEPARTMENT =
            "UPDATE DEPARTMENT SET NAME=?, LOCATION=? WHERE ID=?";

    @Override
    public Optional<Department> getById(BigInteger Id) {
        Department department = null;
        PreparedStatement statement = null;
        Connection connection = null;

        try {
            connection = ConnectionSource.instance().createConnection();
            statement = connection.prepareStatement(SQL_SELECT_DEPARTMENT_BY_ID);
            statement.setInt(1, Id.intValue());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                department = new Department(
                        Id,
                        resultSet.getString("NAME"),
                        resultSet.getString("LOCATION")
                );
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(statement);
            close(connection);
        }
        return Optional.ofNullable(department);
    }

    @Override
    public List<Department> getAll() {
        List<Department> departments = new ArrayList<>();
        Statement statement = null;
        Connection connection = null;

        try {
            connection = ConnectionSource.instance().createConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_DEPARTMENTS);
            while (resultSet.next()) {
                Department department = new Department(
                        BigInteger.valueOf(resultSet.getInt("ID")),
                        resultSet.getString("NAME"),
                        resultSet.getString("LOCATION")
                );
                departments.add(department);
                //System.out.println("Getting " + department.getId().intValue() + " " + department.getName() + " " + department.getLocation());
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(statement);
            close(connection);
        }
        return departments;
    }

    @Override
    public Department save(Department department) {
        PreparedStatement statement = null;
        Connection connection = null;

        try {
            connection = ConnectionSource.instance().createConnection();

            if (getById(department.getId()).isEmpty()) {
                statement = connection.prepareStatement(SQL_INSERT_DEPARTMENT);
                statement.setInt(1, department.getId().intValue());
                statement.setString(2, department.getName());
                statement.setString(3, department.getLocation());
                //System.out.println("Inserting " + department.getId().intValue() + " " + department.getName() + " " + department.getLocation());
            } else {
                statement = connection.prepareStatement(SQL_UPDATE_DEPARTMENT);
                statement.setString(1, department.getName());
                statement.setString(2, department.getLocation());
                statement.setInt(3, department.getId().intValue());
                //System.out.println("Updating " + department.getId().intValue() + " " + department.getName() + " " + department.getLocation());
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(statement);
            close(connection);
        }

        return department;
    }

    @Override
    public void delete(Department department) {
        PreparedStatement statement = null;
        Connection connection = null;

        try {
            connection = ConnectionSource.instance().createConnection();
            statement = connection.prepareStatement(SQL_DELETE_DEPARTMENT);
            statement.setInt(1, department.getId().intValue());
            statement.setString(2, department.getName());
            statement.setString(3, department.getLocation());
            //System.out.println("Deleting " + department.getId().intValue() + " " + department.getName() + " " + department.getLocation());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(statement);
            close(connection);
        }
    }
}
