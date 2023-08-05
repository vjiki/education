package com.epam.rd.autocode.dao.impl;

import com.epam.rd.autocode.ConnectionSource;
import com.epam.rd.autocode.dao.EmployeeDao;
import com.epam.rd.autocode.domain.Department;
import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDaoImpl implements EmployeeDao {

    private static final String SQL_SELECT_ALL_EMPLOYEES =
            "SELECT ID, FIRSTNAME, LASTNAME, MIDDLENAME," +
                    " POSITION, MANAGER, HIREDATE, SALARY, DEPARTMENT" +
                    " FROM EMPLOYEE";
    private static final String SQL_SELECT_EMPLOYEE_BY_ID =
            "SELECT FIRSTNAME, LASTNAME, MIDDLENAME," +
                    " POSITION, MANAGER, HIREDATE, SALARY, DEPARTMENT" +
                    " FROM EMPLOYEE WHERE ID=?";
    private static final String SQL_INSERT_EMPLOYEE =
            "INSERT INTO EMPLOYEE(ID, FIRSTNAME, LASTNAME, MIDDLENAME," +
                    " POSITION, MANAGER, HIREDATE, SALARY, DEPARTMENT)" +
                    " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_EMPLOYEE =
            "DELETE FROM EMPLOYEE WHERE ID=?";
    private static final String SQL_UPDATE_EMPLOYEE =
            "UPDATE EMPLOYEE SET FIRSTNAME=?, LASTNAME=?, MIDDLENAME=?," +
                    " POSITION=?, MANAGER=?, HIREDATE=?, SALARY=?, DEPARTMENT=?" +
                    " WHERE ID=?";
    private static final String SQL_SELECT_EMPLOYEE_BY_MANAGER =
            "SELECT ID, FIRSTNAME, LASTNAME, MIDDLENAME," +
                    " POSITION, HIREDATE, SALARY, DEPARTMENT" +
                    " FROM EMPLOYEE WHERE MANAGER=?";
    private static final String SQL_SELECT_EMPLOYEE_BY_DEPARTMENT =
            "SELECT ID, FIRSTNAME, LASTNAME, MIDDLENAME," +
                    " POSITION, MANAGER, HIREDATE, SALARY" +
                    " FROM EMPLOYEE WHERE DEPARTMENT=?";

    @Override
    public Optional<Employee> getById(BigInteger Id) {
        Employee employee = null;
        PreparedStatement statement = null;
        Connection connection = null;

        try {
            connection = ConnectionSource.instance().createConnection();
            statement = connection.prepareStatement(SQL_SELECT_EMPLOYEE_BY_ID);
            statement.setInt(1, Id.intValue());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                employee = new Employee(
                        Id,
                        new FullName(resultSet.getString("FIRSTNAME"),
                        resultSet.getString("LASTNAME"),
                        resultSet.getString("MIDDLENAME")),
                        Position.valueOf(resultSet.getString("POSITION")),
                        resultSet.getDate("HIREDATE").toLocalDate(),
                        BigDecimal.valueOf(resultSet.getDouble("SALARY")),
                        BigInteger.valueOf(resultSet.getInt("MANAGER")),
                        BigInteger.valueOf(resultSet.getInt("DEPARTMENT"))
                );
            }

        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(statement);
            close(connection);
        }
        return Optional.ofNullable(employee);
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        Statement statement = null;
        Connection connection = null;

        try {
            connection = ConnectionSource.instance().createConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_EMPLOYEES);
            while (resultSet.next()) {
                Employee   employee = new Employee(
                        BigInteger.valueOf(resultSet.getInt("ID")),
                        new FullName(resultSet.getString("FIRSTNAME"),
                                resultSet.getString("LASTNAME"),
                                resultSet.getString("MIDDLENAME")),
                        Position.valueOf(resultSet.getString("POSITION")),
                        resultSet.getDate("HIREDATE").toLocalDate(),
                        BigDecimal.valueOf(resultSet.getDouble("SALARY")),
                        BigInteger.valueOf(resultSet.getInt("MANAGER")),
                        BigInteger.valueOf(resultSet.getInt("DEPARTMENT"))
                );
                employees.add(employee);
                //System.out.println("Getting " + department.getId().intValue() + " " + department.getName() + " " + department.getLocation());
            }

        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(statement);
            close(connection);
        }
        return employees;
    }

    @Override
    public Employee save(Employee employee) {
        PreparedStatement statement = null;
        Connection connection = null;

        try {
            connection = ConnectionSource.instance().createConnection();

            if (!getById(employee.getId()).isPresent()) {
                statement = connection.prepareStatement(SQL_INSERT_EMPLOYEE);
                statement.setInt(1, employee.getId().intValue());
                statement.setString(2, employee.getFullName().getFirstName());
                statement.setString(3, employee.getFullName().getLastName());
                statement.setString(4, employee.getFullName().getMiddleName());
                statement.setString(5, employee.getPosition().toString());
                statement.setInt(6, employee.getManagerId().intValue());
                statement.setDate(7, Date.valueOf(employee.getHired()));
                statement.setDouble(8, employee.getSalary().doubleValue());
                statement.setInt(9, employee.getDepartmentId().intValue());
                //System.out.println("Inserting " + employee.getPosition().toString());
            } else {
                statement = connection.prepareStatement(SQL_UPDATE_EMPLOYEE);
                statement.setString(1, employee.getFullName().getFirstName());
                statement.setString(2, employee.getFullName().getLastName());
                statement.setString(3, employee.getFullName().getMiddleName());
                statement.setString(4, employee.getPosition().toString());
                statement.setInt(5, employee.getManagerId().intValue());
                statement.setDate(6, Date.valueOf(employee.getHired()));
                statement.setDouble(7, employee.getSalary().doubleValue());
                statement.setInt(8, employee.getDepartmentId().intValue());
                statement.setInt(9, employee.getId().intValue());
                //System.out.println("Updating " + department.getId().intValue() + " " + department.getName() + " " + department.getLocation());
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(statement);
            close(connection);
        }

        return employee;
    }

    @Override
    public void delete(Employee employee) {
        PreparedStatement statement = null;
        Connection connection = null;

        try {
            connection = ConnectionSource.instance().createConnection();
            statement = connection.prepareStatement(SQL_DELETE_EMPLOYEE);
            statement.setInt(1, employee.getId().intValue());
            //System.out.println("Deleting " + department.getId().intValue() + " " + department.getName() + " " + department.getLocation());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public List<Employee> getByDepartment(Department department) {
        List<Employee> employees = new ArrayList<>();
        PreparedStatement statement = null;
        Connection connection = null;

        try {
            connection = ConnectionSource.instance().createConnection();
            statement = connection.prepareStatement(SQL_SELECT_EMPLOYEE_BY_DEPARTMENT);
            statement.setInt(1, department.getId().intValue());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee(
                        BigInteger.valueOf(resultSet.getInt("ID")),
                        new FullName(resultSet.getString("FIRSTNAME"),
                                resultSet.getString("LASTNAME"),
                                resultSet.getString("MIDDLENAME")),
                        Position.valueOf(resultSet.getString("POSITION")),
                        resultSet.getDate("HIREDATE").toLocalDate(),
                        BigDecimal.valueOf(resultSet.getDouble("SALARY")),
                        BigInteger.valueOf(resultSet.getInt("MANAGER")),
                        department.getId()
                );
                employees.add(employee);
                //System.out.println("Getting " + department.getId().intValue() + " " + department.getName() + " " + department.getLocation());
            }

        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(statement);
            close(connection);
        }
        return employees;
    }

    @Override
    public List<Employee> getByManager(Employee employee) {
        List<Employee> employees = new ArrayList<>();
        PreparedStatement statement = null;
        Connection connection = null;

        try {
            connection = ConnectionSource.instance().createConnection();
            statement = connection.prepareStatement(SQL_SELECT_EMPLOYEE_BY_MANAGER);
            statement.setInt(1, employee.getId().intValue());
            //System.out.println(employee.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee  rs_employee = new Employee(
                        BigInteger.valueOf(resultSet.getInt("ID")),
                        new FullName(resultSet.getString("FIRSTNAME"),
                                resultSet.getString("LASTNAME"),
                                resultSet.getString("MIDDLENAME")),
                        Position.valueOf(resultSet.getString("POSITION")),
                        resultSet.getDate("HIREDATE").toLocalDate(),
                        BigDecimal.valueOf(resultSet.getDouble("SALARY")),
                        employee.getId(),
                        BigInteger.valueOf(resultSet.getInt("DEPARTMENT"))
                );
                employees.add(rs_employee);
                //System.out.println("Getting " + department.getId().intValue() + " " + department.getName() + " " + department.getLocation());
            }

        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(statement);
            close(connection);
        }
        return employees;
    }
}
