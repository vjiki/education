package com.epam.rd.autocode.impl;

import com.epam.rd.autocode.ConnectionSource;
import com.epam.rd.autocode.RowMapper;
import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapperImpl implements RowMapper<Employee> {

    private static final String SQL_SELECT_EMPLOYEE_BY_ID =
            "SELECT FIRSTNAME, LASTNAME, MIDDLENAME," +
                    " POSITION, HIREDATE, SALARY" +
                    " FROM EMPLOYEE WHERE ID=?";

    @Override
    public Employee mapRow(ResultSet resultSet) {
        Employee employee = null;
        PreparedStatement statement = null;
        Connection connection = null;

        try {
            connection = ConnectionSource.instance().createConnection();
            statement = connection.prepareStatement(SQL_SELECT_EMPLOYEE_BY_ID);
            statement.setInt(1, resultSet.getInt("ID"));
            ResultSet newResultSet = statement.executeQuery();
            while (newResultSet.next()) {
                    employee = new Employee(
                            BigInteger.valueOf(resultSet.getInt("ID")),
                            new FullName(newResultSet.getString("FIRSTNAME"),
                                    newResultSet.getString("LASTNAME"),
                                    newResultSet.getString("MIDDLENAME")),
                            Position.valueOf(newResultSet.getString("POSITION")),
                            newResultSet.getDate("HIREDATE").toLocalDate(),
                            BigDecimal.valueOf(newResultSet.getDouble("SALARY"))
                    );
            }

        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(statement);
            close(connection);
        }
        return employee;
    }

}
