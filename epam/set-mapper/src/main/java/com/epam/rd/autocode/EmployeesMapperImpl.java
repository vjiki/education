package com.epam.rd.autocode;

import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class EmployeesMapperImpl implements SetMapper<Set<Employee>>{

    @Override
    public Set<Employee> mapSet(ResultSet resultSet) {
        Set<Employee> employees = new HashSet<>();

        try {
            while (resultSet.next()) {
                int rowIndex = resultSet.getRow();
                Employee employee = new Employee(
                        BigInteger.valueOf(resultSet.getInt("ID")),
                        new FullName(resultSet.getString("FIRSTNAME"),
                                resultSet.getString("LASTNAME"),
                                resultSet.getString("MIDDLENAME")),
                        Position.valueOf(resultSet.getString("POSITION")),
                        resultSet.getDate("HIREDATE").toLocalDate(),
                        BigDecimal.valueOf(resultSet.getDouble("SALARY")),
                        null
                );
                int managerId = resultSet.getInt("MANAGER");
                if (managerId == 0) {
                    employees.add(employee);
                } else {
                    employees.add(getWithFullManagerChain(employee,
                                    BigInteger.valueOf(resultSet.getInt("MANAGER")),
                            resultSet, rowIndex));
                }
            }

        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }

        return employees;
    }

    private Employee getWithFullManagerChain(Employee employee, BigInteger managerId, ResultSet resultSet, int rowIndex) {

        Stack<Employee> managerStack = getAllManagers(employee, managerId, resultSet, rowIndex);

        while(managerStack.size() >= 2) {
            Employee manager = managerStack.pop();
            Employee employee1 = managerStack.pop();

            Employee fullEmployee = new Employee(employee1.getId(),
                    employee1.getFullName(),employee1.getPosition(),
                    employee1.getHired(),employee1.getSalary(),manager);
            managerStack.push(fullEmployee);
        }

        //System.out.println("size: " + managerStack.size());
        //System.out.println("peek: " + managerStack.peek());

        return managerStack.pop();
    }

    private Stack<Employee> getAllManagers(Employee employee, BigInteger managerId, ResultSet resultSet, int rowIndex) {
        Stack<Employee> managerStack = new Stack<>();

        managerStack.push(employee);

        while (managerId != null) {
            if (managerId.intValue() == 0) break;

            Employee nextManager  = null;

            try {
                resultSet.beforeFirst();
                while(resultSet.next()) {
                    if (managerId.intValue() == resultSet.getInt("ID")) {
                        nextManager = new Employee(
                                managerId,
                                new FullName(resultSet.getString("FIRSTNAME"),
                                        resultSet.getString("LASTNAME"),
                                        resultSet.getString("MIDDLENAME")),
                                Position.valueOf(resultSet.getString("POSITION")),
                                resultSet.getDate("HIREDATE").toLocalDate(),
                                BigDecimal.valueOf(resultSet.getDouble("SALARY")),
                                null
                        );
                        managerStack.push(nextManager);
                        managerId = BigInteger.valueOf(resultSet.getInt("MANAGER"));
                        break;
                    }
                }
                resultSet.absolute(rowIndex);
            } catch (SQLException e) {
                System.err.println("SQL exception (request or table failed): " + e);
            }
            if (nextManager == null) {
                managerId = null;
            }
        }
        return managerStack;
    }

}
